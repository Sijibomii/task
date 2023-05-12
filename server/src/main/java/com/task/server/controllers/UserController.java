package com.task.server.controllers;

import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import com.mysema.commons.lang.Assert;
import com.task.server.controllers.base.BaseController;
import com.task.server.dto.LoginTokenDto;
import com.task.server.dto.RegisterDto;
import com.task.server.entity.Users;
import com.task.server.services.JwtService;
import com.task.server.services.UserService;
import com.task.server.utils.MessageResult;
import com.task.server.utils.CaptchaUtil;
import com.task.server.utils.Md5;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException; 
import jakarta.mail.internet.MimeMessage;
import jakarta.security.auth.message.AuthException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.task.server.constants.SystemConstants;
import com.task.server.utils.RandomNumber;
import com.task.server.utils.KafkaDispatcher;
import com.task.server.utils.KafkaTopics;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;
import com.task.server.constants.EventEnum;

@SuppressWarnings({"all"})
@RestController
@Controller
public class UserController extends BaseController{
    
    @Autowired
    private UserService userService;

    @Autowired 
    private JwtService jwtService; 

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired 
    private RedisTemplate template;

    @Autowired
    private OAuth2AuthorizedClientService clientService;

    

    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.system.host}")
    private String host;

    
    @GetMapping("/login/oauth/success")
    public MessageResult googleCallback(@AuthenticationPrincipal OAuth2User oauth2User, OAuth2AuthenticationToken authentication) throws Exception{
        
        String registrationId = authentication.getAuthorizedClientRegistrationId();

        if ("google".equals(registrationId)) {
            // Google sign-in
            String email = oauth2User.getAttribute("email");
            Boolean user_exists = userService.emailIsExist(email);
            
            if (user_exists){
                // return tokens
                Users user = userService.findByEmail(email);
                // email should be found but in case
                if (user == null){
                    throw new Exception("user not found");
                }
                // jwt tokens
                var jwtToken = jwtService.generateToken(user);
                var refreshToken = jwtService.generateRefreshToken(user);
               
                LoginTokenDto login = new LoginTokenDto(user.getId(), user.getEmail(), jwtToken, refreshToken);
                KafkaDispatcher.disptach(KafkaTopics.USER_LOGIN_EVENT, user, "user google login", EventEnum.USER_LOGIN);
                return success(login);
            }
            // create users
            String first_name = oauth2User.getAttribute("given_name");
    
            String googleId = oauth2User.getAttribute("sub");
            String avartar = oauth2User.getAttribute("picture");

            Users user = new Users();
            user.setEmail(email);
            user.setAvatarUrl(avartar);
            user.setDisplayName(first_name);
            user.setHasActivated(false);
            user.setOnline(false);
            user.setIsStaff(false);
            user.setHasLoggenIn(false);
            user.setHasActivated(false);
            user.setLastUpdateAt(new Date());
            user.setIsBlocked(false);
            user.setGoogleId(googleId);
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
            authentication.getAuthorizedClientRegistrationId(),
            authentication.getName());
            user.setGoogleAccessToken(client.getAccessToken().getTokenValue());
            Users user_saved = userService.save(user);
            var jwtToken = jwtService.generateToken(user_saved);
            var refreshToken = jwtService.generateRefreshToken(user_saved);
            LoginTokenDto login = new LoginTokenDto(user_saved.getId(), user_saved.getEmail(), jwtToken, refreshToken);
            //  //  disptach event
            KafkaDispatcher.disptach(KafkaTopics.USER_REGISTER_EVENT, user, "user google register", EventEnum.USER_REGISTER);
            return success(login);
        } else if ("github".equals(registrationId)) {
            // github sign-in
            // ...
            System.out.println(registrationId);
            System.out.println(oauth2User);

            String email = oauth2User.getAttribute("email");
            Boolean user_exists = userService.emailIsExist(email);
            if (user_exists){
                // return tokens
                Users user = userService.findByEmail(email);
                // email should be found but in case
                if (user == null){
                    throw new Exception("user not found");
                }
                // jwt tokens
                var jwtToken = jwtService.generateToken(user);
                var refreshToken = jwtService.generateRefreshToken(user);
               
                LoginTokenDto login = new LoginTokenDto(user.getId(), user.getEmail(), jwtToken, refreshToken);
                KafkaDispatcher.disptach(KafkaTopics.USER_LOGIN_EVENT, user, "user github login", EventEnum.USER_LOGIN);
                return success(login);
            }
            String avartar = oauth2User.getAttribute("avatar_url");
            Users user = new Users();
            user.setEmail(email);
            user.setAvatarUrl(avartar);
            user.setDisplayName(oauth2User.getAttribute("login"));
            user.setHasActivated(true);
            user.setOnline(false);
            user.setIsStaff(false);
            user.setHasLoggenIn(true);
            user.setLastUpdateAt(new Date());
            user.setIsBlocked(false);
            user.setGithubId(oauth2User.getAttribute("id"));
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
            authentication.getAuthorizedClientRegistrationId(),
            authentication.getName());
            user.setGithubAccessToken(client.getAccessToken().getTokenValue());
            Users user_saved = userService.save(user);
            var jwtToken = jwtService.generateToken(user_saved);
            var refreshToken = jwtService.generateRefreshToken(user_saved);
            LoginTokenDto login = new LoginTokenDto(user_saved.getId(), user_saved.getEmail(), jwtToken, refreshToken);
             //  disptach event
            KafkaDispatcher.disptach(KafkaTopics.USER_REGISTER_EVENT, user, "user github register", EventEnum.USER_REGISTER);
            return success(login);
        }
        
        return success();
    }


    // key is passed in as a url param
    @SuppressWarnings({"all"}) 
    @RequestMapping(value = "/register/activate")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult activate(String key, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(key)) {
            return error("invalid token");
        }
        ValueOperations valueOperations = template.opsForValue();
        Object info = valueOperations.get(key);
        Users user = null;
        if (info instanceof Users) {
            user = (Users) info;
        }
        if (user == null) {
            return error("invalid link");
        }
        if (userService.emailIsExist(user.getEmail())) {
            // user should not be saved in db before activation
            return error("email already activated");
        } 

        // Delete the key value stored in redis
        valueOperations.getOperations().delete(key);
   
      
        Users user_saved = userService.save(user);
        if (user_saved == null){
            return error("Activation failed");
        }
        KafkaDispatcher.disptach(KafkaTopics.USER_REGISTER_EVENT, user_saved, "user password register", EventEnum.USER_REGISTER);
        // user should login to get tokens
        return success("activation successfull");
    }


    @SuppressWarnings({"all"})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public MessageResult register(HttpServletRequest request) throws Exception{
        // enter details
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
        sb.append(line);
        }
        String requestBody = sb.toString();
        String value = StringUtils.substringBetween(requestBody, "{", "}"); 
        String[] keyValuePairs = value.split(",");         
        Map<String,String> map = new HashMap<>();               

        for(String pair : keyValuePairs)    
        {
            String[] entry = pair.split(":");    
            String _key = entry[0].trim(); 
            String key = _key.substring(1, _key.length() - 1);
            String _val = entry[1].trim();
            String val = _val.substring(1, _val.length() - 1);
            map.put(key, val);        
        }  
        Assert.hasText(map.get("email"),"MISSING_EMAIL");
        Assert.hasText(map.get("password"), "MISSING_PASSWORD");
        Assert.hasText(map.get("captcha"), "MISSING_CAPTCHA");

        // enter captcha
        Boolean validated = CaptchaUtil.validate(request.getSession(), "", map.get("captcha"));
        if (!validated){
            return error("captcha error");
        } 
        RegisterDto register = new RegisterDto(map.get("display_name"), map.get("password"), map.get("email")); 
        Users user = userService.register(register);
        ValueOperations valueOperations = template.opsForValue();
        // verify email
        sendRegistrationEmail(valueOperations, user, user.getEmail());
        KafkaDispatcher.disptach(KafkaTopics.USER_REGISTER_EVENT, user, "user register email sent", EventEnum.USER_REQUEST_REGISTER_EMAIL);
        return success();
    }

    @SuppressWarnings({"all"})
    @Async
    public void sendRegistrationEmail(ValueOperations valueOperations, Users user, String email) throws MessagingException, IOException, TemplateException {

        String token = UUID.randomUUID().toString().replace("-", "");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(email);

        Map<String, Object> model = new HashMap<>(16);
        model.put("name", user.getDisplayName());
        model.put("token", token);
        model.put("host", host);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = cfg.getTemplate("ActivateAccountEmail.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);
        // send email
        javaMailSender.send(mimeMessage);
        valueOperations.set(token, user, 60, TimeUnit.MINUTES);
    }

    @SuppressWarnings({"all"})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public MessageResult login(HttpServletRequest request) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
        sb.append(line);
        }
        String requestBody = sb.toString();
        String value = StringUtils.substringBetween(requestBody, "{", "}"); 
        String[] keyValuePairs = value.split(",");         
        Map<String,String> map = new HashMap<>();               

        for(String pair : keyValuePairs)    
        {
            String[] entry = pair.split(":");    
            String _key = entry[0].trim(); 
            String key = _key.substring(1, _key.length() - 1);
            String _val = entry[1].trim();
            String val = _val.substring(1, _val.length() - 1);
            map.put(key, val);        
        }  
        Assert.hasText(map.get("email"),"MISSING_USERNAME");
        Assert.hasText(map.get("password"), "MISSING_PASSWORD");
        Assert.hasText(map.get("captcha"), "MISSING_CAPTCHA");

        Boolean validated = CaptchaUtil.validate(request.getSession(), "", map.get("captcha"));

        if(!validated){
            // exception 
        }
         //TODO: google captcha

        Users user = userService.login(map.get("email"), map.get("password"));

        if(user == null){
            throw new Exception("user not found");
        }
		String key_id = "USER_ID";
        String key_email = "USER_EMAIL";
		HttpSession session = request.getSession();
		session.setAttribute(key_id, user.getId());
		session.setAttribute(key_email, user.getEmail());
        // send mail 
        ValueOperations valueOperations = template.opsForValue();
        sentEmail(valueOperations, user, user.getEmail());
        KafkaDispatcher.disptach(KafkaTopics.USER_LOGIN_EVENT, user, "user login email sent", EventEnum.USER_REQUEST_LOGIN_EMAIL);
        return success();
        
    }

    @RequestMapping(value = "/login/verify", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public MessageResult verifyLogin(HttpServletRequest request) throws Exception{
        // String id = (String) request.getSession().getAttribute("USER_ID");
        String email = (String) request.getSession().getAttribute("USER_EMAIL");
        Users user = userService.findByEmail(email);
        // email should be found but in case
        if (user == null){
            throw new Exception("user not found");
        }
        // jwt tokens
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        KafkaDispatcher.disptach(KafkaTopics.USER_LOGIN_EVENT, user, "user password login", EventEnum.USER_LOGIN);
        LoginTokenDto login = new LoginTokenDto(user.getId(), user.getEmail(), jwtToken, refreshToken);
        return success(login);
    }


    // send token to email
    @SuppressWarnings({"all"})
    @Async
    public void sentEmail(ValueOperations valueOperations, Users user, String email) throws MessagingException, IOException, TemplateException {
        String token = UUID.randomUUID().toString().replace("-", "");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(email);
  
        Map<String, Object> model = new HashMap<>(16);
        model.put("ame", user.getDisplayName());
        model.put("token", token);


        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = cfg.getTemplate("loginEmail.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);
        // send email
        javaMailSender.send(mimeMessage);
        valueOperations.set(user.getId(), token, 5, TimeUnit.MINUTES);
    }

    
    @SuppressWarnings({"all"})
    @Async
    public void sentResetCode(ValueOperations valueOperations, String email, String code) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(email);
        Map<String, Object> model = new HashMap<>(16);
        model.put("code", code);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = cfg.getTemplate("resetCodeEmail.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);
        //发送邮件
        javaMailSender.send(mimeMessage);
        valueOperations.set(SystemConstants.REQUEST_CODE_PREFIX + email, code, 10, TimeUnit.MINUTES);
    }

    
    @SuppressWarnings({"all"})
    @RequestMapping(value= "/reset/login/password/verify", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageResult sendResetPasswordCode(String email, String code) throws Exception{
        ValueOperations valueOperations = template.opsForValue();
        Object redisCode = valueOperations.get(SystemConstants.RESET_PASSWORD_CODE_PREFIX + email);
        
        if (!code.equals(redisCode.toString())) {
            return error("VERIFICATION_CODE_INCORRECT");
        } else {
            valueOperations.getOperations().delete(SystemConstants.RESET_PASSWORD_CODE_PREFIX + email);
        }
        Users user = null; 
        user = userService.findByEmail(email);

        Object newPassword = valueOperations.get(SystemConstants.RESET_PASSWORD_PREFIX + email);
        user.setPassword((String)newPassword);
        KafkaDispatcher.disptach(KafkaTopics.USER_DETAILS_RESET_EVENT, user, "user password reset", EventEnum.USER_PASSWORD_RESET);
        return success();
    }

    @SuppressWarnings({"all"})
    @RequestMapping(value = "/reset/login/password", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    // email should be a req param
    public MessageResult forgetPassword(String email, HttpServletRequest request) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
        sb.append(line);
        }
        String requestBody = sb.toString();
        String value = StringUtils.substringBetween(requestBody, "{", "}"); 
        String[] keyValuePairs = value.split(",");         
        Map<String,String> map = new HashMap<>();               

        for(String pair : keyValuePairs)    
        {
            String[] entry = pair.split(":");    
            String _key = entry[0].trim(); 
            String key = _key.substring(1, _key.length() - 1);
            String _val = entry[1].trim();
            String val = _val.substring(1, _val.length() - 1);
            map.put(key, val);        
        }  
        Assert.hasText(map.get("password"),"MISSING_PASSWORD");

        ValueOperations valueOperations = template.opsForValue();
        Users user = null; 
        user = userService.findByEmail(email);
        isTrue(map.get("password").length() >= 6 && map.get("password").length() <= 20, "PASSWORD_LENGTH_ILLEGAL");
        notNull(user, "MEMBER_NOT_EXISTS");
        String newPassword = Md5.md5Digest(map.get("password") + user.getSalt()).toLowerCase();
        // send email to reset password:
        sentResetCode(valueOperations, email, SystemConstants.RESET_PASSWORD_CODE_PREFIX + email); 
        KafkaDispatcher.disptach(KafkaTopics.USER_DETAILS_RESET_EVENT, user, "user password reset email sent", EventEnum.USER_REQUEST_PASSWORD_RESET_EMAIL);
        // puts hash in redis
        valueOperations.set(SystemConstants.RESET_PASSWORD_PREFIX + email, newPassword, 10, TimeUnit.MINUTES);
        
        return success();
    }
}
 
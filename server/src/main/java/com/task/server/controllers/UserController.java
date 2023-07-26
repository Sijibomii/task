package com.task.server.controllers;

import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.task.server.entity.Boards;
import com.task.server.entity.Users;
import com.task.server.services.BoardService;
import com.task.server.services.JwtService;
import com.task.server.services.UserService;
import com.task.server.utils.MessageResult;
import com.task.server.utils.CaptchaUtil;
import com.task.server.utils.Md5;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import com.task.server.utils.RandomString;
import com.task.server.utils.Operator;
import com.task.server.serializers.UUIDSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Autowired
    private BoardService boardService;

    @Autowired 
    KafkaDispatcher kafka;


    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.system.host}")
    private String host;

    // get all user boards for this user
    @GetMapping("/user/boards")
    public MessageResult getUserBoards(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // get user from req
        String userId = (String) request.getAttribute("userId");

        if (userId.isEmpty()){
            throw new Exception("Auth error");
        } 

        List<? extends Boards> userBoards = boardService.getAllUserBoards(userId);

        return success(200, userBoards);
    }
    

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
                kafka.disptach(KafkaTopics.WEBSOCKET, user, "user google login", EventEnum.USER_LOGIN, Operator.USER_LOGIN);
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
            kafka.disptach(KafkaTopics.WEBSOCKET, user, "user google register", EventEnum.USER_REGISTER, Operator.USER_REGISTER);
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
                kafka.disptach(KafkaTopics.WEBSOCKET, user, "user github login", EventEnum.USER_LOGIN, Operator.USER_LOGIN);
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
            kafka.disptach(KafkaTopics.WEBSOCKET, user, "user github register", EventEnum.USER_REGISTER, Operator.USER_REGISTER);
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

        // Delete the key value stored in redis
        valueOperations.getOperations().delete(key);
        
        user.setHasActivated(true);
      
        // Users user_saved = userService.save(user);
        
        kafka.disptach(KafkaTopics.WEBSOCKET, user, "user password register", EventEnum.USER_REGISTER, Operator.USER_REGISTER);
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
        kafka.disptach(KafkaTopics.WEBSOCKET, user, "user register email sent", EventEnum.USER_REQUEST_REGISTER_EMAIL, Operator.USER_REQUEST_REGISTER_EMAIL);
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
        System.out.println(token);
        // javaMailSender.send(mimeMessage);
        valueOperations.set(token, user, 60, TimeUnit.MINUTES);
    }

    @SuppressWarnings({"all"})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public MessageResult login(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		
		
        // send mail 
        ValueOperations valueOperations = template.opsForValue();
        System.out.println("mail sent");
        sentEmail(valueOperations, user, user.getEmail());
        kafka.disptach(KafkaTopics.WEBSOCKET, user, "user login email sent", EventEnum.USER_REQUEST_LOGIN_EMAIL, Operator.USER_REQUEST_LOGIN_EMAIL);
        return success(200);
        
    }

    @RequestMapping(value = "/login/verify", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public MessageResult verifyLogin(HttpServletRequest request) throws Exception{
        
        
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
        Assert.hasText(map.get("code"),"MISSING_CODE");
        Assert.hasText(map.get("email"), "MISSING EMAIL");

        Users user = userService.findByEmail(map.get("email"));
        // email should be found but in case
        if (user == null){
            throw new Exception("user not found");
        }
        
        ValueOperations valueOperations = template.opsForValue();
        
        Object token = valueOperations.get(user.getId().toString());

        if (!token.equals(map.get("code"))){
           throw new Exception("INVALID CODE");
        }

        // jwt tokens
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        kafka.disptach(KafkaTopics.WEBSOCKET, user, "user password login", EventEnum.USER_LOGIN, Operator.USER_LOGIN);
        LoginTokenDto login = new LoginTokenDto(user.getId(), user.getEmail(), jwtToken, refreshToken);
        return success(200,login);
    }


    // send token to email
    @SuppressWarnings({"all"})
    // @Async
    public void sentEmail(ValueOperations valueOperations, Users user, String email) throws MessagingException, IOException, TemplateException {
        
        String token = UUID.randomUUID().toString().replace("-", "");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(email);
  
        Map<String, Object> model = new HashMap<>(16);
        model.put("name", user.getDisplayName());
        model.put("token", token);
        System.out.println("#########################################################################");
        System.out.println(token);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = cfg.getTemplate("loginEmail.ftl"); 
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);
        // send email
        // javaMailSender.send(mimeMessage);
       
        // I should probably be calling the serializer in the redis config but okay
        // UUID.fromString()
        valueOperations.set(user.getId().toString(), token, 5, TimeUnit.MINUTES);
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

        javaMailSender.send(mimeMessage);
        valueOperations.set(SystemConstants.REQUEST_CODE_PREFIX + email, code, 10, TimeUnit.MINUTES);
    }

    @SuppressWarnings({"all"})
    @Async
    public void sentResetPassswordCode(ValueOperations valueOperations, String email) throws MessagingException, IOException, TemplateException {
        // generate random code
        String code = RandomString.generate(32);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(email);
        Map<String, Object> model = new HashMap<>(16);
        model.put("code", code);
        model.put("email", email);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        Template template = cfg.getTemplate("resetPassword.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(html, true);

        javaMailSender.send(mimeMessage);
        valueOperations.set(SystemConstants.REQUEST_CODE_PREFIX + email, code, 10, TimeUnit.MINUTES);
    }

    
    @SuppressWarnings({"all"})
    @RequestMapping(value= "/reset/login/password/verify", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageResult sendResetPasswordCode(String email, String code, HttpServletRequest request) throws Exception{
        ValueOperations valueOperations = template.opsForValue();
        Object redisCode = valueOperations.get(SystemConstants.RESET_PASSWORD_CODE_PREFIX + email);
        
        if (!code.equals(redisCode.toString())) {
            return error("VERIFICATION_CODE_INCORRECT");
        } else {
            valueOperations.getOperations().delete(SystemConstants.RESET_PASSWORD_CODE_PREFIX + email);
        }
        Users user = null; 
        user = userService.findByEmail(email);

        // set request session here
        String sRand = RandomString.generate(32);
		String key = "USER_RESET_TOKEN";
		HttpSession session = request.getSession();
		session.setAttribute(key, sRand);
		
        kafka.disptach(KafkaTopics.WEBSOCKET, user, "user password reset code verified", EventEnum.USER_PASSWORD_RESET_CODE_VERIFIED, Operator.USER_PASSWORD_RESET_CODE_VERIFIED);
        return success();
    }


    @SuppressWarnings({"all"})
    @RequestMapping(value= "/reset/login/password/", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageResult resetPasswordCode(String email, HttpServletRequest request) throws Exception{
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

        // check request session 

        isTrue(map.get("password").length() >= 6 && map.get("password").length() <= 20, "PASSWORD_LENGTH_ILLEGAL");
        Users user = null; 
        user = userService.findByEmail(email);
        String newPassword = Md5.md5Digest(map.get("password") + user.getSalt()).toLowerCase();
        user.setPassword((String)newPassword);
        return success(true);
    }

    @SuppressWarnings({"all"})
    @RequestMapping(value = "/reset/login/password/request", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    // email should be a req param
    public MessageResult forgetPassword(String email, HttpServletRequest request) throws Exception {

        ValueOperations valueOperations = template.opsForValue();
        Users user = null; 
        user = userService.findByEmail(email);
        
        notNull(user, "MEMBER_NOT_EXISTS");
   
        // send email to reset password:
        sentResetPassswordCode(valueOperations, email); 
        kafka.disptach(KafkaTopics.WEBSOCKET, user, "user password reset email sent", EventEnum.USER_REQUEST_PASSWORD_RESET_EMAIL, Operator.USER_REQUEST_PASSWORD_RESET_EMAIL);
        
        return success();
    }
}
 
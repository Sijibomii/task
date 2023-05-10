package com.task.server.controllers;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException; 
import jakarta.mail.internet.MimeMessage;
import jakarta.security.auth.message.AuthException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.task.server.constants.SystemConstants;
import com.task.server.utils.RandomNumber;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

@RestController
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


    // @Autowired
    // private MemberEvent memberEvent;


    // /login/oauth2/code/google is the default redirect URL used by Spring Security for Google OAuth2 authentication.
    @GetMapping("/login/oauth2/code/google")
    public String googleCallback(@AuthenticationPrincipal OAuth2User oauth2User) {
        System.out.println(oauth2User);
        return "oauth2-success";
    }


    @SuppressWarnings({"all"})
    @RequestMapping(value = "/register/active")
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
        return success();
        
    }

    @RequestMapping(value = "/verify/login", method = RequestMethod.POST)
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

    // anything that needs email authentication code will be requested here
    @SuppressWarnings({"all"})
    @RequestMapping(value= "/request/reset/code", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageResult sendResetCode(HttpServletRequest request) throws Exception{
        String id = (String) request.getSession().getAttribute("USER_ID");
        Users user = userService.findById(id);

        if(user == null){
            throw new AuthException();
        }

        ValueOperations valueOperations = template.opsForValue();
        if (valueOperations.get(SystemConstants.REQUEST_CODE_PREFIX + user.getEmail()) != null) {
            return error("EMAIL ALREADY SENT");
        }
        try {
            String code = String.valueOf(RandomNumber.getRandomNumber(100000, 999999));
            String email = user.getEmail();

            sentResetCode(valueOperations, email, code); 
        } catch (Exception e) {
            e.printStackTrace();
            return error("SEND_FAILED");
        }
        return success("SENT_SUCCESS");
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

    // anything that needs email authentication code will be requested here
    @SuppressWarnings({"all"})
    @RequestMapping(value= "/request/reset/login", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageResult sendResetPasswordCode(String email) throws Exception{
        Users user = userService.findByEmail(email);

        if(user == null){
            throw new AuthException();
        }

        ValueOperations valueOperations = template.opsForValue();
        if (valueOperations.get(SystemConstants.RESET_PASSWORD_CODE_PREFIX + user.getEmail()) != null) {
            return error("EMAIL ALREADY SENT");
        }
        try {
            String code = String.valueOf(RandomNumber.getRandomNumber(100000, 999999));

            sentResetCode(valueOperations, email, code); 
        } catch (Exception e) {
            e.printStackTrace();
            return error("SEND_FAILED");
        }
        return success("SENT_SUCCESS");
    }

    @SuppressWarnings({"all"})
    @RequestMapping(value = "/reset/login/password", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
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
        Assert.hasText(map.get("code"),"MISSING_CODE");

        Users user = null; 
        ValueOperations valueOperations = template.opsForValue();
        Object redisCode = valueOperations.get(SystemConstants.RESET_PASSWORD_CODE_PREFIX + email);
        
        user = userService.findByEmail(email);

        isTrue(map.get("password").length() >= 6 && map.get("password").length() <= 20, "PASSWORD_LENGTH_ILLEGAL");
        notNull(user, "MEMBER_NOT_EXISTS");
        if (!map.get("code").equals(redisCode.toString())) {
            return error("VERIFICATION_CODE_INCORRECT");
        } else {
            valueOperations.getOperations().delete(SystemConstants.RESET_PASSWORD_CODE_PREFIX + email);
        }
        //生成密码
        String newPassword = Md5.md5Digest(map.get("password") + user.getSalt()).toLowerCase();
        user.setPassword(newPassword);
        return success();
    }
}

package com.task.server.interceptors;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;

import com.task.server.entity.Users;
import com.task.server.services.JwtService;
import com.task.server.services.UserService;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = extractToken(request);
        if (token != null) {
            try {
                String email = jwtService.extractEmail(token);
                Users user = userService.findByEmail(email);

                if (user != null && jwtService.isTokenValid(token, user)) {
                    // token is valid 
                    request.setAttribute("userId", user.getId());
                    request.setAttribute("displayName", user.getDisplayName());
                    PreAuthenticatedAuthenticationToken auth = new PreAuthenticatedAuthenticationToken(user, "", user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (JwtException ex) {
                // Token is invalid or expired
                if(tokenExpired(request)){
                    // get refresh token if any
                    String refresh = extractRefreshToken(request);
                    String email = jwtService.extractEmail(refresh);
                    Users user = userService.findByEmail(email);
                    Boolean isValid = jwtService.isTokenValid(token, user);

                    if (isValid){
                        // create new access token and write in response
                       String access =  jwtService.generateToken(user);
                       response.addHeader("access", access);
                       // token is valid
                       request.setAttribute("userId", user.getId());
                       request.setAttribute("displayName", user.getDisplayName());

                    }else{
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        return false;
                    }


                }else{
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                } 
            }
        }
        return true;
    }

    

    private Boolean tokenExpired(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return false;
        }
        String token = header.substring(7);
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(jwtService.getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
           return false;
        }
    }

    private String extractRefreshToken(HttpServletRequest request) {
        String header = request.getHeader("Refresh");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }


}

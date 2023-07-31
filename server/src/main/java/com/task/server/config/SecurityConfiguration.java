package com.task.server.config;



import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration{

//   private final JwtAuthenticationFilter jwtAuthFilter;
//   private final AuthenticationProvider authenticationProvider;
//   private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // http
        // disables Cross-Site Request Forgery (CSRF) protection for this application.
        // .csrf()
        // .disable()
        // // configures authorization rules for incoming HTTP requests.
        // .authorizeHttpRequests()
        // // specifies the request matchers to which the following authorization rules will apply. In this case, the listed URL patterns are used as request matchers.
        // .requestMatchers(
        //         "/request/reset/code",
        //         "/register/**",
        //         "/login/**",
        //         "/login",
        //         "/verify/login",
        //         "/request/reset/code",
        //         "/captcha",
        //         "/oauth2/**"
        // )
        //   // allows any user to access the specified URLs without authentication.
        //   .permitAll()
        //   // applies the following authorization rules to all other requests.
        //   .anyRequest()
        //   // requires authentication for the specified URLs.
        //   .authenticated()
        //   .and()
        //   // configures OAuth2 login for this application.
        //   .oauth2Login()
        //   // // specifies the URL for the login page.
        //   .defaultSuccessUrl("/login/oauth/success", true)
        //   .failureUrl("/login?error=true");
          

    return http.build();
  }
}
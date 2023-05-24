package com.task.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

@Configuration
public class ShiroConfig {
    
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean(name="appRealm")
    public AppRealm adminRealm() {
        AppRealm appRealm = new appRealm() ;
        
        adminRealm.setCacheManager(ehCacheManager);
        return adminRealm;
    }
}

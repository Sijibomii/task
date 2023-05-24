package com.task.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.task.server.core.AppRealm;
import com.task.server.core.InstancePermissionResolver;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

@Configuration
public class ShiroConfig {
    
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(appRealm());
        return securityManager;
    }

    @Bean(name="appRealm")
    public AppRealm appRealm() {
        AppRealm appRealm = new AppRealm() ;
        appRealm.setPermissionResolver(new InstancePermissionResolver());
        return appRealm;
    }
}

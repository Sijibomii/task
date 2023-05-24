package com.task.server.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.server.entity.Permissions;
import com.task.server.entity.Users;
import com.task.server.services.PermissionService;
import com.task.server.services.UserService;
import java.util.UUID;

@Component(value = "realm")
public class AppRealm extends AuthorizingRealm{
    
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // check how this principal is passed in
        String currentUsername = (String) getAvailablePrincipal(principals);
        System.out.println(currentUsername);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new AuthorizationException();
        }
        String email = (String) subject.getPrincipal();
        Users user = userService.findByEmail(email);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // will store something like "create:channel:<id>"
        List<String> permissionList = new ArrayList<>();
        try {
            List<Permissions> list;
            if ("root@task.com".equalsIgnoreCase(user.getEmail())) {
                list = permissionService.findAll();
            } else {
                list = permissionService.findUserPermissions(user.getId());
            }
            
            list.forEach(x -> {
                if (!StringUtils.isEmpty(x.getName())) {
                    permissionList.add(x.getName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException();
        }
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        throw new UnsupportedOperationException("Authentication is not supported in this realm");
    }
}

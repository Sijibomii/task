package com.task.server.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.server.entity.Users;
import com.task.server.services.UserService;

@Component(value = "realm")
public class AppRealm extends AuthorizingRealm{
    
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // check how this principal is passed in
        String currentUsername = (String) getAvailablePrincipal(principals);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            // throw error
        }
        String email = (String) subject.getPrincipal();
        Users user = userService.findByEmail(email);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissionList = new ArrayList<>();
        try {
            List<SysPermission> list;
            if ("root".equalsIgnoreCase(admin.getUsername())) {
                list = sysPermissionService.findAll();
            } else {
                SysRole sysRole = sysRoleService.findOne(admin.getRoleId());
                list = sysRole.getPermissions();
            }
            //获取当前用户权限列表
            list.forEach(x -> {
                if (!StringUtils.isEmpty(x.getName())) {
                    permissionList.add(x.getName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException();
        }


        return authorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        throw new UnsupportedOperationException("Authentication is not supported in this realm");
    }
}

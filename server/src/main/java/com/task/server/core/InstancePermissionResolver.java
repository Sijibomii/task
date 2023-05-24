package com.task.server.core;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class InstancePermissionResolver implements PermissionResolver{
    
    @Override
    public Permission resolvePermission(String permissionString) {

        if (permissionString.contains("{")) {
            return new InstanceWildcardPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }

}

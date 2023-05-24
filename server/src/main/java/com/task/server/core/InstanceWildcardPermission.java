package com.task.server.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;


public class InstanceWildcardPermission extends WildcardPermission {

    private final String[] dynamicValues;

    public InstanceWildcardPermission(String wildcardString) {
        super(wildcardString);
        this.dynamicValues = extractDynamicValues(wildcardString);
    }

    @Override
    public boolean implies(Permission p) {
        if (p instanceof InstanceWildcardPermission) {
            // realm returns an array of Strings of permissions belonging to this user and basically does string comparision to see if that permission is in there
            InstanceWildcardPermission other = (InstanceWildcardPermission) p;
            if (dynamicValues.length != other.dynamicValues.length) {
                return false;
            }
            for (int i = 0; i < dynamicValues.length; i++) {
                if (!dynamicValues[i].equals(other.dynamicValues[i])) {
                    return false;
                }
            }
            return super.implies(other);
        }
        return false;
    }

    // extract dynamic values from wildcard string
    private String[] extractDynamicValues(String wildcardString) {
        // Extract the dynamic values from the permission string
        // You can use a regex or custom parsing logic to identify and extract the dynamic values
        // Return the dynamic values as an array of strings
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{(.*?)}");
        Matcher matcher = pattern.matcher(wildcardString);
        while (matcher.find()) {
            String match = matcher.group(1);
            results.add(match);
        }
        return results.toArray(new String[0]);
    }
}


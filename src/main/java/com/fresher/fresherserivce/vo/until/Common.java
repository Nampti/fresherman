package com.fresher.fresherserivce.vo.until;

import java.util.ArrayList;
import java.util.List;

import com.fresher.fresherserivce.vo.enums.Role;

public class Common {

    public static List<Role> convertStringToRoles(String role) {
        List<Role> result = new ArrayList<>();
        if (role == null || role.isEmpty()) {
            return null;
        }
        String[] roles = role.split(";");
        for (String r : roles) {
            result.add(Role.valueOf(r));
        }
        return result;
    }
}

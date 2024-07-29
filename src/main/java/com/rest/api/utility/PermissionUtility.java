package com.rest.api.utility;

import com.rest.api.dto.AuthTokenDetails;

import java.util.List;

public class PermissionUtility {

    /**
     * Checks if the user has the required role.
     *
     * @param authTokenDetails the user whose roles are being checked
     * @param roleName         the name of the required role
     * @return true if the user has the role, false otherwise
     */
    public static boolean hasRole(AuthTokenDetails authTokenDetails, String roleName) {
        List<String> userRoles = authTokenDetails.roles();
        return userRoles.contains(roleName);
    }

    /**
     * Checks if the user has the required permission.
     *
     * @param authTokenDetails the user whose permissions are being checked
     * @param permissionName   the name of the required permission
     * @return true if the user has the permission, false otherwise
     */
    public static boolean hasPermission(AuthTokenDetails authTokenDetails, String permissionName) {
        List<String> userPermissions = authTokenDetails.authorities();
        return userPermissions.contains(permissionName);
    }

    /**
     * Checks if the user has any of the required roles.
     *
     * @param authTokenDetails the user whose roles are being checked
     * @param roleNames        the names of the required roles
     * @return true if the user has any of the roles, false otherwise
     */
    public static boolean hasAnyRole(AuthTokenDetails authTokenDetails, List<String> roleNames) {
        List<String> userRoles = authTokenDetails.roles();
        for (String roleName : roleNames) {
            if (userRoles.contains(roleName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the user has any of the required permissions.
     *
     * @param authTokenDetails the user whose permissions are being checked
     * @param permissionNames  the names of the required permissions
     * @return true if the user has any of the permissions, false otherwise
     */
    public static boolean hasAnyPermission(AuthTokenDetails authTokenDetails, List<String> permissionNames) {
        List<String> userPermissions = authTokenDetails.authorities();
        for (String permissionName : permissionNames) {
            if (userPermissions.contains(permissionName)) {
                return true;
            }
        }
        return false;
    }
}

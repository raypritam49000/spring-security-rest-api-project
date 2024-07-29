package com.rest.api.controller;

import com.rest.api.dto.AuthTokenDetails;
import com.rest.api.exception.UnauthorizedAccessException;
import com.rest.api.utility.PermissionUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/howdy")
public class HowdyController {

    @GetMapping
    public String howdy(HttpServletRequest request) {
        AuthTokenDetails authTokenDetails = (AuthTokenDetails) request.getAttribute("authTokenDetails");
        if (!PermissionUtility.hasPermission(authTokenDetails, "EMPLOYEE_READ")) {
            throw new UnauthorizedAccessException("Access denied");
        }
        return "Hello Howdy";
    }
}

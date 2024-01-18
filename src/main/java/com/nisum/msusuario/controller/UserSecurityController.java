package com.nisum.msusuario.controller;

import com.nisum.msusuario.entity.AuthSecurityRequest;
import com.nisum.msusuario.entity.UserSecurityInfo;
import com.nisum.msusuario.service.impl.UserSecurityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserSecurityController {

    @Autowired
    private UserSecurityInfoService userSecurityInfoService;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserSecurityInfo userSecurityInfo) {
        return userSecurityInfoService.addUser(userSecurityInfo);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthSecurityRequest authSecurityRequest) {
        return userSecurityInfoService.getAuthenticationToken(authSecurityRequest);
    }
}

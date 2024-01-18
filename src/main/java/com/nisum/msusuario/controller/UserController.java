package com.nisum.msusuario.controller;

import com.nisum.msusuario.entity.ApiResponse;
import com.nisum.msusuario.entity.User;
import com.nisum.msusuario.exception.ResourceNotFoundException;
import com.nisum.msusuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> getUsers() {

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Listado de usuarios satisfactorio")
                        .response(userService.getUsers())
                        .build());


    }

    @GetMapping("/find/{email}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String email) {

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Usuario encontrado")
                        .response(userService.findByEmail(email).orElseThrow(ResourceNotFoundException::new))
                        .build());

    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> createUsuario(@RequestBody User user) {

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Usuario creado satisfactoriamente")
                        .response(userService.createUser(user))
                        .build());

    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> updateUsuario(@RequestBody User user) {

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Usuario modificado")
                        .response(userService.updateUser(user))
                        .build());

    }

}
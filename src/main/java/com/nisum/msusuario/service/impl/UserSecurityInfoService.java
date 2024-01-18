package com.nisum.msusuario.service.impl;

import com.nisum.msusuario.entity.AuthSecurityRequest;
import com.nisum.msusuario.entity.UserSecurityInfo;
import com.nisum.msusuario.entity.UserSecurityInfoDetails;
import com.nisum.msusuario.exception.NotUniqueUserSecurityException;
import com.nisum.msusuario.repository.UserSecurityInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserSecurityInfoService implements UserDetailsService {

    @Autowired
    private UserSecurityInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserSecurityInfo> userDetail = repository.findByName(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserSecurityInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserSecurityInfo userSecurityInfo) {
        validateUserSecurityName(userSecurityInfo.getName());
        userSecurityInfo.setPassword(encoder.encode(userSecurityInfo.getPassword()));
        repository.save(userSecurityInfo);
        return "User Added Successfully";
    }

    public String getAuthenticationToken(AuthSecurityRequest authSecurityRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authSecurityRequest.getUsername(), authSecurityRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authSecurityRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    public void validateUserSecurityName(String name) {
        if(!repository.findByName(name).isEmpty()) {
            throw new NotUniqueUserSecurityException();
        }
    }
}

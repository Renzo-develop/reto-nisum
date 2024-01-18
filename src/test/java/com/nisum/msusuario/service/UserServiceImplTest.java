package com.nisum.msusuario.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nisum.msusuario.entity.ApiResponse;
import com.nisum.msusuario.entity.User;
import com.nisum.msusuario.repository.UserRepository;
import com.nisum.msusuario.service.impl.JwtService;
import com.nisum.msusuario.service.impl.UserSecurityInfoService;
import com.nisum.msusuario.service.impl.UserServiceImpl;
import com.nisum.msusuario.util.TestUtils;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserSecurityInfoService userSecurityInfoService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private Gson gson;

    @BeforeEach
    void setUp() {
        gson = new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation().create();
    }

    @Test
    @DisplayName("When list users return not authorized")
    void testWhenGetAllUsersThenReturnOk() throws IOException, IllegalAccessException {
        var mockUser2 = TestUtils.jacksonConvertJSONFileToObject(ResourceUtils.getFile("classpath:mock-user-2.json"), User.class);
        var mockUser3 = TestUtils.jacksonConvertJSONFileToObject(ResourceUtils.getFile("classpath:mock-user-3.json"), User.class);

        List<User> mockListUser = List.of(mockUser2, mockUser3);
        var resultExpected = mockListUser.stream().map(ApiResponse::mutate).collect(Collectors.toList());

        when(userRepository.findAll()).thenReturn(mockListUser);

        var result =  userServiceImpl.getUsers();

        Assertions.assertEquals(result, resultExpected);
    }

}

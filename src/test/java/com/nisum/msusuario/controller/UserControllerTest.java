package com.nisum.msusuario.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nisum.msusuario.entity.ApiResponse;
import com.nisum.msusuario.entity.User;
import com.nisum.msusuario.exception.NotUniqueEmailException;
import com.nisum.msusuario.service.UserService;
import com.nisum.msusuario.util.TestUtils;
import io.jsonwebtoken.JwtException;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private Gson gson;

    @BeforeEach
    void setUp() {
        gson = new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation().create();
    }

    @Test
    @DisplayName("When create user then response Http 200")
    void testWhenCreateUserThenResponseOk() throws IOException, JSONException {
        User imputMocked = TestUtils.jacksonConvertJSONFileToObject(ResourceUtils.getFile("classpath:mock-imput-1.json"), User.class);
        User userMocked = TestUtils.jacksonConvertJSONFileToObject(ResourceUtils.getFile("classpath:mock-user-1.json"), User.class);

        when(userService.createUser(any())).thenReturn(userMocked);

        ResponseEntity<ApiResponse> result = userController.createUsuario(imputMocked);

        Assertions.assertEquals(result.getBody().getResponse(), userMocked);
        Assertions.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("When create user throw exception about not unique email")
    void testWhenCreateUserThenThrowException() throws IOException {
        var imputMocked = TestUtils.jacksonConvertJSONFileToObject(ResourceUtils.getFile("classpath:mock-imput-1.json"), User.class);

        when(userService.createUser(any())).thenThrow(NotUniqueEmailException.class);

        Assertions.assertThrows(NotUniqueEmailException.class, () -> userController.createUsuario(imputMocked));

    }

    @Test
    @DisplayName("When list users return ok")
    void testWhenGetAllUsersThenReturnOk() throws IOException, IllegalAccessException {
        var mockResult = TestUtils.buildListFronJSONFile(ResourceUtils.getFile("classpath:mock-list-user-2.json"), User.class);
        var apiResponseExpected = TestUtils.buildApiResponse("Listado de usuarios satisfactorio", mockResult);

        when(userService.getUsers()).thenReturn(mockResult);

        var result = userController.getUsers();

        Assertions.assertEquals(result.getBody(), apiResponseExpected);
    }

    @Test
    @DisplayName("When list users return not authorized")
    void testWhenGetAllUsersThenReturnNotAuthorizedException() throws IOException, IllegalAccessException {
        when(userService.getUsers()).thenThrow(JwtException.class);

        Assertions.assertThrows(JwtException.class, () -> userController.getUsers());
    }
}

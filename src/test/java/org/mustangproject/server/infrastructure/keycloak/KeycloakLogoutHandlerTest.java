//package org.mustangproject.server.infrastructure.keycloak;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.web.client.RestTemplate;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("!local")
//class KeycloakLogoutHandlerTest {
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private HttpServletRequest request;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @Mock
//    private Authentication auth;
//
//    @Mock
//    private OidcUser oidcUser;
//
//    @InjectMocks
//    private KeycloakLogoutHandler keycloakLogoutHandler;
//
//    @BeforeEach
//    void setUp() {
//        when(auth.getPrincipal()).thenReturn(oidcUser);
//    }
//
//    @Test
//    void testLogoutWhenCalledThenLogoutFromKeycloakCalledWithCorrectUser() {
//        // Arrange
//        // Act
//        keycloakLogoutHandler.logout(request, response, auth);
//        // Assert
//        verify(auth, times(1)).getPrincipal();
//    }
//
//    @Test
//    void testLogoutFromKeycloakWhenSuccessfulResponseThenLogSuccessfulMessage() {
//        // Arrange
//        when(restTemplate.getForEntity(anyString(), eq(String.class)))
//                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));
//        // Act
//        keycloakLogoutHandler.logout(request, response, auth);
//        // Assert
//        verify(restTemplate, times(1)).getForEntity(anyString(), eq(String.class));
//    }
//
//    @Test
//    void testLogoutFromKeycloakWhenUnsuccessfulResponseThenLogErrorMessage() {
//        // Arrange
//        when(restTemplate.getForEntity(anyString(), eq(String.class)))
//                .thenReturn(new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST));
//        // Act
//        keycloakLogoutHandler.logout(request, response, auth);
//        // Assert
//        verify(restTemplate, times(1)).getForEntity(anyString(), eq(String.class));
//    }
//}
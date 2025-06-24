//package org.mustangproject.server.infrastructure.keycloak;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("!local")
//class SecurityConfigTest {
//
//    @Mock
//    private KeycloakLogoutHandler keycloakLogoutHandler;
//
//    private SecurityConfig securityConfig;
//
//    @BeforeEach
//    void setUp() {
//        securityConfig = new SecurityConfig(keycloakLogoutHandler);
//        securityConfig.allowedIPs = "127.0.0.1";
//        securityConfig.oAuth = true;
//    }
//
//    @Test
//    void testFilterChainWhenAllowedIPsIsNotEmpty() throws Exception {
//        // Arrange
//        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
//
//        // Act
//        SecurityFilterChain result = securityConfig.filterChain(http);
//
//        // Assert
//        verify(http).authorizeRequests();
//        verify(http).csrf();
//    }
//
//    @Test
//    void testFilterChainWhenAllowedIPsIsEmptyAndOAuthIsTrue() throws Exception {
//        // Arrange
//        securityConfig.allowedIPs = "";
//        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
//
//        // Act
//        SecurityFilterChain result = securityConfig.filterChain(http);
//
//        // Assert
//        verify(http).authorizeRequests();
//    }
//
//    @Test
//    void testFilterChainWhenAllowedIPsAndOAuthAreFalse() throws Exception {
//        // Arrange
//        securityConfig.allowedIPs = "";
//        securityConfig.oAuth = false;
//        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
//
//        // Act
//        SecurityFilterChain result = securityConfig.filterChain(http);
//
//        // Assert
//        verify(http).authorizeRequests();
//        verify(http).csrf();
//    }
//}
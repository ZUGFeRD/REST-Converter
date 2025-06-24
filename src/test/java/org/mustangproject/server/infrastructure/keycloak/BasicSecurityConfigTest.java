//package org.mustangproject.server.infrastructure.keycloak;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("local")
//class BasicSecurityConfigTest {
//
//    @Mock
//    private WebSecurity webSecurity;
//
//    @InjectMocks
//    private BasicSecurityConfig basicSecurityConfig;
//
//    @Test
//    void testIgnoreResourcesWhenCalledThenIgnoringAndRequestMatchersCalled() {
//        // Arrange
////        WebSecurity.IgnoredRequestConfigurer ignoredRequestConfigurer = mock(WebSecurity.IgnoredRequestConfigurer.class);
////        when(webSecurity.ignoring()).thenReturn(ignoredRequestConfigurer);
//
//        // Act
////        WebSecurityCustomizer webSecurityCustomizer = basicSecurityConfig.ignoreResources();
////        webSecurityCustomizer.customize(webSecurity);
//
//        // Assert
////        verify(webSecurity).ignoring();
////        verify(ignoredRequestConfigurer).requestMatchers(any(RequestMatcher.class));
//    }
//}
//package org.mustangproject.server.infrastructure.keycloak;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.IpAddressMatcher;
//
//@Configuration
//@EnableWebSecurity
//@Profile("local")
//class BasicSecurityConfig {
//
//    @Value("${mustang.allowedIPs}")
//    protected String allowedIPs;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        if (allowedIPs.length()!=0) {
//            IpAddressMatcher hasIpAddress = new IpAddressMatcher(allowedIPs);
//            http
//                .authorizeHttpRequests((authorize) -> {
//                            try {
//                                authorize
//                                    .requestMatchers(HttpMethod.GET,"/**").access((authentication, context) -> new AuthorizationDecision(hasIpAddress.matches(context.getRequest())))
//                                        .anyRequest().permitAll().and().csrf().disable();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//
//        } else {
//            http
//                .authorizeHttpRequests((authorize) -> {
//                            try {
//                                authorize
//                                    .requestMatchers("/**").permitAll().and().csrf().disable();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//        }
//        return http.build();
//    }
//}
package org.mustangproject.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mustangproject.server.error.entity.ApiErrorResponse;
import org.springframework.web.bind.annotation.*;
import java.util.Enumeration;

@RestController
@CrossOrigin
@RequestMapping("mustang")
public class VersionController {

    private static final Logger LOGGER = LogManager.getLogger(VersionController.class);

    @Operation(
            summary = "Returns legal related information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Returns legal related information", useReturnTypeSchema = true),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            }
    )
    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String notice(@RequestHeader(value = "USERNAME", required = false) String username, HttpServletRequest req) {
        LOGGER.info("Operation: notice;User: " + username + ";");

        logRequestInfo(req);


        return "Mustangserver is (c) 2022 by Jochen Stärk (\"usegroup\") and uses the following (unaltered) libraries under Apache License: Spring boot, Mustangproject, Docker, Keycloak, Apache Derby, github.com/phax/phive/, EUPL https://github.com/AgID/EeISI-mapper/";// the idea is to use this value in WebSecurityConfig's hasIpAddress
    }

    private void logRequestInfo(HttpServletRequest req) {
        StringBuffer requestURL = req.getRequestURL();
        String queryString = req.getQueryString();

        if (queryString == null) {
            LOGGER.debug("url: " + requestURL.toString());
        } else {
            LOGGER.debug("url: " + requestURL.append('?').append(queryString));
        }

        LOGGER.debug("method:" + req.getMethod());

        // print all the headers
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            LOGGER.trace("header: " + headerName + ":" + req.getHeader(headerName));
        }

        // print all the request params
        Enumeration params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = (String) params.nextElement();
            LOGGER.trace("Attribute: '" + paramName + "', Value: '" + req.getParameter(paramName) + "'");
        }
    }
}

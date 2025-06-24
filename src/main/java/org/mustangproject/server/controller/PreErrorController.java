// Copyright (c) 2023 Jochen Stärk, see LICENSE file
package org.mustangproject.server.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class PreErrorController implements ErrorController {
    @GetMapping("/error")
    public String error() {
        return "intentional";
    }
}
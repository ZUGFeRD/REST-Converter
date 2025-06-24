// Copyright (c) 2023 Jochen Stärk, see LICENSE file
package org.mustangproject.server.controller;

import com.infocert.eigor.api.EigorApi;
import com.infocert.eigor.api.EigorApiBuilder;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.infocert.eigor.api.*;
import java.io.*;
import java.util.Locale;

import it.infocert.eigor.api.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mustangproject.server.error.entity.ApiErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.TransformerException;

@RestController
// @CrossOrigin
@RequestMapping("eeisi")
@Validated
public class MustangController {

  private static final Logger LOGGER = LogManager.getLogger(MustangController.class);
  @Value("${mustang.additionalLog}")
  protected String additionalLog;

  @Operation(
      summary = "Returns the transformation from UBL to CII.",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Returns the transformation from UBL to CII.",
            useReturnTypeSchema = true),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
      })
  @RequestMapping(value = "/eigor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
  public @ResponseBody HttpEntity<String> eigor(@RequestParam("sourceFormat") @Parameter(example = "ubl") String sourceFormat, @RequestParam("destFormat") @Parameter(example = "cii") String destFormat, @RequestBody @Parameter String xml, @RequestHeader(value = "USERNAME", required = false) String username)
      throws IOException, ConfigurationException, SecurityException {
    LOGGER.info("Operation: eigor;User: " + username + ";" + additionalLog);
    ConversionResult<byte[]> cr = null;

    EigorApi api =
        new EigorApiBuilder()
            .enableForce()
            .build(); // or new EigorApiBuilder().withCustomConfiguration().build();
    try {

      if ((!sourceFormat.toLowerCase().equals("cii")&&!sourceFormat.toLowerCase().equals("ubl")&&!sourceFormat.toLowerCase().equals("fatturapa"))) {
        throw new IllegalArgumentException("source format must be cii, ubl or fatturapa");
      }

      if ((!destFormat.toLowerCase().equals("cii")&&!destFormat.toLowerCase().equals("ubl")&&!destFormat.toLowerCase().equals("fatturapa"))) {
        throw new IllegalArgumentException("destination format must be cii, ubl or fatturapa");
      }

      cr =
          api.convert(
              sourceFormat.toLowerCase(),
              destFormat.toLowerCase(),
                  new ByteArrayInputStream( xml.getBytes() ));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }

    return new HttpEntity<String>(new String(cr.getResult()));
  }




  @Operation(
          summary = "Healthcheck. Just request a ping, will respond with a 'pong'",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Healthcheck. Just request a ping, will respond with a 'pong'", useReturnTypeSchema = true),
                  @ApiResponse(
                          responseCode = "400",
                          description = "Bad Request",
                          content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
          }
  )
  @RequestMapping(value = "/ping", method = RequestMethod.GET)
  public String ping(@RequestHeader(value = "USERNAME", required = false) String username) throws FileNotFoundException, UnsupportedEncodingException, TransformerException {
    LOGGER.info("Operation: ping;User: " + username + ";" + additionalLog);
    return "pong";
  }

}

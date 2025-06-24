package org.mustangproject.server.infrastructure.openapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  private static final Logger LOGGER = LogManager.getLogger(OpenApiConfig.class);

  @Value("${OPENAPI_SERVER_URL:${openapi.server-url}}")
  private String serverUrl;

  @Bean
  public OpenAPI mustangOpenAPI() {

    LOGGER.info("OpenAPI server URL: " + serverUrl);

    OpenAPI oa =
        new OpenAPI()
            .info(
                new Info()
                    .title("Mustangserver")
                    .description(
                        "Mustangproject e-invoice REST server API\n\n**Contacts:**\n"
                            + "- **Jochen Stärk:** General inquiries - [jstaerk@usegroup.de](mailto:jstaerk@usegroup.de)\n"
                            + "- **Martin Wachtveitl:** Technical support - [martin.wachtveitl@itewa.com](mailto:martin.wachtveitl@itewa.com)")
                    .contact(
                        new Contact()
                            .name("Jochen Staerk")
                            .url("http://mustangproject.org/")
                            .email("jstaerk@usegroup.de"))
                    .version("v1.6.0")
                    .license(
                        new License().name("proprietary").url("http://mustangproject.org/server")))
            .externalDocs(
                new ExternalDocumentation()
                    .description("Mustangserver Documentation")
                    .url("https://www.mustangproject.org/files/manual-1.6.0.pdf"));
    if ((serverUrl != null) && (!serverUrl.isEmpty())) {
      oa.addServersItem(new Server().url(serverUrl));
    }
    return oa;
  }
}

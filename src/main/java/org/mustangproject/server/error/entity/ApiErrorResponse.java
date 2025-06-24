// Copyright (c) 2023 Jochen Stärk, see LICENSE file
package org.mustangproject.server.error.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Schema(name = "ApiErrorResponse", description = "Response holding information for client errors.")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ApiErrorResponse {

  @Schema(description = "URL", required = true)
  protected String requestUrl;

  @Schema(description = "HTTP code", required = true)
  protected int httpCode;

  @Schema(description = "Internal error code", required = true)
  protected String errorCode;

  @Schema(description = "Message", required = false)
  protected String message;

  @Schema(description = "Detail description", required = false)
  protected Map<String, String> details;
}

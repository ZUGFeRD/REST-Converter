package org.mustangproject.server.error.exception;

import lombok.Getter;
import org.mustangproject.server.error.ErrorId;

@Getter
public class InternalServerRuntimeException extends RuntimeException {

  private ErrorId errorId;

  public InternalServerRuntimeException(ErrorId errorId) {
    super();
    this.errorId = errorId;
  }

  public InternalServerRuntimeException(ErrorId errorId, Throwable e) {
    super(e);
    this.errorId = errorId;
  }

  public InternalServerRuntimeException(ErrorId errorId, String message) {
    super(message);
    this.errorId = errorId;
  }

  public InternalServerRuntimeException(ErrorId errorId, String message, Throwable t) {
    super(message, t);
    this.errorId = errorId;
  }
}

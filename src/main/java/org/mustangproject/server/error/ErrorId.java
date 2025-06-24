// Copyright (c) 2023 Jochen Stärk, see LICENSE file
package org.mustangproject.server.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum ErrorId {
  INTERNAL_ERROR("MSE1000", "Unbekannte Fehler während der Request Ausführung!"),
  VALIDATION_ERROR("MSE2000", "Die eingegeben Wert scheinen nicht Valide zu sein!");

  private final String code;
  private final String description;

  public String getMessage() {
    return this.code + " : " + this.description;
  }
}

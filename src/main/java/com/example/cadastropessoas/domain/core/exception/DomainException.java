package com.example.cadastropessoas.domain.core.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class DomainException extends Exception {
  private final int errorCode;
  private final String errorMessage;
}

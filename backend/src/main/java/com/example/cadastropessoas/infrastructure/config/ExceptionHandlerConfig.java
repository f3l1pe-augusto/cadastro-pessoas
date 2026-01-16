package com.example.cadastropessoas.infrastructure.config;

import com.example.cadastropessoas.domain.core.exception.DomainException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  private static final String RECURSO_PROBLEMA_PROCESSAR_REQUISICAO = "recurso.problema-processar-requisicao";

  public ExceptionHandlerConfig(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler({EmptyResultDataAccessException.class})
  public ResponseEntity<Object> handleEmptyResultDataAccessException(
      EmptyResultDataAccessException ex,
      WebRequest request
  ) {
    String mensagemUsuario = messageSource.getMessage(
        "recurso.nao-encontrado",
        null,
        LocaleContextHolder.getLocale()
    );

    String mensagemDesenvolvedor = ex.toString();

    List<Erro> erros = List.of(new Erro(mensagemUsuario, mensagemDesenvolvedor));

    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({DomainException.class})
  protected ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {

    String mensagemUsuario = getMessageRecursoProblemaProcessarRequisicao();
    mensagemUsuario += ": " + ex.getMessage();

    String mensagemDesenvolvedor = ex.getMessage();

    List<Erro> erros = List.of(new Erro(mensagemUsuario, mensagemDesenvolvedor));

    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatusCode.valueOf(ex.getErrorCode()), request);
  }

  @ExceptionHandler({InvalidDataAccessResourceUsageException.class})
  public final ResponseEntity<Object> invalidDataAccessResourceUsageException(
      InvalidDataAccessResourceUsageException ex,
      WebRequest request
  ) {
    Erro e;

    String cause = ex.getMostSpecificCause().getMessage();

    e = new Erro(cause, cause);

    return handleExceptionInternal(ex, e, HttpHeaders.EMPTY, HttpStatus.CONFLICT, request);
  }

  private String getMessageRecursoProblemaProcessarRequisicao() {
    return messageSource.getMessage(RECURSO_PROBLEMA_PROCESSAR_REQUISICAO, null, LocaleContextHolder.getLocale());
  }

  private record Erro(String mensagemUsuario, String mensagemDesenvolvedor) { }
}

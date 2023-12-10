package com.jihoon.board.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CustomResponse {
  public static final ResponseEntity<?> serviceUnavailable = 
    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
}

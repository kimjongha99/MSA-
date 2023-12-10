package com.jihoon.board.common.response;

public interface ResponseMessage {
  String SUCCESS = "Success";

  String VALIDATE_FAIL = "Validate Fail";

  String EXISTED_EMAIL = "Existed Email";
  String EXISTED_NICKNAME = "Existed Nickname";
  String EXISTED_TEL_NUMBER = "Existed Tel Number";

  String NO_EXISTED_USER = "No Existed User";
  String NO_EXISTED_BOARD = "No Existed Board";

  String NO_PERMISSION = "No Permission";

  String SIGN_IN_FAIL = "Sign In Data Mismatch";

  String DATABASE_ERROR = "Database Error";
}

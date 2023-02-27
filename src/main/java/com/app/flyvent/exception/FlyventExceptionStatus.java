package com.app.flyvent.exception;

public enum FlyventExceptionStatus {
    MEMBERS_NO_MATCHING_FOUND(false, 1000, "일치하는 회원을 찾을 수 없습니다."),
    MEMBERS_PASSWORD_UNMATCH(false, 10001, "비밀번호가 일치하지 않습니다.");

    private boolean isSuccess;
    private int statusCode;
    private String message;

    FlyventExceptionStatus(boolean isSuccess, int statusCode, String message) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
    }
}

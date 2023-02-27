package com.app.flyvent.exception;

import lombok.Getter;

@Getter
public class FlyventException extends RuntimeException {
    private FlyventExceptionStatus status;

    public FlyventException(FlyventExceptionStatus status) {
        super(status.name());
        this.status = status;
    }
}

package com.app.flyvent.exception;

import com.app.flyvent.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(FlyventException.class)
    private String handleFlyventException(FlyventException exception, Model model) {
        log.info(exception.getStatus().name());

        Message alertMessage = new Message("예외가 발생했습니다.", "/");
        return showMessageAndRedirect(alertMessage, model);
    }

    @ExceptionHandler(BindException.class)
    private String handleNullArgument(BindException exception, Model model) {
        exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(fieldError -> log.info(fieldError.getField() + " : " + fieldError.getDefaultMessage()));

        Message alertMessage = new Message("예외가 발생했습니다.", "/");
        return showMessageAndRedirect(alertMessage, model);
    }

    private String showMessageAndRedirect(Message params, Model model) {
        model.addAttribute("message", params.getMessage());
        model.addAttribute("redirectUri", params.getRedirectUri());
        return "util/alertMessage";
    }
}

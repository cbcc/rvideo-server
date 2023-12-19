package cn.scau.rvideo.server.controller.validation;

import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.dto.Status;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> list = ex.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder("参数错误: ");
        for (ObjectError objectError : list) {
            message.append(objectError.getDefaultMessage());
            message.append("; ");
        }
        Response<Void> response = new Response<>();
        response.setStatus(Status.ARGUMENT_NOT_VALID);
        response.setMessage(message.toString());
        return response;
    }
}

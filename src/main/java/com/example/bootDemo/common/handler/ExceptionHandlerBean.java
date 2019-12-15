package com.example.bootDemo.common.handler;

import com.example.bootDemo.common.consts.CodeMsg;
import com.example.bootDemo.common.model.response.ResponseException;
import com.example.bootDemo.common.model.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerBean {
    /**
     * 处理入参校验BindException异常
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public Result handleBindException(BindException e) {
        String errorMessage = this.processResponse(e.getBindingResult());
        log.warn("参数校验异常:{}", errorMessage);
        return Result.error(CodeMsg.INVALID_PARAM, errorMessage);
    }

    /**
     * 处理入参校验ServletException异常
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = {ServletException.class})
    public Result handleMissionServletRequestException(ServletException e) {
        log.warn("Servlet校验异常:{}", e.getMessage());
        return Result.error(CodeMsg.INVALID_PARAM, e.getMessage());
    }

    /**
     * 处理入参校验MethodNotValidException异常
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result handleMethodNotValidException(MethodArgumentNotValidException e) {
        log.warn("参数校验异常:{}", e.getMessage());
        return Result.error(CodeMsg.INVALID_PARAM, this.processResponse(e.getBindingResult()));
    }

    /**
     * 处理入参校验ResponseException异常
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = {ResponseException.class})
    public Result handleResponseException(ResponseException e) {
        log.warn("业务异常:{}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理入参校验MethodNotValidException异常
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = {UnknownHostException.class})
    public Result handleMethodNotValidException(UnknownHostException e) {
        log.warn("网络请求异常:{}", e.getMessage());
        return Result.error(CodeMsg.UNKNOWN_HOST, "非法的网络地址");
    }

    /**
     * 处理入参校验MethodNotValidException异常
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public Result handleHttpMessageException(HttpMessageNotReadableException e) {
        log.warn("网络请求异常:{}", e.getMessage());
        return Result.error(CodeMsg.NETWORK_NOT_READABLE, "提交的报文信息读取失败,请检查请求方式和报文是否正确");
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public Result handleHttpMessageException(ConstraintViolationException e) {
        log.warn("约束异常:{}", e.getMessage());
        return Result.error(CodeMsg.INVALID_PARAM, e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public Result handleHttpMessageException(MethodArgumentTypeMismatchException e) {
        log.warn("参数类型有误，请检查报文参数:{}", e.getMessage());
        return Result.error(CodeMsg.INVALID_PARAM, "参数类型有误，请检查报文参数");
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public Result handleHttpMessageException(NullPointerException e) {
        log.warn("空值异常:{}", e.getMessage(), e);
        return Result.error(CodeMsg.INVALID_PARAM, "空值异常");
    }

    /**
     * @return
     * @Description: @Validated 验证注解全局返回处理
     * @author maoyun@rtmap.com
     * @Date 17/3/29 19:44
     * 如类注解是 @ControllerAdvice 返回json需要添加 @ResponseBody注解,使用 @RestControllerAdvice注解则可以省略
     * 如继承 ResponseEntityExceptionHandler 类 则返回类型不能是Result ,使用spring rest 默认返回类型ResponseEntity
     */
    @ExceptionHandler(value = {Exception.class})
    public Result validExceptionHandler(Exception e) {
        e.printStackTrace();
        int code = CodeMsg.ERROR;
        String message = "系统内部错误.";
        log.error("ExceptionHandlerBean-无法匹配捕获的异常信息,{}", e.getMessage(), e);
        return Result.error(code, message);
    }

    /**
     * 处理 错误响应
     *
     * @param bindingResult 入参绑定的错误信息
     * @return
     */
    private String processResponse(BindingResult bindingResult) {

        List<ObjectError> errorList = bindingResult.getAllErrors();
        List<String> errMsgList = new ArrayList<>();
        for (ObjectError error : errorList) {
            errMsgList.add(error.getDefaultMessage());
        }
        log.info("请求失败原因:{}，请求参数:{}", errMsgList.toString(), bindingResult.getTarget().toString());
        if (errorList.size() == 1) {
            return errMsgList.get(0);
        }
        return errMsgList.toString();
    }

}

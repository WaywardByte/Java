package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */

    //sql异常提示（注册用户）
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.info(ex.getMessage());

        ex.printStackTrace();
        String message = ex.getMessage();
        //Duplicate entry 'zixia' for key 'employee.idx_username'
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2];
            String re = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(re);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }


    @ExceptionHandler
    public Result exception(Exception ex) {
        String message = ex.getMessage();
        if (message.contains("超出配送范围")) {
            return Result.error(MessageConstant.OUT_OF_RANGE);
        }
        if (message.contains("起售中的菜品不能删除")) {
            return Result.error(MessageConstant.DISH_ON_SALE);
        }
        if (message.contains("套餐内包含未启售菜品")) {
            return Result.error(MessageConstant.SETMEAL_ENABLE_FAILED);
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}

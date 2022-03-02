package com.lyh.controller.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//此类作为springmvc的异常处理器，出现异常都会进到这个类中，这属于控制层的东西，
//正确的处理应该是进到这里，返回的数据类型应该和不出现异常返回的数据类型相同，所以在R类中我们加一个属性msg，用于记录异常信息，然后返回给前端
//@ControllerAdvice
@RestControllerAdvice
//@ControllerAdvice 和 @RestControllerAdvice都是对Controller进行增强的，可以全局捕获spring mvc抛的异常。
//RestControllerAdvice = ControllerAdvice + ResponseBody
//@ResponseBody，表示此方法的返回值仅作为字符串返回
public class ProjectExceptionAdvice {

    @ExceptionHandler
//    ExceptionHandler(异常类型)的作用是用来捕获指定的异常。不加参数则捕获所有类型的异常
    public R doException(Exception ex){

//        在命令行打印异常信息在程序中出错的位置及原因。
//        这个是个经验之谈，很重要的一个操作
        ex.printStackTrace();

//        一般这里捕获到异常，应该下面几步操作
//        记录日志
//        通知运维
//        通知开发
        return new R("服务器故障，请稍后重试！");
    }
}

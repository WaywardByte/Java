package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

//当前类为切面类
@Aspect
//加入spring容器中
@Component
@Slf4j
public class AutoFillAspect {

    @Before("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFill(JoinPoint joinPoint) {
        //1.获取当前被拦截方法的数据库操作模型
        //1.1获取方法签名对象
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //1.2根据签名对象获取注解对象
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);
        //1.3获取数据库操作模型
        OperationType operationType = autoFill.value();


        //2.准备赋值数据
        LocalDateTime now = LocalDateTime.now();//时间
        Long currentId = BaseContext.getCurrentId();//用户id，代表用户


        //3.获取方法参数
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];


        //4.根据不同的操作类型，通过反射进行赋值
        if (operationType == OperationType.INSERT) {
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod("setCreateUser", Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                setCreateTime.invoke(entity, now);
                setUpdateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operationType == OperationType.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

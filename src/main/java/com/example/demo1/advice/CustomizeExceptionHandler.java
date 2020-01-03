package com.example.demo1.advice;

import com.example.demo1.exception.CusomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

   @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model){
       if (ex instanceof CusomizeException) {
           model.addAttribute("message", ex.getMessage());
       }else{
           model.addAttribute("message","服务器太热了，要不你等一下再试吧~~~~");
       }

        return new ModelAndView("error");
    }

}

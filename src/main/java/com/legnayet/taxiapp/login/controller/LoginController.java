package com.legnayet.taxiapp.login.controller;

import com.legnayet.taxiapp.common.MyException;
import com.legnayet.taxiapp.common.Result;
import com.legnayet.taxiapp.login.form.LoginForm;
import com.legnayet.taxiapp.login.service.LoginService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Result<?> getContent(@RequestBody LoginForm loginForm) {
        try {
            return Result.ok(loginService.login(loginForm.getUserName(),loginForm.getPassword()));
        } catch (MyException e) {
            log.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }catch (Exception e) {
            log.error("登录失败", e);
            return Result.error("登录失败");
        }
    }
    @GetMapping("test")
    public Result<?> test() {
        try {
            return Result.ok();
        } catch (Exception e) {
            log.error("test失败", e);
            return Result.error("test失败");
        }
    }
}

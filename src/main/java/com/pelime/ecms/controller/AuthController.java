package com.pelime.ecms.controller;

import com.pelime.ecms.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(String username, String password){
        ModelAndView mv=new ModelAndView();
        String errMsg="";
        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            errMsg=e.getMessage();
        }catch (IncorrectCredentialsException e) {
            errMsg="账号或密码不正确";
        }catch (LockedAccountException e) {
            errMsg="账号已被锁定,请联系管理员";
        }catch (AuthenticationException e) {
            errMsg="账户验证失败";
        }
        if(!errMsg.equals("")){
            mv.setViewName("login");
            mv.addObject("err",errMsg);
            return mv;
        }
        else {
            mv.setViewName("redirect:index");
            return mv;
        }
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login";
    }

}

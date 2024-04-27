package com.example.springmvc.web;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
 @GetMapping("/auth")
 @ResponseBody
 public Authentication authentication(Authentication authentication){
     return  authentication;
 }
}

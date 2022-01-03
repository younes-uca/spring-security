package com.example.msspringsecurity.ws.pub;


import com.example.msspringsecurity.bean.User;
import com.example.msspringsecurity.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userControllerPub")
@RequestMapping("/v1/api/pub/user")
public class UserController {

    @PostMapping("/sign-in/")
    public String signIn(@RequestBody User user) {
        return userService.signIn(user);
    }

    @Autowired
    private UserService userService;
}

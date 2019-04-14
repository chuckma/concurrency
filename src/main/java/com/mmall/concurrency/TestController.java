package com.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Charles Date:2018/3/17
 */
@Controller
@RequestMapping("/bind")
@Slf4j
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }


    @RequestMapping("/list")
    @ResponseBody
    public String list(UserListForm userListForm) {
        return userListForm.toString();
    }


    @RequestMapping("/list2")
    @ResponseBody
    public String list2(@RequestBody(required = false)List<User> users) {
        return users.toString();
    }

    @RequestMapping("/list3")
    @ResponseBody
    public String list3(@RequestBody(required = false)UserListForm users) {
        return users.toString();
    }
}

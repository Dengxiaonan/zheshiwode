package com.lening.manager.controller;

import org.junit.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @date： 2020/8/25
 * @author：Dream
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/name")
    public Map name() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Map map = new HashMap();
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("loginName", loginName);
        System.out.println(map);
        return map;
    }


}

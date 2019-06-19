package net.wanho.controller;

import com.alibaba.fastjson.JSONObject;
import net.wanho.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {


    @Resource
    private UserService userService;

    @RequestMapping("/analyse")

    public JSONObject analyse()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            boolean ret = userService.analyse();
            jsonObject.put("status", 200);
        }
        catch(Exception e)
        {
            jsonObject.put("status", 100);
            e.printStackTrace();
        }
        return jsonObject;
    }

    @RequestMapping("/query")
    public JSONObject query()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = userService.query();
        }
        catch(Exception e)
        {
            jsonObject.put("status", 100);
            e.printStackTrace();
        }
        return jsonObject;
    }
}

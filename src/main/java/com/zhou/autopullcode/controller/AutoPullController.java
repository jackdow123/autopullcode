package com.zhou.autopullcode.controller;

import com.zhou.autopullcode.entity.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/other")
public class AutoPullController {

    @Value("${shPath}")
    String shPath;
    @Value("${debug.token}")
    String token;
    @Value("${appName}")
    String appName;

    @PostMapping
    @ResponseBody
    public CommonResult providHook(@RequestBody String json_input, HttpServletRequest request) {
        // Coding webhook若是设置了token，Coding则对每个请求进行了哈希签名
        // 这个签名会放在请求头 X-Coding-Signature，在服务器端我们需要进行签名解析才能拿到真正数据
        // 从请求头中获取签名
        String signature = request.getHeader("X-Coding-Signature");
        // 进行签名解析
//        HMACSHA1.byte2hex(HMACSHA1.HmacSHA1Encrypt(json_input, token));
        String sha1 = "";
        String calculate_signature = "sha1=".concat(sha1);
        // 进行身份验证
        if (!signature.equals(calculate_signature)) {
            return CommonResult.build(500002, "invalid token");
        }
        //todo ip校验 防止token被知道后恶意访问

        //需要执行的脚本
        String bash = "sh " + shPath + " " + appName;
        Runtime runtime = Runtime.getRuntime();
        //开启线程，执行shell脚本
        new Thread(() -> {
            try {
                runtime.exec(bash);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ).start();
        //请求成功
        return CommonResult.ok();
    }
}

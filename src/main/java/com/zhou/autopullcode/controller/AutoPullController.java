package com.zhou.autopullcode.controller;

import com.zhou.autopullcode.entity.CommonResult;
import com.zhou.autopullcode.service.TokenMappingService;
import com.zhou.autopullcode.utils.HMACSHA1;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/other")
public class AutoPullController {

    @Value("${shPath}")
    String shPath;

    private final TokenMappingService tokenMappingService;

    public AutoPullController(TokenMappingService tokenMappingService) {
        this.tokenMappingService = tokenMappingService;
    }

    @GetMapping
    @ResponseBody
    public CommonResult providHook(@RequestBody String content, HttpServletRequest request) throws Exception {
        //根据请求体获取项目名成
        JSONObject jsonObj = new JSONObject(content);
        JSONObject repoJsonObj = jsonObj.optJSONObject("repository");
        String appName = repoJsonObj.getString("name");
        System.out.println(appName);
        //根据项目名获取对应的token值
        String token = tokenMappingService.findTokenByAppname(appName);
        System.out.println(token);
        // Coding webhook若是设置了token，Coding则对每个请求进行了哈希签名
        // 这个签名会放在请求头 X-Coding-Signature，在服务器端我们需要进行签名解析才能拿到真正数据
        // 从请求头中获取签名
        String signature = request.getHeader("X-Coding-Signature");
        // 进行签名解析
        String calculateSignature = String.format("sha1=%s", HMACSHA1.getSignature(token, content));
        // 进行身份验证
        if (!signature.equals(calculateSignature)) {
            return CommonResult.build(500002, "invalid token");
        }
        //todo ip校验 防止token被知道后恶意访问
        //需要执行的脚本
        String bash = "sh " + shPath + " " + appName;
        Runtime runtime = Runtime.getRuntime();
        //开启线程，执行shell脚本
        Process process = runtime.exec(bash);
        //请求成功
        return CommonResult.ok();
    }


}

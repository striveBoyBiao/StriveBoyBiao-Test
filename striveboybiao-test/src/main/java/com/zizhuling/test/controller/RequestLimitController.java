package com.zizhuling.test.controller;

import com.zizhuling.test.annotation.AccessLimit;
import com.zizhuling.test.utils.WrapperResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *     请求限制次数
 * </P>
 *
 * @author hebiao Create on 2022/11/9 10:20
 * @version 1.0
 */
@Controller
public class RequestLimitController {

    @AccessLimit( seconds = 5,maxCount = 5,needLogin = true)
    @PostMapping("/requestLimit")
    @ResponseBody
    public WrapperResponse requestLimit(){
        return WrapperResponse.success("请求成功");
    }

}

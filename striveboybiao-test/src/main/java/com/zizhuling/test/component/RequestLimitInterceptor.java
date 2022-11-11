package com.zizhuling.test.component;

import com.alibaba.fastjson.JSON;
import com.zizhuling.test.annotation.AccessLimit;
import com.zizhuling.test.dto.UserRequestLimitDTO;
import com.zizhuling.test.utils.Constants;
import com.zizhuling.test.utils.Message;
import com.zizhuling.test.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;

/**
 * <p>
 *     用户请求次数限制--重写拦截器
 * </P>
 *
 * @author hebiao Create on 2022/11/7 16:42
 * @version 1.0
 */
@Component
public class RequestLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求是否属于方法的请求
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            //获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            String key = request.getRequestURI();
            //如果需要登录
            if(login){
                //获取登录的session进行判断
                ////这里假设用户是1,项目中是动态获取的userId
                key = Constants.USER_ID;
            }

            //从redis中获取用户访问的次数
            ValueOperations<String, UserRequestLimitDTO> operations= redisTemplate.opsForValue();
            UserRequestLimitDTO userRequestLimit = operations.get( key );
            //间隔秒数
            int intervalTime = 0;
            if(userRequestLimit != null){
                intervalTime =(int) (System.currentTimeMillis()- userRequestLimit.getRequestTime().getTime())/1000;
            }
            if(userRequestLimit == null||userRequestLimit.getRequestCount()==null) {
                //第一次访问
                UserRequestLimitDTO userRequestLimitOne = new UserRequestLimitDTO( 1, new Date() );
                operations.set( key, userRequestLimitOne );
            }else if(intervalTime>seconds){
                //时间超过，重新设置方法的请求次数
                UserRequestLimitDTO userRequestLimitOne = new UserRequestLimitDTO( 1, new Date() );
                operations.set( key, userRequestLimitOne );
            }else if(userRequestLimit.getRequestCount() < maxCount && intervalTime<=seconds){
                //加1
                userRequestLimit.setRequestCount( userRequestLimit.getRequestCount()+Constants.ONE_INT );
                operations.set( key,userRequestLimit );
            }else{
                //超出访问次数
                render(response, Message.ACCESS_LIMIT_REACHED);
                return false;
            }
        }
        return true;
    }


    /**
     * @description: 输出结果
     * @author: hebiao
     * @param response
     * @param cm
     * @date: 2022/11/9 16:59
     * @return: void
     */
    private void render(HttpServletResponse response, String cm)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString( Result.of().appendSingleObject( cm ));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}

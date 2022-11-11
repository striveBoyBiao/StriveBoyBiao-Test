package com.zizhuling.test.component;


import com.zizhuling.test.utils.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 *
 * @Author hebiao
 * @Date 2022/6/24 14:32
 * @Version 1.0
 */
@Component
public class RedisLockConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockConfig.class);
    @Lazy
    @Resource
    private RedisTemplate redisTemplate;

    @NotNull
    @Bean
    ServletListenerRegistrationBean<ServletContextListener> redisServletListener() {
        ServletListenerRegistrationBean<ServletContextListener> srb =  new ServletListenerRegistrationBean<>();
        srb.setListener(new ImsServletContextListener());
        return srb;
    }


    public class ImsServletContextListener implements ServletContextListener {
        @Override
        public void contextInitialized( ServletContextEvent sce) {
            LOGGER.info("服务启动初始化。。。");
            //删除redis锁
            this.clearRedisLock();
        }

        @Override
        public void contextDestroyed(  ServletContextEvent sce) {
            LOGGER.info("服务停止处理。。。");
            this.clearRedisLock();
        }


        public void clearRedisLock(){
            try {
                LOGGER.info("清理临时锁任务开始。。。。。");
                Set keys = redisTemplate.keys( Constants.USER_ID);
                if(CollectionUtils.isNotEmpty(keys)){
                    redisTemplate.delete(keys);
                }
                LOGGER.info("清理临时锁任务开始。。。。---->>> 结束了");
            } catch (RuntimeException var2) {
                LOGGER.info("清理临时锁任异常!!!!!!!!!!!!!!!!!!!!");
                LOGGER.info("========================================================================");
                LOGGER.info("=========异常信息：" + var2.getMessage());
                LOGGER.info("========================================================================");
            }
        }
    }


}

package com.zizhuling.test.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     用户请求限制DTO
 * </P>
 *
 * @author hebiao Create on 2022/11/9 16:23
 * @version 1.0
 */
public class UserRequestLimitDTO  implements Serializable {
    /**
     * 请求数量
     */
    private Integer requestCount;
    /**
     * 请求时间
     */
    private Date requestTime;

    public UserRequestLimitDTO(Integer requestCount, Date requestTime) {
        this.requestCount = requestCount;
        this.requestTime = requestTime;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString( this );
    }
}

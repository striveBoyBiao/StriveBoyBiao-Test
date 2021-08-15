package com.zizhuling.test.bo.impl;

import com.zizhuling.test.bo.TestBO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *
 * </p>
 *
 * @author hebiao Create on 2021/8/15
 * @version 1.0
 */
public class TestBOImpl implements TestBO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestBOImpl.class);

    @Test
    public void demo(){
        Integer a = 127 ;
        Integer b = 129 ;
        if(b > a){
            LOGGER.info("b-a = "+ (b-a));
        }
    }




}

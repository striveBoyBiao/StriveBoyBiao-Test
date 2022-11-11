package com.zizhuling.test.utils;

/**
 * <p>
 * </P>
 *
 * @author hebiao Create on 2022/11/7 17:41
 * @version 1.0
 */
public class KeyValuePair {
    private static final long serialVersionUID = 2437010671447729724L;
    private String key;
    private Object value;

    public KeyValuePair(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

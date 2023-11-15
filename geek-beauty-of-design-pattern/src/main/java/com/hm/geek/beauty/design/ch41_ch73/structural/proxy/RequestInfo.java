package com.hm.geek.beauty.design.ch41_ch73.structural.proxy;

import java.util.StringJoiner;

/**
 * RequestInfo.
 *
 * @author huwenfeng
 */
@SuppressWarnings("all")
public class RequestInfo {

    private final String action;
    private final long responseTime;
    private final long startTimestamp;
    
    public RequestInfo(String action, long responseTime, long startTimestamp) {
        this.action = action;
        this.responseTime = responseTime;
        this.startTimestamp = startTimestamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestInfo.class.getSimpleName() + "[", "]")
                .add("action='" + action + "'")
                .add("responseTime=" + responseTime)
                .add("startTimestamp=" + startTimestamp)
                .toString();
    }
}

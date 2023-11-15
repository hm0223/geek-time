package com.hm.geek.beauty.design.ch41_ch73.structural.proxy;


import lombok.extern.slf4j.Slf4j;

/**
 * MetricsCollector.
 *
 * @author huwenfeng
 */
@Slf4j
@SuppressWarnings("unused")
public class MetricsCollector {
    public void recordRequest(RequestInfo requestInfo) {
        log.info("MetricsCollector#recordRequest requestInfo is start. {}", requestInfo);
    }
}

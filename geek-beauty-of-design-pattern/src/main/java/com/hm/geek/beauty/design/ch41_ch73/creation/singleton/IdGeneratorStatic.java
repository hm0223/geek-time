package com.hm.geek.beauty.design.ch41_ch73.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGeneratorIdlerMan. 懒汉式
 *
 * @author huwenfeng
 */
public class IdGeneratorStatic {

    private final AtomicLong id = new AtomicLong(0);
    
    public static class IdGeneratorStaticHolder {
        private static final IdGeneratorStatic INSTANCE = new IdGeneratorStatic();
    }

    private IdGeneratorStatic() {
    }

    public static IdGeneratorStatic getInstance() {
        return IdGeneratorStaticHolder.INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}

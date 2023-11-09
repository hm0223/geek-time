package com.hm.geek.beauty.design.ch41_ch73.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGeneratorIdlerMan. 懒汉式
 *
 * @author huwenfeng
 */
public class IdGeneratorIdlerMan {

    private final AtomicLong id = new AtomicLong(0);
    private static IdGeneratorIdlerMan INSTANCE = null;

    private IdGeneratorIdlerMan() {
    }

    public static synchronized IdGeneratorIdlerMan getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IdGeneratorIdlerMan();
        }
        return INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}

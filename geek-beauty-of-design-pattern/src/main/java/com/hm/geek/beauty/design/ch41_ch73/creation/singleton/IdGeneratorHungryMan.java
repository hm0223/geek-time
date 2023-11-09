package com.hm.geek.beauty.design.ch41_ch73.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGeneratorHungryMan. 饿汉式
 *
 * @author huwenfeng
 */
public class IdGeneratorHungryMan {

    private final AtomicLong id = new AtomicLong(0);
    private static final IdGeneratorHungryMan INSTANCE = new IdGeneratorHungryMan();

    private IdGeneratorHungryMan() {
    }

    public static IdGeneratorHungryMan getInstance() {
        return INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}

package com.hm.geek.beauty.design.ch41_ch73.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGenerator2. 双重检查
 *
 * @author huwenfeng
 */
public class IdGeneratorDoubleCheck {

    private final AtomicLong id = new AtomicLong(0);
    private static IdGeneratorDoubleCheck instance = null;

    private IdGeneratorDoubleCheck() {
    }

    public static IdGeneratorDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (IdGeneratorDoubleCheck.class) {
                if (instance == null) {
                    instance = new IdGeneratorDoubleCheck();
                }
            }
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}

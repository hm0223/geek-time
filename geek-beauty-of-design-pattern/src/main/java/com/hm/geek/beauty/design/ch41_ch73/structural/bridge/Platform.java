package com.hm.geek.beauty.design.ch41_ch73.structural.bridge;

/**
 * 对于第一种 GoF 的理解方式，弄懂定义中“抽象”和“实现”两个概念，是理解它的关键。定义中的“抽象”，指的并非“抽象类”或“接口”，而是被抽象出来的一套“类库”，它只包含骨架代码，真正的业务逻辑需要委派给定义中的“实现”来完成。而定义中的“实现”，也并非“接口的实现类”，而是一套独立的“类库”。“抽象”和“实现”独立开发，通过对象之间的组合关系，组装在一起。
 * 对于第二种理解方式，它非常类似我们之前讲过的“组合优于继承”设计原则，通过组合关系来替代继承关系，避免继承层次的指数级爆炸。
 * 平台操作系统 抽象类 Platform.
 *
 * @author huwenfeng
 */
public abstract class Platform {
    /**
     * 这是桥接模式最核心的代码
     * 在 Platform 中通过组合方式关联 Video
     * Platform 的子类也可以关联 Video 子类
     */
    protected Video account;

    public Platform(Video account) {
        this.account = account;
    }

    /**
     * 该方法与 Video 中的方法名相同
     * 方法名相同不是强制的
     *
     * @return
     */
    abstract Video openVideo();
}

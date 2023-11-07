package com.hm.geek.beauty.design.ch34_ch35.refactor1;

/**
 * Main.
 *
 * @author huwenfeng
 */
public class Main {

    public static void main(String[] args) {
        LogTraceIdGenerator logTraceIdGenerator = new RandomIdGenerator();
        String generate = logTraceIdGenerator.generate();
        System.out.println("generate = " + generate);   
    }
}

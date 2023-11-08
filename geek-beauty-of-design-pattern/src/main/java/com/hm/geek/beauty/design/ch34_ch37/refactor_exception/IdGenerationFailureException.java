package com.hm.geek.beauty.design.ch34_ch37.refactor_exception;

/**
 * IdGenerationFailureException.
 *
 * @author huwenfeng
 */
public class IdGenerationFailureException extends RuntimeException {

    private final String code;
    private final String message;

    public IdGenerationFailureException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

    public IdGenerationFailureException(String message, Throwable throwable) {
        super(message, throwable);
        this.code = "500";
        this.message = message;
    }
    
}

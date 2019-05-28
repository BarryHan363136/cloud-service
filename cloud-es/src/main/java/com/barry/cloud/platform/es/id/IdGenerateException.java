package com.barry.cloud.platform.es.id;

/**
 * Created by dubin on 16/12/19.
 */
public class IdGenerateException extends RuntimeException{

    public IdGenerateException() {
        super();
    }

    public IdGenerateException(String message) {
        super(message);
    }

    public IdGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdGenerateException(Throwable cause) {
        super(cause);
    }
}

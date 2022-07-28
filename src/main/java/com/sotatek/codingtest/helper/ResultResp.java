package com.sotatek.codingtest.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ResultResp<T extends Object> implements Serializable {

    private int respCode;

    private String messageCode;

    private T data;

    public ResultResp() {
    }

    public ResultResp(int respCode, T data) {
        this.respCode = respCode;
        this.data = data;
    }

    public ResultResp(HttpStatus httpStatus, T data) {
        this.respCode = httpStatus.value();
        this.messageCode = httpStatus.getReasonPhrase();
        this.data = data;
    }

    public ResultResp(HttpStatus httpStatus) {
        this.respCode = httpStatus.value();
        this.messageCode = httpStatus.getReasonPhrase();
    }

    public ResultResp(int respCode, String messageCode) {
        this.respCode = respCode;
        this.messageCode = messageCode;
    }

    public ResultResp(int respCode, String messageCode, T data) {
        this.respCode = respCode;
        this.messageCode = messageCode;
        this.data = data;
    }
}

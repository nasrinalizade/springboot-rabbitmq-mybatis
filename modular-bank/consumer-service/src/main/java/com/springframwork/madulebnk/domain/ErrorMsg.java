package com.springframwork.madulebnk.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = ErrorMsg.class)
public class ErrorMsg {
    private int errorCode;
    private String errorMessageText;

    public ErrorMsg(int errorCode, String errorMessageText) {
        this.errorCode = errorCode;
        this.errorMessageText = errorMessageText;
    }

    public ErrorMsg() {

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessageText() {
        return errorMessageText;
    }

    public void setErrorMessageText(String errorMessageText) {
        this.errorMessageText = errorMessageText;
    }

    @Override
    public String toString() {
        return "errorMessage{" +
                "errorCode=" + errorCode +
                ", errorMessageText='" + errorMessageText + '\'' +
                '}';
    }
}

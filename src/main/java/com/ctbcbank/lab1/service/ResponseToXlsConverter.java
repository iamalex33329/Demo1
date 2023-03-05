package com.ctbcbank.lab1.service;

import com.ctbcbank.lab1.AjaxResponse;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResponseToXlsConverter extends AbstractHttpMessageConverter<AjaxResponse> {

    /* 針對哪一個class來處理後續的資料，像這裡就是專門處理 AjaxResponse */
    @Override
    protected boolean supports(Class<?> clazz) {
        return (AjaxResponse.class == clazz);
    }

    /* 針對request處理，反序列化 */
    @Override
    protected AjaxResponse readInternal(Class<? extends AjaxResponse> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(AjaxResponse ajaxResponse, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}

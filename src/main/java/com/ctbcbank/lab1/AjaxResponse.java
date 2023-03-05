package com.ctbcbank.lab1;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AjaxResponse {

    private boolean isOK;
    private int code;       // 200, 4XX, 5XX
    private String message;
    private Object data;

    public static AjaxResponse success() {
        AjaxResponse response = new AjaxResponse();
        response.setOK(true);
        response.setCode(200);
        response.setMessage("Request Success");

        return response;
    }

    public static AjaxResponse success(Object obj) {
        AjaxResponse response = new AjaxResponse();
        response.setOK(true);
        response.setCode(200);
        response.setMessage("Request Success");
        response.setData(obj);

        return response;
    }

    public static AjaxResponse success(Object obj, String message) {
        AjaxResponse response = new AjaxResponse();
        response.setOK(true);
        response.setCode(200);
        response.setMessage(message);
        response.setData(obj);

        return response;
    }
}

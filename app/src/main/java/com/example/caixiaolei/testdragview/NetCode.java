package com.example.caixiaolei.testdragview;

import org.json.JSONObject;

/**
 * Created by caixiaolei on 2017/11/3.
 */

public class NetCode {

    public static int STATE_OK = 1;
    public static int STATE_ERROR = 1;

    public static int CODE_OK = 1;
    public static int CODE_ERROR = 1;



    private int state = 0;

    private int code = 0;

    private String message = "";

    private JSONObject jsonObject = null;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}

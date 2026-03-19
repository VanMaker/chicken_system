package com.njau.utils;

public class ErrorUtils extends Exception{
    private String error_id;
    private String error_value;
    private String error_msg;

    public ErrorUtils(String error_id) {
        this.error_id = error_id;
    }

    public ErrorUtils(String error_id, String error_value) {
        this.error_id = error_id;
        this.error_value = error_value;
    }

    @Override
    public String toString() {
        switch (error_id){
            case "property_error":
                error_msg = "\"" + error_value + "\"属性不存在";
        }
        return error_msg;
    }

    //处理视频打开和编辑时的异常

}

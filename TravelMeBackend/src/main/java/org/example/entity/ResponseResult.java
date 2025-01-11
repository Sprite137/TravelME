package org.example.entity;

import lombok.Data;

import java.io.Serializable;



@Data
public class ResponseResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public ResponseResult (int code, String msg, T data){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResponseResult (int code,T data){
        this.code = code;
        this.data = data;
    }

    public ResponseResult (int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult<Object> SuccessResult (Object data){
        ResponseResult<Object> result = new ResponseResult<>(RequestCodeAndMsg.SUCCESS_CODE,RequestCodeAndMsg.SUCCESS_MSG);
        result.setData(data);
        return  result;
    }

    public static ResponseResult<Object> SuccessResult (){
        return new ResponseResult<>(RequestCodeAndMsg.SUCCESS_CODE,RequestCodeAndMsg.SUCCESS_MSG);
    }

    public static ResponseResult<Object> ErrorResult (HttpEnum httpEnum){
        return new ResponseResult<>(httpEnum.code,httpEnum.msg);
    }


}

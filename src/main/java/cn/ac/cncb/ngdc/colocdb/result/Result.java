package cn.ac.cncb.ngdc.colocdb.result;

import lombok.Data;


@Data
public class Result {
    private Integer status;
    private String message;
    private Object data;
    private Meta meta;

    Result(Integer status, String message, Object data,Meta meta) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.meta = meta;
    }
}

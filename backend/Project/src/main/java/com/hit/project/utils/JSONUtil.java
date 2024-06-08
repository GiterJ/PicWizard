package com.hit.project.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONUtil {
    private Integer code;
    private String msg;

    public static JSONUtil getOK(int code, String msg) {
        return new JSONUtil(code, msg);
    }

    public static JSONUtil getERROR(int code, String msg) {
        return new JSONUtil(code, msg);
    }




}

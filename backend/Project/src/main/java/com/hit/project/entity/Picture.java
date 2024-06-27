package com.hit.project.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * picture
 * @author 
 */
public class Picture implements Serializable {
    private Integer id;

    private String name;

    private LocalDateTime time;

    private String func;

    private String base64;
    public Picture(Integer id, String base64, String name, LocalDateTime time, String func) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.func = func;
        this.base64 = base64;
    }
    public Picture(String name, LocalDateTime time, String func, String base64) {
        this.name = name;
        this.time = time;
        this.func = func;
        this.base64 = base64;
    }



    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
package com.example.todo;

import android.os.Build;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TodoItem implements Serializable {

    private int id;
    private String header;
    private String desc;

    public TodoItem(String header, String desc) {
        this.id = -1; // By default, it will be changed when the item is put into storage.
        this.header = header;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getDesc() {
        return desc;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

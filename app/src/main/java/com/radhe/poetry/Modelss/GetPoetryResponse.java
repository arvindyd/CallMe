package com.radhe.poetry.Modelss;

import java.util.List;

public class GetPoetryResponse {

    String status;
    String message;
   List<Poetry> data;

    public GetPoetryResponse(String status, String message, List<Poetry> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Poetry> getData() {
        return data;
    }

    public void setData(List<Poetry> data) {
        this.data = data;
    }
}

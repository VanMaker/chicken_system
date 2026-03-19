package com.njau.entity;

public class UploadResp {
    public boolean success;
    public String fileId;
    public String url;
    public String savedPath;
    public String message;

    public UploadResp ok(String fileId, String url, String savedPath, String message) {
        UploadResp r = new UploadResp();
        r.success = true;
        r.fileId = fileId;
        r.url = url;
        r.savedPath = savedPath;
        r.message = message;
        return r;
    }

    public UploadResp fail(String msg) {
        UploadResp r = new UploadResp();
        r.success = false;
        r.message = msg;
        return r;
    }
}

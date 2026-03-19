package com.njau.utils;

import com.njau.entity.UploadResp;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Paths;

public class PhotoUploader {
    private RestTemplate restTemplate;

    public PhotoUploader() {
        this.restTemplate = new RestTemplate();
        // 可选：设置超时（生产必须）
//         HttpComponentsClientHttpRequestFactory f = new HttpComponentsClientHttpRequestFactory();
//         f.setConnectTimeout(5000);
//         f.setReadTimeout(30000);
//         this.restTemplate = new RestTemplate(f);
    }

    public UploadResp upload(String cloudUrl, String filePath) throws IOException {
        FileSystemResource file = new FileSystemResource(Paths.get(filePath).toFile());
        if (!file.exists()) throw new FileNotFoundException(filePath);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);
//        if (metaJson != null && !metaJson.isBlank()) body.add("meta", metaJson);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<UploadResp> resp = restTemplate.postForEntity(
                cloudUrl, requestEntity, UploadResp.class
        );

        if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
            throw new IOException("upload failed, status=" + resp.getStatusCode());
        }
        return resp.getBody();
    }


}

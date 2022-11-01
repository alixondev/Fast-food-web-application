package com.example.appdbservice.service;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.AttachmentDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request);

    void downloadDb(Long id, boolean inline, HttpServletResponse response);

}

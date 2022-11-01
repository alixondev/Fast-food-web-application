package com.example.appdbservice.controller;

import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.AttachmentDTO;
import com.example.appdbservice.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {
    private final AttachmentService attachmentService;

    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadDb(request);
    }

    @Override
    public void download(Long id, boolean inline, HttpServletResponse response) {
        attachmentService.downloadDb(id, inline, response);
    }
}

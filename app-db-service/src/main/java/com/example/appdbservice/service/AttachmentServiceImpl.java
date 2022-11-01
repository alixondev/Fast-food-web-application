package com.example.appdbservice.service;

import com.example.appdbservice.entity.product.Attachment;
import com.example.appdbservice.entity.product.AttachmentContent;
import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.payload.ApiResult;
import com.example.appdbservice.payload.AttachmentDTO;
import com.example.appdbservice.repository.product.AttachmentContentRepository;
import com.example.appdbservice.repository.product.AttachmentRepository;
import com.example.appdbservice.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Override
    @Transactional
    public ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request) {

        long millis = System.currentTimeMillis();

        try {

            Iterator<String> fileNames = request.getFileNames();

            MultipartFile multipartFile = request.getFile(fileNames.next());

            String contentType = multipartFile.getContentType();//  = image/png

            String originalFilename = multipartFile.getOriginalFilename();

            long size = multipartFile.getSize();

            byte[] bytes = multipartFile.getBytes();

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType
            );

            attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    bytes,
                    attachment
            );

            attachmentContentRepository.save(attachmentContent);

            String url = AppConstant.ATTACHMENT_CONTROLLER_PATH
                    + AppConstant.DOWNLOAD_PATH +
                    "/" + attachment.getId();

            AttachmentDTO attachmentDTO = new AttachmentDTO(
                    attachment.getId(),
                    attachment.getOriginalName(),
                    attachment.getSize(),
                    attachment.getContentType(),
                    url
            );

            System.err.println("Upload db => " + (System.currentTimeMillis() - millis));
            return ApiResult.successResponse(attachmentDTO);

        } catch (IOException e) {
            e.printStackTrace();
            throw RestException.maker("Fayl yuklashda xatolik", HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public void downloadDb(Long id, boolean inline, HttpServletResponse response) {
        long millis = System.currentTimeMillis();
        try {
            Attachment attachment = getAttachmentByIdOrElseThrow(id);

            AttachmentContent attachmentContent = getAttachmentContentByIdOrElseThrow(attachment.getId());

            String disposition = inline ? "inline" : "attachment";

            response.setHeader("Content-Disposition",
                    disposition + "; filename=\"" + attachment.getOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            response.setHeader("Cache-Control", "max-age=8640000");
            response.setContentLength((int) attachment.getSize());

            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());

            System.err.println("Download db => "+(System.currentTimeMillis() - millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Attachment getAttachmentByIdOrElseThrow(Long id) {
        return attachmentRepository.findById(id).orElseThrow(()
                -> RestException.notFound("Attachment"));
    }

    public AttachmentContent getAttachmentContentByIdOrElseThrow(Long id) {
        return attachmentContentRepository.findByAttachmentId(id).orElseThrow(()
                -> RestException.notFound("Attachment content"));
    }

}

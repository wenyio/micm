package com.iscolt.micm.cloud.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.iscolt.micm.cloud.dto.FileInfo;
import com.iscolt.micm.commons.dto.ResponseResult;
import com.iscolt.micm.commons.model.dto.ResponseCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传服务
 * <p>
 * Description:
 * </p>
 *
 * @author: https://github.com/isColt
 * @date: 4/1/2020
 * @see: com.iscolt.micm.cloud.controller
 * @version: v1.0.0
 */
@RestController
@RequestMapping(value = "cloud/upload")
public class UploadController {

    // TODO 阿里ACCESS_KEY等丢到yml中去
    private static final String ENDPOINT = "oss-cn-shanghai.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "xx";
    private static final String ACCESS_KEY_SECRET = "xx";
    private static final String BUCKET_NAME = "micm";

    /**
     * 文件上传
     *
     * @param multipartFile @{code MultipartFile}
     * @return {@link ResponseResult<FileInfo>} 文件上传路径
     */
    @PostMapping(value = "")
    public ResponseResult<FileInfo> upload(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;

        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        try {
            client.putObject(new PutObjectRequest(BUCKET_NAME, newName, new ByteArrayInputStream(multipartFile.getBytes())));
            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            return new ResponseResult<FileInfo>(ResponseCode.OK.code(), "文件上传成功", new FileInfo("http://" + BUCKET_NAME + "." + ENDPOINT + "/" + newName));
        } catch (IOException e) {
            return new ResponseResult<FileInfo>(ResponseCode.FAIL.code(), "文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }

}

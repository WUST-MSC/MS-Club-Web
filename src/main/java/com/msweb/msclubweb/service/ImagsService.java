package com.msweb.msclubweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msweb.msclubweb.controller.Result;
import com.msweb.msclubweb.domain.Imags;
import org.springframework.web.multipart.MultipartFile;

public interface ImagsService extends IService<Imags> {
    //添加图片
    Result addImg (MultipartFile file);
}

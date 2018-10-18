package com.zac.spring.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zac.spring.entity.Image;
import com.zac.spring.entity.Product;
import com.zac.spring.repository.ImageRepository;

/**
 * Created by Zac
 */
@Service
public class ImageService {
  final static Logger logger = LoggerFactory.getLogger(ImageService.class);

  private ImageRepository imageRepository;

  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

//  @Cacheable("cache.allImages")
  public List<Image> findAll() {
    return imageRepository.findAll();
  }
  
//  @CacheEvict(value = {"cache.imageById"}, allEntries = true)
  public void createOne(Image image) {
    Date nowDate = new Date();
    image.setCreateDate(nowDate);
    image.setUpdateDate(nowDate);
    imageRepository.save(image);
  }
  
  public void deleteById(Integer id) {
    imageRepository.deleteById(id);
  }
  
  /*
  * 将Response修改为图片流
  * @param response 将被修改的原生HttpServletResponse
  * @param id 图片的id
  */
 public void showImage(HttpServletResponse response,Integer id){
     Image image = imageRepository.getOne(id);
     String name = image.getImageName().toLowerCase();
     if(name.contains(".jpg")){
         response.setContentType("image/jpeg");
     }
     else if(name.contains(".gif")){
         response.setContentType("image/gif");
     }
     else {
         response.setContentType("image/png");
     }

     try {
         OutputStream outputStream = response.getOutputStream();
         InputStream inputStream = new ByteArrayInputStream(image.getImageBlob()); 
         IOUtils.copy(inputStream,outputStream);
         outputStream.flush();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}

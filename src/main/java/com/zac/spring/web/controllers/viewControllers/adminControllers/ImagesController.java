package com.zac.spring.web.controllers.viewControllers.adminControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @version V1.0
 * @Author fendo
 * @ClassName ImagesController
 * @Description 图片 controller
 * @Data 20181011
 **/
@RequestMapping("images")
@Controller
public class ImagesController {

  final static Logger logger = LoggerFactory.getLogger(ImagesController.class);

  /**
   *
   * @Title getIcons
   * @Description: 把本地的图片以流的形式输出到页面中去
   * @Author fendo
   * @Date 20181011
   * @param
   * @return void
   * @throws @测试地址:
   *           localhost:8080/images/geticons
   */
  @RequestMapping(value = "geticons", method = RequestMethod.GET)
  public void getIcons(HttpServletRequest request,
      HttpServletResponse response) {
    try {
      Resource resource = new ClassPathResource(
          "image/jsa_about_img_blue_background.png");
      String fileName = resource.getFilename();
      System.out.println(fileName);
      if (resource != null && resource.exists()) {
        if (resource.isReadable()) {
          InputStream is = resource.getInputStream();
          int i = is.available(); // 得到文件大小
          byte data[] = new byte[i];
          is.read(data); // 读数据
          is.close();
          response.setContentType("image/*"); // 设置返回的文件类型
          OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
          toClient.write(data); // 输出数据
          toClient.close();
        }
      }
//      FileInputStream is = new FileInputStream(
//          "image/jsa_about_img_blue_background.png");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * @Title getImages
   * @Description: 根据用户表的的imgguid去FORMBLOB中获取图片
   * @Author fendo
   * @Date 20181011
   * @param guid
   * @return void
   * @throws @测试地址:
   *           localhost:8996/GZ/images/getimages?guid=269A037ADE14FE02E050AE0AC684ADFB
   */
  // @RequestMapping(value = "getimages", method = RequestMethod.GET)
  // public void getImages(String guid, HttpServletRequest request,
  // HttpServletResponse response) {
  //
  // ServletOutputStream out = null;
  //
  // if (StringUtils.isNotBlank(guid)) {
  // try {
  //
  // // 根据id去图片表获取数据
  // FromBlob fromBlob = fromBlobMapper.selectByPrimaryKey(guid);
  //
  // // 获取blob字段
  // byte[] contents = fromBlob.getBdata();
  // System.out.println("内容是:" + contents.length);
  // InputStream is = new ByteArrayInputStream(contents);
  // response.setContentType("image/*");
  // out = response.getOutputStream();
  // int len = 0;
  // byte[] buf = new byte[1024];
  // while ((len = is.read(buf, 0, 1024)) != -1) {
  // out.write(buf, 0, len);
  // }
  //
  // out.flush();
  // out.close();
  // } catch (IOException e) {
  // logger.equals(e.toString());
  // e.printStackTrace();
  // }
  // }
  // }

}
package com.zac.spring.web.controllers.viewControllers.adminControllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zac.spring.entity.Image;
import com.zac.spring.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhuyst on 2017/6/9.
 * 主页的Controller
 */
@Controller
@RequestMapping("/adminPage")
public class ImageController {
  private static final String IMAGE_INDEX = "adminPage/image/index";
  private static final String REDIRECT_DIR = "redirect:/adminPage/images";

    @Autowired
    private ImageService imageService;

//    @GetMapping("/images")
//    public ModelAndView showImages() {
//        ModelAndView modelAndView = new ModelAndView(IMAGE_INDEX);
////        List<Product> list = productService.findAll();
//        modelAndView.addObject("list", imageService.findAll());
//        return modelAndView;
//    }
    
    @RequestMapping("/images")
    public String index(Model model, HttpSession session){
        List<Image> images = imageService.findAll();
        Long id = (Long) session.getAttribute("id");
        String msg = (String) session.getAttribute("msg");

        if(id != null){
            model.addAttribute("id",id);
            session.removeAttribute("id");
        }
        if(msg != null){
            model.addAttribute("msg",msg);
            session.removeAttribute("msg");
        }

        model.addAttribute("list",images);
        return IMAGE_INDEX;
    }

    @RequestMapping(value = "/image/upload",method = RequestMethod.POST)
    public String upload(HttpSession session, @RequestParam("image")MultipartFile[] images){
        String msg = "上传成功";
        Boolean flag = true;

        for(MultipartFile imageData : images){
            if(imageData.getSize() > 5120000){
                msg = "上传文件过大";
                flag = false;
                break;
            }
            String name = imageData.getOriginalFilename().toLowerCase();
            if(!(name.contains(".jpg") || name.contains(".gif") || name.contains(".png"))){
                msg = "上传的文件不是图片";
                flag = false;
                break;
            }

            Image image = new Image();
            image.setImageName(imageData.getOriginalFilename());
//            image.setImageType(name);
            try {
              image.setImageBlob(imageData.getBytes());
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            imageService.createOne(image);
        }
        if(flag){
//            HashMap<String,byte[]> map = FileUploadUtil.upload(images);
//            if(!imageService.insertImage(map)){
//                msg = "上传失败";
//            }
        }

        session.setAttribute("msg",msg);
        return REDIRECT_DIR;
    }

    @RequestMapping(value = "/image/showImage",method = RequestMethod.GET)
    public void showImage(HttpServletResponse response,Integer id){
        imageService.showImage(response,id);
    }

    @RequestMapping("/image/delete")
    public String deleteImage(HttpSession session,Integer id){
        String msg = "删除成功";
        imageService.deleteById(id);
//        if(imageService.deleteImage(id)){
//            msg = "删除成功";
//        }
//        else msg = "删除失败";

        session.setAttribute("msg",msg);
        return REDIRECT_DIR;
    }
}

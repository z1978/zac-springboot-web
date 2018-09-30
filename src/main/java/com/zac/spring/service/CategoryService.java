package com.zac.spring.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zac.spring.entity.Category;
import com.zac.spring.repository.CategoryRepository;
import com.zac.spring.repository.ImageModel;
import com.zac.spring.repository.ImageRepository;

/**
 * Created by Zac
 */
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

  //region Find methods
    //==================================================================================
    @Cacheable("cache.allCategorys")
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    @Cacheable(value = "cache.categoryByName", key = "#name", unless = "#result == null")
    public Category findByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Category getCategoryById(Integer id){
        return categoryRepository.getOne(id);
    }

    @Cacheable(value = "cache.categoryById", key = "#id", unless = "#result == null")
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }
    
    @Cacheable(value = "cache.parentCategoryId", key = "#id", unless = "#result == null")
    public List<Category> findByParentCategoryId(Integer id) {
        return categoryRepository.findByParentCategoryId(id);
    }
	
	@Autowired
	ImageRepository imageRepository;
	public void testRun() throws Exception {
		// image 1
		ClassPathResource backImgFile = new ClassPathResource("image/jsa_about_img_black_background.png");
		byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
		backImgFile.getInputStream().read(arrayPic);
		ImageModel blackImage = new ImageModel(1, "JSA-ABOUT-IMAGE-BLACK-BACKGROUND", "png", arrayPic);
		
		// image 2
		ClassPathResource blueImgFile = new ClassPathResource("image/jsa_about_img_blue_background.png");
		arrayPic = new byte[(int) blueImgFile.contentLength()];
		blueImgFile.getInputStream().read(arrayPic);
		ImageModel blueImage = new ImageModel(2, "JSA-ABOUT-IMAGE-BLUE-BACKGROUND", "png", arrayPic);
		
		// store image to MySQL via SpringJPA
		imageRepository.save(blackImage);
		imageRepository.save(blueImage);
		
		// retrieve image from MySQL via SpringJPA
		for(ImageModel imageModel : imageRepository.findAll()){
			Files.write(Paths.get("retrieve-dir/" + imageModel.getName() + "." + imageModel.getType()), imageModel.getPic());
		}
	}
	
}

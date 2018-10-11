package com.zac.spring.service;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zac.spring.entity.Product;
import com.zac.spring.repository.ProductRepository;
import com.zac.spring.web.controllers.viewControllers.ZacController;

/**
 * Created by Zac
 */
@Service
public class ProductService {
  
  final static Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

  //region Find methods
    //==================================================================================
    @Cacheable("cache.allProducts")
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    @Cacheable(value = "cache.productByName", key = "#name", unless = "#result == null")
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
    
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Product getProductById(Integer id){
        return productRepository.getOne(id);
    }

    @Cacheable(value = "cache.productById", key = "#id", unless = "#result == null")
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
    
    @Cacheable(value = "cache.productByCategoryId", key = "#id", unless = "#result == null")
    public List<Product> findByCategoryId(Integer id) {
        return productRepository.findByCategoryId(id);
    }
    //==================================================================================
    //endregion

    @CacheEvict(value = {"cache.allProducts" , "cache.productByName", "cache.productById"}, allEntries = true)
    public void deleteById(Integer id) {
    	productRepository.deleteById(id);
    }
    
    @CacheEvict(value = {"cache.allProducts" , "cache.productByName", "cache.productById"}, allEntries = true)
    public void createOne(Product product) {
//    	product.setProductId(99);
    	short s;
    	s = 1;
    	product.setStatus(s);
    	product.setDelFlg(s);
    	Date nowDate = new Date();
    	product.setCreateDate(nowDate);
    	product.setUpdateDate(nowDate);
        productRepository.save(product);
    }
    
    @CacheEvict(value = {"cache.allProducts" , "cache.productByName", "cache.productById"}, allEntries = true)
    public void updateOne(Product product) {
    	Date nowDate = new Date();
    	product.setUpdateDate(nowDate);
        productRepository.save(product);
    }

    public boolean checkIfProductNameIsTaken(List<Product> allProducts, Product product, Product persistedProduct) {
        boolean productNameAlreadyExists = false;
        for (Product product1 : allProducts) {
            //Check if the product name is edited and if it is taken
            if (!product.getName().equals(persistedProduct.getName())
                    && product.getName().equals(product1.getName())) {
                productNameAlreadyExists = true;
            }
        }
        return productNameAlreadyExists;
    }
    
    private final static ByteBuffer PNG_HEADER_8_BYTE = ByteBuffer
        .wrap(new byte[] { (byte) 137, 80, 78, 71, 13, 10, 26, 10 });
    
    public boolean isPngBinary(byte[] binary) {
      try {
        return PNG_HEADER_8_BYTE.equals(ByteBuffer.wrap(binary, 0, 8));
      } catch (IndexOutOfBoundsException | NullPointerException e) {
        logger.error(LogMessageBuilder
            .message("binary图像校验失败。binary={0}", Arrays.toString(binary))
            .cause("可能有意改变图像数据")
            .effect("不能登录图像")
            .solution("频繁发生时可能是被攻击啦")
            .build());
        return false;
      }
    }
    
    public byte[] getImageBinary(String encodedImageBase64) {
      try {
        return Base64
            .decodeBase64(encodedImageBase64.split(";")[1].split(",")[1]);
      } catch (IndexOutOfBoundsException | NullPointerException e) {
        logger.error(LogMessageBuilder
            .message("图像变换失败encodedString={0}", encodedImageBase64)
            .cause("可能有意改变图像数据")
            .effect("不能登录图像")
            .solution("频繁发生时可能是被攻击啦")
            .build());
        return null;
      }
    }
}

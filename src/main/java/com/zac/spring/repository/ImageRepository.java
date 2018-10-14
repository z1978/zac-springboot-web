package com.zac.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zac.spring.entity.Image;

/**
 * Created by Zac
 */
public interface ImageRepository extends JpaRepository<Image, Integer>, JpaSpecificationExecutor<Image>  {
	List<Image> findByImageType(int imageType);
}

package com.zac.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * The persistent class for the dtb_product database table.
 * 
 */
@Entity
@Table(name = "t_image")
public class Image extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "image_id")
  private int imageId;

  @Column(name = "image_name")
  private String imageName;

  @Column(name = "image_type")
  private String imageType;
  
  @Lob
  @Column(name = "image_blob", columnDefinition="BLOB")
  private byte[] imageBlob;

  @Column(name = "product_id")
  private int productId;

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  public byte[] getImageBlob() {
    return imageBlob;
  }

  public void setImageBlob(byte[] imageBlob) {
    this.imageBlob = imageBlob;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
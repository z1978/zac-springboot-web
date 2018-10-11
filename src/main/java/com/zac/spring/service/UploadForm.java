package com.zac.spring.service;

import java.io.Serializable;

public class UploadForm implements Serializable {

  private static final long serialVersionUID = 1L;

  public String imageFile;

  public Integer imageVariety;

  public Integer previous;

  private Integer productId;

  public String getImageFile() {
    return imageFile;
  }

  public void setImageFile(String imageFile) {
    this.imageFile = imageFile;
  }

  public Integer getImageVariety() {
    return imageVariety;
  }

  public void setImageVariety(Integer imageVariety) {
    this.imageVariety = imageVariety;
  }

  public Integer getPrevious() {
    return previous;
  }

  public void setPrevious(Integer previous) {
    this.previous = previous;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

}
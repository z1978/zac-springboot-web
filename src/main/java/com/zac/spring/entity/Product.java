package com.zac.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the dtb_product database table.
 * 
 */
@Entity
@Table(name="dtb_product")
public class Product extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private int productId;

	@Column(name="category_id")
	private int categoryId;

	@Column(name="creator_id")
	private int creatorId;

	@Column(name="del_flg")
	private short delFlg;

	@Lob
	@Column(name="description_detail")
	private String descriptionDetail;

	@Lob
	@Column(name="description_list")
	private String descriptionList;

	@Lob
	@Column(name="free_area")
	private String freeArea;

	@Lob
	@Column(name="name")
    @NotBlank (message = "Name is required")
	private String name;

	@Lob
	@Column(name="note")
	private String note;

	@Lob
	@Column(name="search_word")
	private String searchWord;

	@Column(name="status")
	private short status;

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public int getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
  }

  public short getDelFlg() {
    return delFlg;
  }

  public void setDelFlg(short delFlg) {
    this.delFlg = delFlg;
  }

  public String getDescriptionDetail() {
    return descriptionDetail;
  }

  public void setDescriptionDetail(String descriptionDetail) {
    this.descriptionDetail = descriptionDetail;
  }

  public String getDescriptionList() {
    return descriptionList;
  }

  public void setDescriptionList(String descriptionList) {
    this.descriptionList = descriptionList;
  }

  public String getFreeArea() {
    return freeArea;
  }

  public void setFreeArea(String freeArea) {
    this.freeArea = freeArea;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getSearchWord() {
    return searchWord;
  }

  public void setSearchWord(String searchWord) {
    this.searchWord = searchWord;
  }

  public short getStatus() {
    return status;
  }

  public void setStatus(short status) {
    this.status = status;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
	
	
}
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
@Table(name = "t_contact")
public class Contact extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private int contactId;

	@Lob
	@Column(name = "your_name")
	@NotBlank (message = "Name is required")
	private String yourName;

	@Lob
	@Column(name = "your_email")
	@NotBlank (message = "Email is required")
	private String yourEmail;

	@Lob
	@Column(name = "your_message")
	@NotBlank (message = "Message is required")
	private String yourMessage;

  public int getContactId() {
    return contactId;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

  public String getYourName() {
    return yourName;
  }

  public void setYourName(String yourName) {
    this.yourName = yourName;
  }

  public String getYourEmail() {
    return yourEmail;
  }

  public void setYourEmail(String yourEmail) {
    this.yourEmail = yourEmail;
  }

  public String getYourMessage() {
    return yourMessage;
  }

  public void setYourMessage(String yourMessage) {
    this.yourMessage = yourMessage;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
	
	
}
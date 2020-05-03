package com.sip.ams.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Provider {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "Nom obligatoire")
	@Column(name = "name")
	private String name;
	@NotBlank(message = "Addresse obligatoire")
	@Column(name = "address")
	private String address;
	@NotBlank(message = "Email obligatoire")
	@Column(name="email")
	private String email;
	
	
	
	
	public long getId() {
		return id;
	}
	public Provider(String name,
			String address,
			 String email) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
	}
	public Provider() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
	public List<Article> articles;




	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	
	

}

package com.sip.ams.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Article {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "label obligatoire")
	@Column(name = "label")
	private String label;
	@Column(name = "price")
	private float price;
	@Column(name = "picture")
	private String picture;
	
	
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	

	public Article( String label, float price, String picture) {
		super();
		this.label = label;
		this.price = price;
		this.picture = picture;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)    /* * LAZY : on charge l'entité associée seulement quand elle est accédée pour la * première fois (chargement à la demande) */	
	 @JoinColumn(name = "provider_id", nullable = false) /* cle etrangere de entite provider */
	 @OnDelete(action = OnDeleteAction.CASCADE) /** If you use @OnDelete then deleting the provider will also delete the * article.*/
																					 
	private Provider provider; /* importer objet provider de la classe Privider */



	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	 
	
	
	
	
	
	
	

}

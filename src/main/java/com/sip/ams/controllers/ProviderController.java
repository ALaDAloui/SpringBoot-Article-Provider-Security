package com.sip.ams.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	
	private final ProviderRepository providerRepositery; /* definir attribut */
	@Autowired
	public ProviderController (ProviderRepository providerRepository) {
		this.providerRepositery = providerRepository ;
		
	}
	
	@GetMapping("list")
	public String listProviders(Model model) {
		model.addAttribute("providers" , providerRepositery.findAll());
		return "provider/listProviders";	
		}
	
	@GetMapping("add")
	public String showAddProviderForm(Provider provider) {
		return "provider/addProvider";
		
	}
	
	
	@PostMapping("add")
	public String addProvider(@Valid Provider provider ,BindingResult result, Model model  ) {
		if (result.hasErrors()){
			return "provider/addProvider"; 
			
		}
		providerRepositery.save(provider);
		return "redirect:list";
	}
	
	
	@GetMapping("delate/{id}")
	public String delateProvider(@PathVariable("id") long id ,Model model) {
		Provider provider = providerRepositery.findById(id)                                    /* chercher le fournisseur a supprimer par id*/
				.orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));   /* sil nexiste pas => message invalid*/
		
		providerRepositery.delete(provider);                          /*supprimer provider*/
		model.addAttribute("providers", providerRepositery.findAll()); /*ajouter tt les fournisseurs qui reste dans le model*/
		return "provider/listProviders";           /* affichier listProviders */
	}
	
	
	 @GetMapping("edit/{id}") 
	 public String showProviderFormToUpdate(@PathVariable("id") long id, Model model) {   
		 
		 Provider provider = providerRepositery.findById(id)    
				 .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id)); 
		 
		 model.addAttribute("provider", provider);
		 
		 return"provider/updateProvider";
	 }
	 
	 @PostMapping("update/{id}") 
	 public String updateProvider(@PathVariable("id") long id, @Valid Provider provider, BindingResult result,Model model) {
		 if (result.hasErrors()){
			 provider.setId(id);
				return "provider/updateProvider";
				
			}
		  providerRepositery.save(provider) ;
		  model.addAttribute("providers", providerRepositery.findAll());
		  return "provider/listProviders";
	 
	 }
   
	
	@GetMapping("show/{id}")
	public String showProvider(@PathVariable("id") long id, Model model) {
		
		Provider provider=providerRepositery.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
				
		List<Article> articles = providerRepositery.findArticlesByProvider(id);
		for (Article a : articles)
			System.out.println("Article = " + a.getLabel());
			model.addAttribute("articles", articles);
			model.addAttribute("provider", provider);
			return "provider/ showProvider";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package com.sip.ams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;


@Controller
@RequestMapping("/article")
public class ArticleController {
	
	
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	
	
	private final ArticleRepository articleRepository ;
	private final ProviderRepository providerRepository;
	
	@Autowired
	public ArticleController (ArticleRepository articleRepository , ProviderRepository providerRepository) {
		this.articleRepository = articleRepository;
		this.providerRepository =providerRepository;
		
	}
	
	@GetMapping("list")
	public String listArticle (Model model) {
		model.addAttribute("articles", articleRepository.findAll());
		return "article/listArticles" ;
	}
	
	@GetMapping("show/{id}")
	public String ShowArticleDetails (@PathVariable("id") long id ,Model model) {
		
		Article article =articleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
		
				

		
		model.addAttribute("articles",article);
		
		
		return "article/showArticle";
	}
	
	
	@GetMapping("add")
	public String showAddArticleform (Article article,Model model) {
		
		model.addAttribute("providers",providerRepository.findAll());
		model.addAttribute("articles" ,new Article());
	return "article/addArticles";
	}
	
	@PostMapping("add")
	public String addArticle (@Valid Article article, BindingResult result , 
			@RequestParam(name = "providerId", required = false) Long p, @RequestParam("files") MultipartFile[] files) {
		
		Provider provider=providerRepository.findById(p).orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + p));
		
		article.setProvider(provider);
		/* part upload*/

		 StringBuilder fileName = new StringBuilder();
		 MultipartFile file = files[0];
		 Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
		

		 fileName.append(file.getOriginalFilename());
		 try {
		Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
		e.printStackTrace();
		}

		article.setPicture(fileName.toString());
		 articleRepository.save(article);
		
		
		
		
		
		
		articleRepository.save(article);
		
		return "redirect:list";
	}     /* The BindingResult has to follow the object that is bound. The reason is that if you have more objects that are bound you must know which BindingResult belongs to which object.*/
	
	@GetMapping("edit/{id}")
	public String showeditArticle (@PathVariable("id") long id ,Model model ) {
		
		Article article = articleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
				 /* * 5ater get ==> te5ou l page html chfeha melowel (feha article bil prix * wel label w provider bil id mte3ou )' */	
												
												 
													
		model.addAttribute("articles",article);                              /* l page html bich twari l'article */
		model.addAttribute("providers" , providerRepository.findAll());        /* w l provider*/
		model.addAttribute("idProvider", article.getProvider().getId()); /* provider te5ouh bil id mte3ou */
		
		return "article/updateArticles";
	}
	
	
	@PostMapping("edit/{id}")
	public String editArticle (@PathVariable("id") long id ,@Valid Article article, BindingResult result , Model model ,
			@RequestParam(name = "providerId", required = false) Long p) {
		
		if(result.hasErrors()) {
			article.setId(id);
		
		return "article/updateArticles";	
		}
		
	    Provider provider =providerRepository.findById(p).orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + p));
	    		
	    
	    article.setProvider(provider);
		articleRepository.save(article);
		model.addAttribute("articles" , articleRepository.findAll());
			return "article/listArticles";
	}
	
	
	@GetMapping("delate/{id}")
	public String delateArticle (@PathVariable ("id") long id, Model model    ) {
		
		Article article=articleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
		
		
		articleRepository.delete(article);		
		model.addAttribute("articles" , articleRepository.findAll());
		
		return "article/listArticles";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

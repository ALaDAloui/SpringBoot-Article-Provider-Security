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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.sip.ams.entities.User;
import com.sip.ams.repositories.UserRepository;


@Controller
public class MainController {
	
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	
	private final UserRepository userRepository;
	@Autowired
	public MainController (UserRepository userRepository) {
		this.userRepository= userRepository;
		
	}

	
	@GetMapping("/")
    public String root() {
		
        return "index";
    }
	
	@GetMapping("showd")
	public String addPicture ( Model model){ 
	
		model.addAttribute("users",userRepository.findAll());
		
		return "details";
		
	}
	
	@PostMapping("showd")
	public String addProfilePicture(@PathVariable("email") String email,@Valid User user, BindingResult result,@RequestParam("files") MultipartFile[] files
			,Model model) {
		
		if(result.hasErrors()) {
			user.setEmail(email);
		
		return "details";	
		}
		User user1=userRepository.findByEmail(email);

		 StringBuilder fileName = new StringBuilder();
		 MultipartFile file = files[0];
		 Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
		

		 fileName.append(file.getOriginalFilename());
		 try {
		Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
		e.printStackTrace();
		}
		 user1.setProfilePicture(fileName.toString());
		 userRepository.save(user1);
		 model.addAttribute("users",userRepository.findByEmail(email));
		 
		 return "redirect:showdetails";
		/*en cour de dev*/
		
	}
	
	
	
	
	

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
	
}

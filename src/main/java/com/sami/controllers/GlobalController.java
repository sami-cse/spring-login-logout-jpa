package com.sami.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sami.repository.PermissionRepository;
import com.sami.repository.UserRepository;

@ControllerAdvice
public class GlobalController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PermissionRepository permissionRepository;

	@ModelAttribute("permissions")
	public void getUserRole(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		model.addAttribute("userId", userId);
		
		System.out.println("*************************************************");
		System.out.println("User ID From Session :"+userId);
		System.out.println("*************************************************");
		
		model.addAttribute("permissions", permissionRepository.userAccessByuserId(userId));
		

	}

}

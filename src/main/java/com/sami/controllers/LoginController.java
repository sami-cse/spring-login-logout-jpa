package com.sami.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sami.models.User;
import com.sami.repository.PermissionRepository;
import com.sami.repository.RoleRepository;
import com.sami.repository.UserRepository;
import com.sami.validator.LoginValidator;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	RoleRepository roleRepository;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("action", "/login");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(Model model, @ModelAttribute("user") User user, HttpSession session, BindingResult result) {

		LoginValidator loginValidator = new LoginValidator();
		loginValidator.validate(user, result);
		if (result.hasErrors()) {
			return "login";
		}

		model.addAttribute("action", "/login");
		if (userRepository.findByNameAndPasswordAndId(user.getName(), user.getPassword(), user.getId()) != null) {
			session.setAttribute("username", user.getName());
			session.setAttribute("userId", user.getId());
			return "redirect:/adminhome";
		} else {
			model.addAttribute("errors", "Invalid Username Or Password");
			return "login";
		}
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession(true);
		return "redirect:/login";
	}

	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String errorPage() {
		return "404";
	}
}

package com.sami.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sami.models.Permission;
import com.sami.models.User;
import com.sami.repository.PermissionRepository;
import com.sami.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionRepository permissionRepository;

	Integer size = new Integer(3);
	Integer page = new Integer(0);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:/login";
	}

	@RequestMapping("/adminhome")
	public String home(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {

		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = this.size;
		}

		Pageable pageable = new PageRequest(page, size);

		Page<User> users = userRepository.findAllByOrderByIdDesc(pageable);
		model.addAttribute("users", users);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", "home");
		return "index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("action", "/create");
		return "create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("user") User user) {

		model.addAttribute("user", user);
		model.addAttribute("action", "/create");
		String originalPassword = user.getPassword();
		String generatedSecuredPasswordHash = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(originalPassword,
				org.springframework.security.crypto.bcrypt.BCrypt.gensalt(11));
		user.setPassword(generatedSecuredPasswordHash);
		userRepository.save(user);
		return "redirect:/adminhome";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		model.addAttribute("action", "/edit/" + id);
		return "create";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id, Model model, @ModelAttribute("user") User user) {
		model.addAttribute("user", user);
		model.addAttribute("action", "/edit/" + id);

		userRepository.save(user);
		return "redirect:/adminhome";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id, Model model) {
		userRepository.delete(id);
		return "redirect:/adminhome";
	}

	/* User Permission---------------------------------------------------- */
	@RequestMapping(value = "/userPermissions", method = RequestMethod.GET)
	public String userPermissionsGet(Model model) {

		Permission permission = new Permission();
		model.addAttribute("permission", permission);
		model.addAttribute("action", "/userPermissions");
		model.addAttribute("userLists", userRepository.findAll());
		return "userPermissions";
	}

	@RequestMapping(value = "/userPermissions", method = RequestMethod.POST)
	public String userPermissionsPOST(Model model, @ModelAttribute("permission") Permission permission) {

		model.addAttribute("permission", permission);
		model.addAttribute("action", "/userPermissions");
		model.addAttribute("userLists", userRepository.findAll());
		permissionRepository.save(permission);
		return "redirect:/userPermissionLists";
	}

	@RequestMapping(value = "/userPermissionLists", method = RequestMethod.GET)
	public String userPermissionLists(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {

		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = this.size;
		}

		Pageable pageable = new PageRequest(page, size);
		Page<Permission> permissions = permissionRepository.findAllByOrderByIdAsc(pageable);
		model.addAttribute("permissions", permissions);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("pages", "userPermissionLists");
		return "index";
	}

	@RequestMapping(value = "/usersFaq", method = RequestMethod.GET)
	public String usersFaq() {
		return "usersFaq";
	}

}

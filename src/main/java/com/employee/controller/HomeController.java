package com.employee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.dao.UserRepository;
import com.employee.helper.MessageHelper;
import com.employee.model.User;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository repository;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Employee Management System");
		return "home";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("title", "Contact - Employee Management System");
		return "contact";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Employee Management System");
		return "about";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Signup - Employee Management System");
		model.addAttribute("user", new User());
		return "signup";
	}

	@GetMapping("/signin")
	public String customLogin(Model model) {
		model.addAttribute("title", "Log in - Employee Management System");
		return "login";
	}

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,

			Model model) {
		try {
			user.setRole("ROLE_USER");
			user.setEnable(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User result = this.repository.save(user);
			System.out.println("Result : " + result);
			model.addAttribute("user", new User());
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			return "signup";
		}

	}

}

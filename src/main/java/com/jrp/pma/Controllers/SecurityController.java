package com.jrp.pma.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrp.pma.DAO.AccountRepository;
import com.jrp.pma.entities.UserAccount;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	AccountRepository accountRepo;

	@GetMapping("/register")
	// populating the registration form with user info
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "Security/Registration";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		// will encode the users password and then set the password back on the same user, then save to the db
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountRepo.save(user);
		
		return "redirect:/";
	}
}

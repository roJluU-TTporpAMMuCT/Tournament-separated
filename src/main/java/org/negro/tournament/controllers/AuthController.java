package org.negro.tournament.controllers;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import org.negro.tournament.models.User;
import org.negro.tournament.services.UserService;


@RestController
public class AuthController {
	
	@Autowired
	UserService uServ;
	
	@PostMapping("singUp")
	public List<ObjectError> reg(@RequestBody @Valid User userjs, BindingResult br)
	{
		if(!br.hasErrors() ) {
			try {
				uServ.loadUserByUsername(userjs.getUsername() );
				br.addError(new ObjectError("Name error", "Name has already in use") );
			}catch(UsernameNotFoundException e) {
				uServ.saveUser(userjs);
			}
		}
		return br.getAllErrors();
	}
}

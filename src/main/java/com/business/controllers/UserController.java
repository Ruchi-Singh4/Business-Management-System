package com.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.business.entities.User;
import com.business.services.UserServices;

@Controller
public class UserController
{
	@Autowired
	private UserServices services;

	@PostMapping("/addingUser")
	public String  addUser(@ModelAttribute User user)
	{
		System.out.println(user);
		this.services.addUser(user);
		return "redirect:/admin/services";
	}

	@GetMapping("/updatingUser/{id}")
	public String updateUser(@ModelAttribute User user, @PathVariable("id") int id)
	{
		this.services.updateUser(user, id);
		return "redirect:/admin/services";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id" )int id)
	{
		this.services.deleteUser(id);
		return "redirect:/admin/services";
	}
	
    // Show registration form
    @RequestMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam("uname") String uname,
                               @RequestParam("uemail") String uemail,
                               @RequestParam("upassword") String upassword,
                               @RequestParam("unumber") Long unumber){

        User user = new User();
        user.setUname(uname);
        user.setUemail(uemail);
        user.setUpassword(upassword);
        user.setUnumber(unumber);// Raw password will be encoded in the service

        services.addUser(user);

        return "redirect:/login"; // Redirect to login page or other page after successful registration
    }



}

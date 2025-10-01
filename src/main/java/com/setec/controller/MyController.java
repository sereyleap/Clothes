package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.Entities.Booked;
import com.setec.repos.BookedRepos;
import com.setec.telegrambot.MyTelegramBot;

@Controller
public class MyController {
	// http://localhost:8080/
	
	@GetMapping({"/","/home"})
	public String home(Model mod) {
		Booked booked = new Booked(
				1,
				"Mam Panhavuth",
				"098986363",
				"mpanhavuth@gamail.com",
				"09/03/2025",
				"8:30 AM",
				5);
		mod.addAttribute("booked", booked);
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/service")
	public String servive() {
		return "service";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/reservation")
	public String reservation(Model mod) {
		Booked booked = new Booked(
				1,
				"Mam Panhavuth",
				"0989863633",
				"mpanhavuth@gamail.com",
				"09/03/2025",
				"8:30 AM",
				5);
		mod.addAttribute("booked", booked);
		return "reservation";
	}
	
	@GetMapping("/testimonial")
	public String testimonial() {
		return "testimonial";
	}
	@Autowired
	private	BookedRepos bookedRepos;
	
	@Autowired
	private MyTelegramBot bot;
	
	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked) {
	    bookedRepos.save(booked);

	    // Build a formatted message with icons and new lines
	    String formattedMessage = String.format(
	        "✨ New Booking\n" +
	        "------------------------------\n" +
	        "👤 Name: %s\n" +
	        "📞 Phone Number: %s\n" +
	        "📧 Email: %s\n" +
	        "📅 Date: %s\n" +
	        "⏰ Time: %s\n" +
	        "👥 Number of People: %d",
	        booked.getName(),
	        booked.getPhoneNumber(),
	        booked.getEmail(),
	        booked.getDate(),
	        booked.getTime(),
	        booked.getPerson()
	    );

	    // Send the structured message to the bot
	    bot.message(formattedMessage);

	    return "success";
	}

}

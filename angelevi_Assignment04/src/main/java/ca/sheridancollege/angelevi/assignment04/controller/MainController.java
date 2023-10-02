/*Name: Vicente Manuel Angeles
Assignment: Assignment 04
Program: software development and network engineer

[brief description of program]

In this program we added some services, change the database
to mySql, put a registration page, put a new manager and user
inside the program, make a user Beans, and a lot more. This
program shows how to create a new user inside the program,
and give the user some roles and put restrictions between
manager and customer and of course add some new Cheese! this
is the last assignment for this class. I hope I pass this class!
*/

package ca.sheridancollege.angelevi.assignment04.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.angelevi.assignment04.beans.Cheese;
import ca.sheridancollege.angelevi.assignment04.database.DatabaseAccess;

@Controller
public class MainController {
	
	@Autowired @Lazy
	private DatabaseAccess da;
	
	
	//this method is for the main index 
	
	@GetMapping("/")
	public String mainIndex( Model model) {

		return "index.html";
	}
	

	//this method is use to get the information of the cheese
	@PostMapping("/inventory/addCheese")
	public String addCheese(Model model, 
				@ModelAttribute Cheese cheese) {
		int rowsReturned = da.insertCheese(cheese);

		model.addAttribute("rows", rowsReturned);
		model.addAttribute("cheese", da.getCheeseInventory());
		model.addAttribute("unitsId", da.getUnitsMap());
		model.addAttribute("cMap", da.getUnits());

		
		return"inventory/view.html";
	}
	
	//this method is for the inventory index and to get
	//	the value from the cheese class
	@GetMapping("/inventory/addCheese")
	public String addCheeseForm(HttpSession session, Model model) {
		
		model.addAttribute("cheese", new Cheese());

		
		if(session.getAttribute("unitsList") == null) {
			session.setAttribute("unitsList", da.getUnits());
		}


		return"inventory/index.html";
	}
	
	//this method is for showing the table of the cheeses
	@GetMapping("/inventory/inventoryView")
	public String viewInCheese(HttpSession session, Model model) {

        model.addAttribute("cheese", da.getCheeseInventory());
//        model.addAttribute("cMap", da.getUnits());
		if(session.getAttribute("unitsList") == null) {
			session.setAttribute("unitsList", da.getUnits());
		}
		return"inventory/view.html";
	}
	
	//this method is use to show the distinct value of the database
	@GetMapping("/view")
	public String viewCheese(HttpSession session, Model model) {
		
		model.addAttribute("cheese", da.getCheeses());
//		model.addAttribute("unitsList", da.getUnits());
		if(session.getAttribute("unitsList") == null) {
			session.setAttribute("unitsList", da.getUnits());
		}
		
		return"view.html";
	}
	
	//this method is use for launching the login web page. 
	@GetMapping("/login")
	public String loginForm() {
		return "login.html";
	}
	
	//this method is use for launching the login web page.
	@GetMapping("/register")
	public String registerForm() {
		return "register.html";
	}
	
	//this method is use for getting the information when the user
	//try to register a new account. 
	@PostMapping("/register")
	public String registerUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {
		
		long userId = da.addUser(userName, password, email);
		da.AddUserRole(userId, 2L);
//		da.AddUserRole(userId, 1L);
		return "redirect:/login";

	}
	
	
	
	
}
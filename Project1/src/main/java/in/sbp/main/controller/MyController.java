package in.sbp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sbp.main.entity.Student;
import in.sbp.main.service.StudentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController 
{
	@Autowired
	private StudentServiceImpl stdServiceImp;
	
	@GetMapping("/")
    public String home() {
        return "index";  // Spring Boot automatically resolves index.html from templates/
    }
	
	@GetMapping("/regPage")
	public String openRegPage(Model model)   
	{
		model.addAttribute("std", new Student());
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute("std") Student std, Model model)   
	{
		boolean status = stdServiceImp.registerStudent(std);
		
		if(status)
		{
			model.addAttribute("successMsg", "User registered successfully");
		}
		else
		{
			model.addAttribute("errorMsg", "User not registered due to some error");
		}
		return "register";
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage(Model model)   
	{
		model.addAttribute("std", new Student());
		return "login";
	}
	
	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("std") Student std, Model model)   
	{
		Student validStd = stdServiceImp.loginStudent(std.getEmail(),std.getPassword());
		
		if(validStd != null)
		{
			model.addAttribute("StdName",validStd.getName());
			return "profile";
		}
		else
		{
			model.addAttribute("errorMsg","Email id and password did not matched");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);      // to destroy current session first we get current session by .getSession() method 
		                                                      // we pass false to return current session and store it to session and then check
		if(session  != null)                                  // if not null then ssession.invalidate() means it destroy current session.
		{
			session.invalidate();
		}
		return "redirect:/loginPage";
	}
}

// When a user navigates to /regPage via a browser, this method is invoked. A new Student object is created and added to the model under 
// the attribute name "std". Then, the application renders the register view, where this Student object can be used to display or input data 
// about a student in a form. model.addAttribute("key", value) is used to add data to the Model object that can be accessed in the view. 
// In this case, the key is "std" and the value is new Student().
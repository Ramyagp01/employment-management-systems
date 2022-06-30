package com.emp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.emp.entity.Employee;
import com.emp.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	private EmpService Service;

	@GetMapping("/")
	public String home(Model m) {

		List<Employee> emp = Service.getAllEmp();
		m.addAttribute("emp", emp);
		return "index";
	}

	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}

	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session) {
		System.out.println(e);

		Service.addEmp(e);
		session.setAttribute("msg", "Employee Added Successfully..");

		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {

		Employee e = Service.getEmpById(id);
		m.addAttribute("emp", e);
		return "edit";

	}

	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		Service.addEmp(e);

		session.setAttribute("msg", "Employee Data Updated Successfully..");
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String DeleteEmp(@PathVariable int id,HttpSession session)
	{
		
		Service.deleteEmp(id);
		session.setAttribute("msg", "Employee Data Deleted Successfully..");
		return "redirect:/";
	}
}

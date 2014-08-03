package com.tja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tja.domain.Customer;
import com.tja.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/add",method=RequestMethod.GET)
	public ModelAndView addCustomerPage(Customer c) {
		ModelAndView mav = new ModelAndView("addCustomer","customer",new Customer());
		return mav;
	}
	
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public ModelAndView addCustomer(@Valid  Customer customer,BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.setViewName("addCustomer");
		} else {
			customerService.createCustomer(customer);
			mav.setViewName("redirect:/customer/list");
		}
		return mav;
	}
	
	@RequestMapping(value={"/","/list"})
	public ModelAndView listCustomer() {
		List<Customer> customers = customerService.getAllCustomer();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("customerList", customers);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String deleteCustomer(Long id) {
		customerService.deleteCustomer(id);
		return "success";
	}
}

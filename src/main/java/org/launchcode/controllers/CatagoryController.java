package org.launchcode.controllers;

import javax.validation.Valid;

import org.launchcode.models.Catagory;
import org.launchcode.models.Cheese;
import org.launchcode.models.CheeseType;
import org.launchcode.models.data.CatagoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("category")
public class CatagoryController {
	
	@Autowired
	private CatagoryDao catagoryDao;
	
	@RequestMapping(value = "")
	public String index(Model model) {
		
		model.addAttribute("categories", catagoryDao.findAll());
        model.addAttribute("title", "My Categories");

        return "category/index";
	}
	
	 @RequestMapping(value = "add", method = RequestMethod.GET)
	    public String displayAddCategoryForm(Model model) {
	        model.addAttribute("title", "Add Category");
	        model.addAttribute(new Catagory());
	        return "category/add";
	 }

	 @RequestMapping(value = "add", method = RequestMethod.POST)
	 public String processAddCategoryForm(@ModelAttribute  @Valid Catagory newCategory,
	                                   Errors errors, Model model) {

	    if (errors.hasErrors()) {
	        model.addAttribute("title", "Add Category");
	        return "category/add";
	    }

	    catagoryDao.save(newCategory);
	    return "redirect:";
	 }

	    @RequestMapping(value = "remove", method = RequestMethod.GET)
	    public String displayRemoveCategoryForm(Model model) {
	        model.addAttribute("categories", catagoryDao.findAll());
	        model.addAttribute("title", "Remove Category");
	        return "cheese/remove";
	    }

	    @RequestMapping(value = "remove", method = RequestMethod.POST)
	    public String processRemoveCategoryForm(@RequestParam int[] categoryIds) {

	        for (int categoryId : categoryIds) {
	            catagoryDao.delete(categoryId);
	        }

	        return "redirect:";
	    }

}

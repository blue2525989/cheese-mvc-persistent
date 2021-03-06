package org.launchcode.controllers;

import java.util.List;

import javax.validation.Valid;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "menu")
public class MenuController {
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private CheeseDao cheeseDao;

	@RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "All Menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add a Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Menu menu, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add a Menu");
            model.addAttribute("menu", menu);
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }
    
    @RequestMapping(value = "view/{menuId}",  method = RequestMethod.GET)
    public String view (@PathVariable int menuId, Model model) {
    	Menu menu = menuDao.findOne(menuId);
    	if (menu == null) {
    		return "redirect:/";
    	}
    	else {
    		model.addAttribute("title", menu.getName());
    		model.addAttribute(menu);
    		return "menu/view";
    	}
    }
    
    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(@PathVariable int menuId, Model model) {
    	Menu menu = menuDao.findOne(menuId);
    	if (menu != null) {
    		Iterable<Cheese> cheeses = cheeseDao.findAll();
        	AddMenuItemForm form = new AddMenuItemForm(menu, cheeses);
        	model.addAttribute("form", form);
        	model.addAttribute("title", "Add item to menu: " + menu.getName());
        	return "menu/add-item";
    	}
    	else {
    		return "redirect:/";
    	}
    }

    @RequestMapping(value="add-item", method= RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("form", form);
            model.addAttribute("title", String.format("Add Cheese to Menu: %s", form.getMenu().getName()));
            return "menu/add-item";
        }
        Cheese cheese = cheeseDao.findOne(form.getCheeseId());
        Menu menu = menuDao.findOne(form.getMenuId());
        menu.addItem(cheese);
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();


    }
}

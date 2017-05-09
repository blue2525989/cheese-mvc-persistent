package org.launchcode.models.forms;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

public class AddMenuItemForm {
	
	private Menu menu;
	
	private Iterable<Cheese> cheeses;
	
	@NotNull
	private int menuId;
	
	@NotNull
	private int cheeseId;
	
	public AddMenuItemForm () {}
	
	public AddMenuItemForm (Menu menu, Iterable<Cheese> cheese) {
		this.menu = menu;
		this.cheeses = cheese;
	}

	public Menu getMenu() {
	    return this.menu;
	}

	public void setMenu(Menu menu) {
	    this.menu = menu;
	}

	public Iterable<Cheese> getCheeses() {
	    return this.cheeses;
	}

	public void setCheeses(Iterable<Cheese> cheeses) {
	    this.cheeses = cheeses;
	}

	public int getMenuId() {
	    return menuId;
	}

	public void setMenuId(int menuId) {
	   this.menuId = menuId;
	}

	public int getCheeseId() {
	    return this.cheeseId;
	}

	public void setCheeseId(int cheeseId) {
	    this.cheeseId = cheeseId;
	}
}
package org.launchcode.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Menu {
	
	@Id
    @GeneratedValue
	private int id;
	
	@NotNull
	@Size(min = 3, max = 16)
	private String name;
	
	@ManyToMany
	List<Cheese> cheeses = new ArrayList<>();
	
	public Menu() {}
	
	public Menu(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public List<Cheese> getCheeses() {
		return this.cheeses;
	}
	
	public void addItem(Cheese item) {
		cheeses.add(item);
	}
}

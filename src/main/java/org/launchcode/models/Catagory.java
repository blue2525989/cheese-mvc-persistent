package org.launchcode.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Catagory {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Size(min=3, max=15)
	private String name;
	
	@OneToMany
	@JoinColumn(name = "category_id")
	private List<Cheese> cheeses  = new ArrayList<>();;

	
	public Catagory () {}
	
	public Catagory (String name) {
		setName(name);
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Cheese> getCheeses() {
        return cheeses;
    }

}

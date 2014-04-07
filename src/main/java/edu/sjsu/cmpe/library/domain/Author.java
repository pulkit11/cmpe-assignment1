package edu.sjsu.cmpe.library.domain;

import java.util.Random;

import org.hibernate.validator.constraints.NotEmpty;

public class Author {
	@NotEmpty
	private String name;
	private int id;
	
	public Author()
	{
		Random random = new Random();
		this.id = random.nextInt(100);
	}
	/*
	 * @param name
	 * returns name parameter
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 * returns id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}

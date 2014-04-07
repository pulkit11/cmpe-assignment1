package edu.sjsu.cmpe.library.domain;

import java.util.Random;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
	private int id;
	
	@NotEmpty
	private String rating;
	
	@NotEmpty
	private String comment;
	
	public Review()
	{
		Random rand = new Random();
		this.id = rand.nextInt(100);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the rating
	 */
	@JsonProperty("rating")
	public Integer getRating() {
		return Integer.valueOf(rating);
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}

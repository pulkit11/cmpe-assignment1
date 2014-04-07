/**
 * 
 */
package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;


import edu.sjsu.cmpe.library.domain.Review;

/**
 * @author Amit
 *When response needs list of reviews for a perticular book
 */
public class ReviewsDto extends LinksDto{
	private List<Review> reviews = new ArrayList<Review>();
	
	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}

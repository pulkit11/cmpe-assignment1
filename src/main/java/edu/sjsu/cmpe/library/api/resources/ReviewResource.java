/**
 * 
 */
package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BooksDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {
	
	public ReviewResource(){
		//not required
	}
	
	@POST
    @Timed(name="create-review")
    public Response createBookReview(@PathParam("isbn") Long isbn,@Valid Review review)
    {
		//Step1: Get the book with mentioned isbn
    	BooksDto books = new BooksDto();
    	Book book = books.getBookByIsbn(isbn);
    	
    	//Step2: Add Review for the book
    	book.addReviewForBook(review);
    	
    	//Step3: Return links as a response
    	LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("view-review", "/books/"+book.getIsbn()+"/reviews/"+review.getId(), "GET"));
    	return Response.ok(links).build();
    }
	
	 @GET
	 @Path("{id}")
	 @Timed(name="view-review")
	 public ReviewDto getReviewsOfBook(@PathParam("isbn") Long isbn, @PathParam("id") Long id)
	    {
	    	//Step1: Get the book with the mentioned isbn
	    	BooksDto books = new BooksDto();
	    	Book book = books.getBookByIsbn(isbn);
	    	
	    	//Step2: Now search the review with mentioned Id
	    	Review review = book.getReviewById(id.intValue());
	    	
	    	//Step3: Prepare the object to return.
	    	//ReviewDto object with links (Expected in response)
	    	ReviewDto reviewResponse = new ReviewDto(review);
	    	reviewResponse.addLink(new LinkDto("view-review", "/books/"+book.getIsbn()+"/reviews/"+review.getId(), "GET"));
	    	
	    	//step4: Send response to client
	    	return reviewResponse;
	    }
	 
	@GET
    @Timed(name="view-All-reviews")
    public ReviewsDto getAllReviewsOfBook(@PathParam("isbn") Long isbn)
    {
    	//step1: get the required book based on isbn
    	BooksDto books = new BooksDto();
    	Book book = books.getBookByIsbn(isbn);
    	
    	//step2: get all reviews of the book
    	List<Review> allReviews = book.getReviews();
    	
    	//step3: Generate object to pass to the client, along with links
    	//step3.1: take all reviews and set in the new object
    	ReviewsDto reviewsDto = new ReviewsDto();
    	reviewsDto.setReviews(allReviews);
    	
    	//step3.2: set up as many links as reviews
    	
    	return reviewsDto;
    }
}

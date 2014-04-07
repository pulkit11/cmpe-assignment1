/**
 * 
 */
package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BooksDto;
import edu.sjsu.cmpe.library.dto.LinkDto;

/**
 * @author Amit
 *
 */
@Path("/v1/books/{isbn}/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

	public AuthorResource(){
		//Not Required
	}
	
	@GET
	@Path("{id}")
	@Timed(name="view-author")
	public AuthorDto getAuthorByisbnId(@PathParam("isbn") Long isbn, @PathParam("id") Long id)
	{
		//step1: get the book by isbn
		BooksDto books = new BooksDto();
		Book book = books.getBookByIsbn(isbn);
		
		//step2: get the author of that book
		Author author = book.getAuthorById(id.intValue());
		
		//step3: return the link
		AuthorDto authorDto = new AuthorDto(author);
		authorDto.addLink(new LinkDto("view-author", "/books/"+book.getIsbn()+"/authors/"+author.getId(), "GET"));
		return authorDto;
	}
	
	@GET
	@Timed(name="view-all-authors")
	public AuthorsDto getAllReviews(@PathParam("isbn") Long isbn)
	{	
		//Step1: Get the book with mentioned isbn
		BooksDto books = new BooksDto();
		Book book = books.getBookByIsbn(isbn);
		
		//Step3: Get all Authors of the book.
		List<Author> allAuthors = book.getAuthors();
		
		//Step4: Build object with all authors and links
		//To return multiple authors used AuthorsDto
		AuthorsDto authorsDto = new AuthorsDto();
		authorsDto.setAuthors(allAuthors);
		
		//Step5: Now add and return links for all authors
	
    	return authorsDto;
	}
}

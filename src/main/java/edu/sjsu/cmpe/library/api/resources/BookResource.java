package edu.sjsu.cmpe.library.api.resources;

//import java.util.List;

//import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;






//import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

//import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
//import edu.sjsu.cmpe.library.domain.Review;
//import edu.sjsu.cmpe.library.dto.AuthorDto;
//import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.BooksDto;
//import edu.sjsu.cmpe.library.dto.BooksDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
//import edu.sjsu.cmpe.library.dto.ReviewDto;
//import edu.sjsu.cmpe.library.dto.ReviewsDto;
//import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") Long isbn) {
    	//Step1: create object of books repository(BooksDto)
    	BooksDto books = new BooksDto();

    	//Step2: search and get the book from repository
        Book book = books.getBookByIsbn(isbn);

        //here bookDto is creating the json HTTP response hence passing Book's object
        //so that it'll create the json object according to parameters listed in book
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
    	bookResponse.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(), "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book", "/books/"+book.getIsbn(), "DELETE"));
    	bookResponse.addLink(new LinkDto("create-review", "/books/"+book.getIsbn()+"/reviews", "POST"));
    	
    	//automatically returns JSON object
    	return bookResponse;
    }

    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);

	//String location = "/books/" + savedBook.getIsbn();
	BookDto bookResponse = new BookDto(savedBook);
	
	
	bookResponse.addLink(new LinkDto("view-book", "/books/"+String.valueOf(savedBook.getIsbn()), "GET"));
	bookResponse.addLink(new LinkDto("update-book", "/books/"+String.valueOf(savedBook.getIsbn()), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", "/books/"+String.valueOf(savedBook.getIsbn()), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/"+String.valueOf(savedBook.getIsbn())+"/reviews", "POST"));
	
	
	

	return Response.status(201).entity(bookResponse).build();
    }
    
    @DELETE
    @Path("{isbn}")
    @Timed(name="delete-book")
    public Response deleteBookFromLibrary(@PathParam("isbn") Long isbn)
    {
    	
    	bookRepository.deleteBookByIsbn(isbn);
    	
    	
    	LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("create-book", "/books", "POST"));
    	return Response.ok(links).build();
    }
    
    @PUT
    @Path("{isbn}")
    @Timed(name="update-book")
    public Response updateBookStatus(@QueryParam("status") String status, @PathParam("isbn") Long isbn)
    {
    	
    	Book book=bookRepository.update(isbn, status);
    	
    	
    	LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("view-book", "/books/"+book.getIsbn(), "GET"));
    	links.addLink(new LinkDto("update-book", "/books/"+book.getIsbn(), "PUT"));
    	links.addLink(new LinkDto("delete-book", "/books/"+book.getIsbn(), "DELETE"));
    	links.addLink(new LinkDto("create-review", "/books/"+book.getIsbn()+"/reviews", "POST"));
    	if(book.hasReviews()){
    		links.addLink(new LinkDto("view-all-reviews", "/books/"+book.getIsbn()+"/reviews", "GET"));
    	}
    	return Response.ok(links).build();
    }
    
    
}


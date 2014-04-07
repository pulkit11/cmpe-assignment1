package edu.sjsu.cmpe.library.domain;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.dto.LinkDto;

public class Book{
    private long isbn;
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    @JsonProperty("publication-date")
    private String publicationDate;
    
    @JsonProperty("num-pages")
    int numPages;
	
    private String status;
	private String language;
    private String available = "available";
    
    @Valid
    private List<Author> authors = new ArrayList<Author>();
    private List<Review> reviews = new ArrayList<Review>();
    
    private List<LinkDto> authorsLinks = new ArrayList<LinkDto>();
    // add more fields here
    
    private int linksFlag = 0;
    
    public Book()
    {
    	status = available;
    	Random r = new Random();
    	int n = r.nextInt(100);
    	isbn = (long) n;
    }
    /**
     * @return the isbn
     */
    public long getIsbn() {
    	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    /**
     * @param publicationDate
     */
    public void setpublicationDate(String publicationDate)
    {
    	this.publicationDate = publicationDate;
    }
    
    /**
     * @return publicationDate 
     */
    public String getpublicationDate()
    {
    	return this.publicationDate;
    }
    
    /*
     * @param status
     */
    public void setStatus(String status) {
		this.status = status;
	}
    
    /*
     * @return status
     */
    public String getStatus(){
    	return this.status;
    }
    
    /*
     * @param num-pages
     */
    @JsonProperty("num-pages")
    public Object getnumPages() {
    	Object o = new Object();
    	if(this.numPages == 0)
			{
    			o = "Not Specified";
    			return o;
			}
    	else{
    		o = numPages;
    		return o;
    	}
	}
    /*
     * @param return num-pages
     */
    @JsonProperty("num-pages")
	public void setnumPages(int numPages) {
		this.numPages = numPages;
	}
    
    /*
     * @return language
     */
    @JsonProperty("language")
	public String getlanguage() {
		if(this.language == null)
			return "Language is not specified";
		else
			return language;
	}
	
	/**
	 * 
	 * @param language
	 * returns language
	 */
	public void setlanguage(String language) {
		this.language = language;
	}
	
	// changes
	
	
	@JsonIgnore
	public List<Author> getAuthors() {
		System.out.println("------1");
		return authors;
	}
	@JsonProperty
	public void setAuthors(List<Author> authors) {
		System.out.println("-----123");
		this.authors = authors;
	}
	
	/**
	 * @param Review
	 */
	public void addReviewForBook(Review review)
	{
		this.reviews.add(review);
	}
	
	/**
	 * 
	 * @param Id
	 * @return Review
	 */
	public Review getReviewById(int id)
	{
		Review review=null;
		int i=0;
		while(reviews.get(i)!=null)
		{
			review = reviews.get(i);
			if(review.getId() == id)
				break;
			i++;
		}
		return review;
	}
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
	
	public Author getAuthorById(int id){

		Author author=null;
		int i=0;
		while(authors.get(i)!=null)
		{
			author = authors.get(i);
			if(author.getId() == id)
				break;
			i++;
		}
		return author;
	}
	
	/**
	 * @return the authorsLinks
	 */
	@JsonProperty("authors")
	public List<LinkDto> getAuthorsLinks() 
	{
		System.out.println("getauthor1");
		Iterator<Author> it = authors.iterator();
		if(linksFlag == 0){
			while(it.hasNext())
			{
				Author author = it.next();
				this.authorsLinks.add(new LinkDto("view-author", "/books/"+this.isbn+"/authors/"+author.getId(), "GET"));
			}
			linksFlag = 1;
		}
		return authorsLinks;
	}
	
	/**
	 * @param authorsLinks the authorsLinks to set
	 */
	@JsonIgnore
	public void setAuthorsLinks(List<LinkDto> authorsLinks) {
		System.out.println("set author1");
		this.authorsLinks = authorsLinks;
	}
	
	/**
	 * 
	 * @return true if Reviews are available for the book
	 * Or returns False if there are no reviews available
	 */
	public boolean hasReviews()
	{
		if(reviews.isEmpty())
		{
			return false;
		}
		else{
			return true;
		}
	}
}
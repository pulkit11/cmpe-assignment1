package edu.sjsu.cmpe.library.repository;

//import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.ConcurrentHashMap;

//import com.yammer.dropwizard.jersey.params.LongParam;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.*;

public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;

    /** Never access this key directly; instead use generateISBNKey() */
  

    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	
    }

    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    
    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Book saveBook(Book newBook) {
	//checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	//Long isbn = generateISBNKey();
	//newBook.setIsbn(isbn);
	// TODO: create and associate other fields such as author

	BooksDto book=new BooksDto();
	
	book.addBookToLibrary(newBook);
	// Finally, save the new book into the map
	//bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }

   
    @Override
    public Book getBookByISBN(Long isbn) 
    {
    	//Book book=new Book();
    	BooksDto b=new BooksDto();
    	
    	Book book=b.getBookByIsbn(isbn);
    	
	
	return book;
    }

	@Override
	public BooksDto deleteBookByIsbn(Long isbn) {
		BooksDto books = new BooksDto();
    	books.deleteBookbyIsbn(isbn);
    	return books;
	}

	@Override
	public Book update(Long isbn, String status)
	
	{
		BooksDto books = new BooksDto();
    	//update book object Present in the hashtable
    	books.updateStatusByIsbn(isbn, status);
    	Book book = books.getBookByIsbn(isbn);
    	
    	return book;
		
	}

}

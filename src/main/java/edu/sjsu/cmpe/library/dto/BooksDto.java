package edu.sjsu.cmpe.library.dto;

import java.util.Hashtable;



import edu.sjsu.cmpe.library.domain.Book;

public class BooksDto {
	
	private static Hashtable<String, Book> books = new Hashtable<String,Book>();

	/**
	 * 
	 * @param book
	 */
	public void addBookToLibrary(Book book)
	{
		System.out.println("in booksDto");
		books.put(String.valueOf(book.getIsbn()), book);
	}
	
	/**
	 * 
	 * @param isbn
	 */
	public void deleteBookbyIsbn(Long isbn)
	{
		books.remove(String.valueOf(isbn));
	}
	
	/*
	 * @param isbn
	 * @param return Book
	 * Search and return the book based on isbn number
	 */
	public Book getBookByIsbn(Long isbn) {
		Book book = books.get(String.valueOf(isbn));
		return book;
	}
	
	/**
	 * 
	 * @param isbn
	 */
	public void updateStatusByIsbn(long isbn, String status){
		Book book = books.get(String.valueOf(isbn));
		book.setStatus(status);
	}
}

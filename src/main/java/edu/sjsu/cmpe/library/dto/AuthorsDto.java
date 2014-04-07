package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

/**
 * 
 * @author Amit
 *This class is created to create a list of authors, so that it can be returned to client in json
 */
@JsonPropertyOrder(alphabetic = true)
public class AuthorsDto extends LinksDto{
	public List<Author> authors = new ArrayList<Author>();

	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}

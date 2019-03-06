package com.app.bookstore.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.bookstore.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BookRepository implements IBookRepository {
	
	@Override
	public List<Book> getBooks() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Book> books = Arrays.asList(mapper.setSerializationInclusion(Include.NON_NULL).readValue(new File(
				"data/books.json"), Book[].class));
		return books;
	}

}

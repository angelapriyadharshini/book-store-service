package com.app.bookstore.Repository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.bookstore.model.Book;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Repository
public interface IBookRepository {
	public List<Book> getBooks() throws JsonParseException, JsonMappingException, IOException;
}

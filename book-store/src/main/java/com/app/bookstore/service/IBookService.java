package com.app.bookstore.service;

import java.io.IOException;
import java.util.List;

import com.app.bookstore.model.Book;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IBookService {
	public List<Book> getAllBooks() throws JsonParseException, JsonMappingException, IOException;
}

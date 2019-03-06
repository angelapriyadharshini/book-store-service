package com.app.bookstore.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookstore.Repository.IBookRepository;
import com.app.bookstore.model.Book;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class BookService implements IBookService {
	
	@Autowired
	private IBookRepository bookRepository;

	public List<Book> getAllBooks() throws JsonParseException, JsonMappingException, IOException{
		return bookRepository.getBooks();
	}
}

package com.app.bookstore.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookstore.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
// @RequestMapping("/books")
public class BookController {

	@Autowired
	private ObjectMapper mapper;

	private final Logger LOGGER = Logger.getLogger(getClass().getName());

	@CrossOrigin
	@GetMapping("/books")
	public List<Book> getAllBooks() throws JsonParseException,
			JsonMappingException, IOException {

		List<Book> books = Arrays.asList(mapper.setSerializationInclusion(
				Include.NON_NULL).readValue(new File("data/books.json"),
				Book[].class));
		LOGGER.info(">>> Retrieving All books" + books.toString());
		return books;
	}

	// http://localhost:8080/api/books/2
	// @CrossOrigin
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable int id) throws JsonParseException,
			JsonMappingException, IOException {

		List<Book> booklist = Arrays.asList(mapper.readValue(new File(
				"data/books.json"), Book[].class));
		Book book = new Book();

		for (Book b : booklist) {
			try {

				if (b.getId() == id) {
					LOGGER.info(">>> Retrieving " + b.getTitle());
					book = b;
				}
			} catch (Exception ex) {
				LOGGER.info(">>> Error while retrieving book: "
						+ ex.getMessage());
			}
		}
		// Handle errors if id not found
		return book;
	}

	// @CrossOrigin
	@PutMapping("/books/{id}")
	public void editBook(@RequestBody Book book, @PathVariable int id) {
		try {

			List<Book> books = Arrays.asList(mapper.readValue(new File(
					"data/books.json"), Book[].class));

			for (Book b : books) {
				try {
					if (b.getId() == book.getId()) {
						LOGGER.info(">>> Editing " + b.getTitle());
						b.setAuthor(book.getAuthor());
						b.setTitle(book.getTitle());
						b.setEdition(book.getEdition());
					}
				} catch (Exception ex) {
					LOGGER.info(">>> Error while retrieving book: "
							+ ex.getMessage());
				}
			}

			String json = mapper.writeValueAsString(books);
			List<Book> jjson = Arrays.asList(mapper.readValue(json,
					Book[].class));
			mapper.writerWithDefaultPrettyPrinter().writeValue(
					new File("data/books.json"), jjson);
		} catch (IOException e) {
			LOGGER.info(">>> Error while reading JSON file");
		}
	}

	// @CrossOrigin
	@PostMapping("/books")
	public void createBook(@RequestBody Book book) {
		try {

			List<Book> books = Arrays.asList(mapper.readValue(new File(
					"data/books.json"), Book[].class));

			int newId = books.size() + 1;
			String json = mapper.writeValueAsString(books);
			json = json.replaceAll("\\]", "");
			book.setId(newId);
			String newBookJson = mapper.writeValueAsString(book);
			// newjsn.replaceAll("\\[", "");
			LOGGER.info(">>> Creating new Book: " + newBookJson);
			String newBookJSON = json + "," + newBookJson + "]";

			List<Book> jjson = Arrays.asList(mapper.readValue(newBookJSON,
					Book[].class));
			mapper.writerWithDefaultPrettyPrinter().writeValue(
					new File("data/books.json"), jjson);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @CrossOrigin
	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable int id) throws JsonParseException,
			JsonMappingException, IOException {

		List<Book> books = Arrays.asList(mapper.readValue(new File(
				"data/books.json"), Book[].class));

		for (Book b : books) {
			try {

				if (b.getId() == id) {
					books.remove(b);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public List<Book> bookList() {
		List<Book> booklist = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setId(1);
		book1.setTitle("The Scarlet Pimpernal");
		book1.setAuthor("Emma Orczy");
		book1.setEdition("1st");
		booklist.add(book1);

		Book book2 = new Book();
		book2.setId(2);
		book2.setTitle("Merchant of Venice");
		book2.setAuthor("William Shakespeare");
		book2.setEdition("2nd");
		booklist.add(book2);

		Book book3 = new Book();
		book3.setId(3);
		book3.setTitle("Julius Caesar");
		book3.setAuthor("William Shakespeare");
		book3.setEdition("2nd");
		booklist.add(book3);

		return booklist;
	}
}

package com.app.bookstore;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.bookstore.model.Book;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		try {

			ObjectMapper mapper = new ObjectMapper();
			List<Book> books = Arrays.asList(mapper.readValue(new File(
					"data/books.json"), Book[].class));

			System.out.println("Number of Books available : " + books.size());
			String json = mapper.writeValueAsString(books);
			System.out.println(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SpringApplication.run(BookStoreApplication.class, args);
	}

}

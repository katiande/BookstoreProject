package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(BookRepository repository){
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book ("Lastensatuja","Matti Meikäläinen","1-2", 1994, 5.50));
			repository.save(new Book ("Suomen historia","Lilli Putti", "2-3",2006, 25.90));
			
			log.info("fetch books");
			for (Book books : repository.findAll()){
				log.info(books.toString());
			}
			
		};
	}
}

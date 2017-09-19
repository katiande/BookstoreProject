package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(BookRepository repository, UserRepository userepo){
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book ("Lastensatuja","Matti Meikäläinen","1-2", 1994, 5.50));
			repository.save(new Book ("Suomen historia","Lilli Putti", "2-3",2006, 25.90));
			
			User user1 = new User("user", "$2a$07$1uwvmrbyqO1GujZjhsphoePTJyrjjDPobt5kwBbRMfeeKYTrVcXVS", "matti.matti@kukkuu.fi", "USER"); //kayttis
			User user2 = new User("admin","$2a$05$JLgf1ua4Lz4kiC1QSy/5vObPlNNFQWccN/lzyw8mN4sue9QpWe8BK", "kaisa.kukka@liirum.com", "ADMIN"); //yllap
				userepo.save(user1);
				userepo.save(user2);
			
			log.info("fetch books");
			for (Book books : repository.findAll()){
				log.info(books.toString());
			}
			
		};
	}
}

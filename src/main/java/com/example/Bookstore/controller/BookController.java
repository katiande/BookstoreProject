package com.example.Bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;


@Controller
public class BookController {
	
	@Autowired
	private BookRepository repo;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
    @RequestMapping(value="/booklist", method=RequestMethod.GET)
    public String bookList(Model model) {
    	
    	model.addAttribute("books", repo.findAll());
    	
        return "booklist";
    }
    
    @RequestMapping(value="/books", method= RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest(){
    	return(List<Book>) repo.findAll();
    }
    
    @RequestMapping(value="/book/{id}", method= RequestMethod.GET)
    public @ResponseBody Book findBookRest (@PathVariable("id") Long bookId){
    	return repo.findOne(bookId);
    }
    
    @RequestMapping(value="/add")
    public String addBook(Model model) {
    	
    	model.addAttribute("book", new Book());
    	
        return "addbook";
    }
   
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(Book book) {
    	
    	repo.save(book);
    	
        return "redirect:/booklist";
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	
    	repo.delete(bookId);
    	
        return "redirect:../booklist";
    }

}

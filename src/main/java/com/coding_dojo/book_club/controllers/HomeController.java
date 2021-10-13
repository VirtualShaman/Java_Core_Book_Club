package com.coding_dojo.book_club.controllers;
    
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coding_dojo.book_club.models.Book;
import com.coding_dojo.book_club.models.LoginUser;
import com.coding_dojo.book_club.models.User;
import com.coding_dojo.book_club.services.BookService;
import com.coding_dojo.book_club.services.UserService;
    
    
@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
    private BookService bookService;
    
    public HomeController(UserService userServ, BookService bookService) {
    	this.userServ = userServ;
    	this.bookService = bookService;
    }
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    @RequestMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
    	model.addAttribute("allBooks", bookService.allBooks());
    	model.addAttribute("user", userServ.oneUser((Long)
    			session.getAttribute("user_id")));
    	return "dashboard.jsp";
    }
    
    @RequestMapping("/oneBook/{id}")
    public String oneCouse(@PathVariable("id") Long id,
    		Model model, HttpSession session) {
    	User user = userServ.oneUser((Long)session.getAttribute("user_id"));
    	model.addAttribute("user", user);
    	model.addAttribute("book", bookService.oneBook(id));
    	return "oneBook.jsp";
    }
    
    @RequestMapping("/newBook")
    public String newBook(@ModelAttribute("book") Book book, 
    		Model model, HttpSession session) {
    	Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
    	return "newBook.jsp";
    }
    @RequestMapping(value = "/makeBook", method= RequestMethod.POST)
    public String createAction(@Valid @ModelAttribute("book") Book book,
    		BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		return "newBook.jsp";
    	} else {
    		bookService.createBook(book);
    		return "redirect:/dashboard";
    	}
    }
    
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id,
    		@ModelAttribute("book") Book book, Model model,
    		HttpSession session) {
    	Book oneBook = bookService.oneBook(id);
    	model.addAttribute("book", oneBook);
    	Long user_id = (Long)session.getAttribute("user_id");
    	model.addAttribute("user", user_id);
    	return "editBook.jsp";
    }
    @RequestMapping(value="/editAction/{id}", method=RequestMethod.PUT)
    public String editAction(@Valid @ModelAttribute("book") Book book,
    		BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		return "editBook.jsp";
    	} else {
    		bookService.updateBook(book);
    		return "redirect:/dashboard";
    	}
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
    	bookService.delete(id);
    	return "redirect:/dashboard";
    }
    
    
}

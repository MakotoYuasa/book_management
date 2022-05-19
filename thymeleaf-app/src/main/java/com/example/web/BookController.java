package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Book;
import com.example.service.BookService;

@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    BookService bookService;

    @ModelAttribute
    BookForm setUpForm() {
        return new BookForm();
    }

    @GetMapping
    String list(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    @PostMapping(path = "create")
    String create(@Validated BookForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }

        Book book = new Book();
        BeanUtils.copyProperties(form, book);
        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, BookForm form) {
        Book book = bookService.findOne(id);
        BeanUtils.copyProperties(book, form);
        return "books/edit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, @Validated BookForm form, BindingResult result) {
        if (result.hasErrors()) {
            return editForm(id, form);
        }

        Book book = new Book();
        BeanUtils.copyProperties(form, book);
        book.setId(id);
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/books";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
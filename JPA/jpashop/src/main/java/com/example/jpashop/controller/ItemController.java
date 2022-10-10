package com.example.jpashop.controller;

import com.example.jpashop.domain.Book;
import com.example.jpashop.domain.Item;
import com.example.jpashop.service.ItemService;
import com.example.jpashop.vo.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 2022-10-10
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createItemForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm.html";
    }

    @PostMapping("/new")
    public String createItem(BookForm bookForm) {
        itemService.saveItem(new Book(bookForm));
        return "redirect:/items";
    }

    @GetMapping
    public String itemList(Model model) {
        model.addAttribute("items", itemService.findItems());
        return "items/itemList";
    }

    @PostMapping("{itemId}/edit")
    public String getItem(@ModelAttribute("form") BookForm bookForm, @PathVariable String itemId) {
        Book book = new Book();
        book.setId(bookForm.getId());
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("{itemId}/edit")
    public String getItemView(@PathVariable Long itemId, Model model) {
        Book item = (Book) itemService.findById(itemId);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

}

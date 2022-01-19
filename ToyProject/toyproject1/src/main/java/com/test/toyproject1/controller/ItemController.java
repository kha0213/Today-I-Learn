package com.test.toyproject1.controller;

import com.test.toyproject1.entity.Item;
import com.test.toyproject1.entity.ItemBook;
import com.test.toyproject1.service.ItemService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class ItemController {
    private Logger logger = Logger.getLogger("ItemController");

    final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 상품 목록
     */
    @RequestMapping(value = "/items", method = GET)
    public String list(Model model) {
        logger.info("list start!!");
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
     * 아이템 생성
     * @return
     */
    @RequestMapping(value = "/items/new", method = GET)
    public String createForm() {
        logger.info("createForm start!!");
        return "items/createItemForm";
    }

    /**
     * 아이템 생성 후 목록으로 이동
     * @param book
     * @return
     */
    @RequestMapping(value = "/items/new", method = POST)
    public String create(ItemBook book) {
        logger.info("create start!!");
        itemService.saveItem(book);
        return "redirect:/items";
    }

    /**
     * 아이템 수정화면 이동
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping(value = "/items/{itemId}/edit", method = GET)
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        logger.info("updateItemForm start!!");
        Item item = itemService.findOne(itemId);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }

    /**
     * 아이템 수정
     * @param item
     * @param model
     * @return
     */
    @RequestMapping(value = "/items/{itemId}/edit", method = POST)
    public String update(ItemBook item, Model model) {
        logger.info("update start!!");
        itemService.saveItem(item);
        return "redirect:/items";
    }
}

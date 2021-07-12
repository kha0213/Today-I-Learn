package com.test.toyproject1.controller;

import com.test.toyproject1.entity.Item;
import com.test.toyproject1.entity.Member;
import com.test.toyproject1.exception.NotEnoughStockException;
import com.test.toyproject1.search.OrderSearch;
import com.test.toyproject1.service.ItemService;
import com.test.toyproject1.service.MemberService;
import com.test.toyproject1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class OrderController {
    private Logger logger = Logger.getLogger("OrderController");

    final OrderService orderService;
    final MemberService memberService;
    final ItemService itemService;

    @Autowired
    public OrderController(OrderService orderService, MemberService memberService, ItemService itemService) {
        this.orderService = orderService;
        this.memberService = memberService;
        this.itemService = itemService;
    }

    /**
     * 주문화면 보기
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/order", method = GET)
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }

    /**
     * 주문 로직
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @RequestMapping(value = "/order", method = POST)
    public String order(Long memberId, Long itemId, int count, RedirectAttributes redirect) {
        try {
            orderService.order(memberId,itemId,count);
        } catch (NotEnoughStockException e) {
            logger.info("ERROR : " +e.getMessage());
        }
        redirect.addAttribute("memberId", memberId);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders", method = GET)
    public String orderView(OrderSearch orderSearch, Model model) {
        logger.info("[orderView] orderSearch : " + orderSearch);
        model.addAttribute("orders", orderService.findOrders(orderSearch));
        return "order/orderView";
    }
}

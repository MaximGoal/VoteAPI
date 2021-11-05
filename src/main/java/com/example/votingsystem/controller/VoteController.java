package com.example.votingsystem.controller;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.Vote;
import com.example.votingsystem.service.Voter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VoteController {

    private Voter voter;

    public VoteController(Voter voter) {
        this.voter = voter;
    }

    @GetMapping("/{userId}")
    public String basePage(@PathVariable("userId") Integer id,
            Model model) {
        LocalDate date = LocalDate.now();
        List<Restaurant> restaurantList = voter.getRestaurants();
        Map<Restaurant, Menu> map = new HashMap<>();
        for (int i = 0; i < restaurantList.size(); i++) {
            Restaurant restaurant = restaurantList.get(i);
            Menu menuByDateAndRestaurant = voter.getMenuByDateAndRestaurant(date, restaurant);
            map.put(restaurant, menuByDateAndRestaurant);
        }
        model.addAttribute("restaurantMenuMap", map);
        model.addAttribute("restaurants", map.keySet());

        model.addAttribute("user", voter.getUserById(id));
        model.addAttribute("date", date);

        return "menuList";
    }


    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView("users.html");
        modelAndView.addObject("usersList", voter.getAllUsers());
        return modelAndView;
    }

    @GetMapping ("/vote/{menuId}")
    public String vote(@PathVariable("menuId") Integer menuId,
                       @RequestParam("userId") Integer userId) {
        voter.vote(userId, menuId);

        return "redirect:/"+userId;
    }

    @GetMapping ("/unvote/{menuId}")
    public String unVote(@PathVariable("menuId") Integer menuId,
                       @RequestParam("userId") Integer userId) {
        voter.unVote(userId, menuId);

        return "redirect:/"+userId;
    }
}

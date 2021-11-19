package com.example.votingsystem.controller;

import com.example.votingsystem.exception.VoteException;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.RestaurantPage;
import com.example.votingsystem.model.Vote;
import com.example.votingsystem.service.VoteApiService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VoteController {

    private VoteApiService voteApiService;

    public VoteController(VoteApiService voteApiService) {
        this.voteApiService = voteApiService;
    }

    @GetMapping("/{userId}")
    public String basePage(@PathVariable("userId") Integer id,
                           Model model) {
        LocalDate date = LocalDate.now();
        List<Restaurant> restaurantList = voteApiService.getRestaurants();

        Map<Restaurant, Menu> map = new HashMap<>();
        for (Restaurant restaurant : restaurantList) {
            Menu menuByDateAndRestaurant = voteApiService.getMenuByDateAndRestaurant(date, restaurant);
            map.put(restaurant, menuByDateAndRestaurant);
        }
        model.addAttribute("restaurantMenuMap", map);
        model.addAttribute("restaurants", map.keySet());
        model.addAttribute("user", voteApiService.getUserById(id));
        model.addAttribute("date", date);

        return "menuList";
    }

    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView("users.html");
        modelAndView.addObject("usersList", voteApiService.getAllUsers());

        return modelAndView;
    }

    @GetMapping("/vote/{menuId}")
    public String vote(@PathVariable("menuId") Integer menuId,
                       @RequestParam("userId") Integer userId,
                       Model model) {
        try {
            voteApiService.vote(userId, menuId);
            model.addAttribute("userId", userId);
            return "redirect:/" + userId;
        } catch (Exception e) {
            throw new VoteException("Error in user:" + userId + " voting.", userId);
        }
    }

    @GetMapping("/unvote/{menuId}")
    public String unVote(@PathVariable("menuId") Integer menuId,
                         @RequestParam("userId") Integer userId,

                         @RequestParam("date")
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        voteApiService.unVote(userId, menuId, date);

        return "redirect:/" + userId;
    }

    @GetMapping("/delete/vote/{id}")
    public String unVote(@PathVariable("id") Integer id) {
        voteApiService.deleteVote(id);

        return "redirect:/allVotes";
    }

    @GetMapping("/allVotes")
    public String allVotes(Model model) {
        List<Vote> voteList = voteApiService.getVotes();
        voteList.sort(Comparator.comparing(Vote::getDate));
        model.addAttribute("votes", voteList);
        model.addAttribute("date", LocalDate.now());
        return "/votesList";
    }
}

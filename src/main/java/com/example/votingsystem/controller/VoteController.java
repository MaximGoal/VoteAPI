package com.example.votingsystem.controller;

import com.example.votingsystem.exception.VoteException;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.RestaurantPage;
import com.example.votingsystem.model.Vote;
import com.example.votingsystem.service.VoteApiService;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class VoteController {

    private VoteApiService voteApiService;

    public VoteController(VoteApiService voteApiService) {
        this.voteApiService = voteApiService;
    }

    @GetMapping("/{userId}")
    public String basePage(@PathVariable("userId") Integer id,
                           Model model,
                           RestaurantPage restaurantPage
    ) {
//                           @RequestParam(value = "pageNumber", required = false, defaultValue = "1") String pageNumberStr,
//                           @RequestParam(value = "pageSize", required = false, defaultValue = "3") String pageSizeStr,
//                           @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy
        LocalDate date = LocalDate.now();
        List<Restaurant> restaurantList = voteApiService.getRestaurants();

        Map<Restaurant, Menu> map = new HashMap<>();
        for (Restaurant restaurant : restaurantList) {
            Menu menuByDateAndRestaurant = voteApiService.getMenuByDateAndRestaurant(date, restaurant);
            if (menuByDateAndRestaurant != null)
                map.put(restaurant, menuByDateAndRestaurant);
        }

//        TODO: "restaurantMenuMap" map transfer to view like Page object

        int pageNumber = restaurantPage.getPageNumber();
        int pageSize = restaurantPage.getPageSize();
        String sortBy = restaurantPage.getSortBy();

        if (pageNumber < 1) {pageNumber = 1;}
        if (pageSize < 1) {pageSize = 1;}

        ArrayList<Restaurant> sortedListOfRestaurants = new ArrayList<>(map.keySet());
        switch(sortBy) {
            case "id": sortedListOfRestaurants.sort(Comparator.comparing(Restaurant::getId)); break;
            case "name": sortedListOfRestaurants.sort(Comparator.comparing(Restaurant::getName)); break;
            case "totalPrice": sortedListOfRestaurants.sort((o1, o2) -> {
                Double totalPrice1 = map.get(o1).getTotalPrice();
                Double totalPrice2 = map.get(o2).getTotalPrice();
                return totalPrice1>totalPrice2 ? 1 : (totalPrice1.equals(totalPrice2) ? 0 : -1);
            }); break;
        }

//        3 parameter of PageRequest.of() - Sort.by(Sort.Direction.ASC, sortBy) is just for transfer proper sortBy parameter into view model ( page.sortBy )
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
//        https://stackoverflow.com/questions/37749559/conversion-of-list-to-page-in-spring
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), sortedListOfRestaurants.size());
        final Page<Restaurant> page = new PageImpl<>(   sortedListOfRestaurants.subList(start, end),
                                                        pageable,
                                                        sortedListOfRestaurants.size());
//        Page<Restaurant> page = new PageImpl<>(
//                sortedListOfRestaurants,
//                PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, restaurantPage.getSortBy())),
//                map.size());
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("restaurantPage", restaurantPage);
        model.addAttribute("page", page);
        model.addAttribute("sortBy", sortBy);
//        model.addAttribute("pageContent", page.getContent());
//        model.addAttribute("restaurantSize", map.size());

        // after pagination
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

    @GetMapping("/{userId}/allVotes")
    public String allVotes(@PathVariable ("userId") Integer userId, Model model) {
        List<Vote> voteList = voteApiService.getVotes();
        voteList.sort(Comparator.comparing(Vote::getDate));
        model.addAttribute("votes", voteList);
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("userId", userId);
        return "/votesList";
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    RedditPostRepository postRepository;

    @RequestMapping("/")
    public String listPosts(Model model){
        model.addAttribute("posts", postRepository.findAll());
        return "homepage";
    }

    @GetMapping("/add")
    public String postForm(Model model){
        model.addAttribute("addpost", new RedditPosts());
        return "addpostpage";
    }

    @PostMapping("/add")
    public String postedForm(Model model){
        model.addAttribute("addpost", new RedditPosts());
        return "addpostpage";
    }

    @PostMapping("/process")
    public String processForm(@Valid RedditPosts redposts, BindingResult result){
        if (result.hasErrors()){
            return "addpostpage";
        }
        postRepository.save(redposts);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchPosts(@RequestParam("searchUser") String searchUser, Model model){

        model.addAttribute("posts",postRepository.findAllByUserIgnoreCase(searchUser));
        return "userpage";
    }
}

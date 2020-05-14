package com.example.demo.Movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MovieController {

    private MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        return "addForm";
    }

    @GetMapping("/movieList")
    public String showToDoList(Model model, @RequestParam MovieCategory category) {
        model.addAttribute("list", movieRepository.findByCategory(category));
        return "list";
    }

    @PostMapping("/add")
    public String add(Movie movie) {
        try {
            movieRepository.addMovie(movie);
            return "redirect:/";
        } catch (Exception ex) {
            return "error";
        }
    }

}

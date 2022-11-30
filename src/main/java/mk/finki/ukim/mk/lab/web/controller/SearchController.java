package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.service.SearchService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public String getSearchPage(@RequestParam String search, @NotNull Model model){
        model.addAttribute("search",searchService.searchByString(search));
        return "search";
    }
}

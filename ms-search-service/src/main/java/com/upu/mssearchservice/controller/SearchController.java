package com.upu.mssearchservice.controller;

import com.upu.mssearchservice.model.ThesisDocument;
import com.upu.mssearchservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/theses")
    public Flux<ThesisDocument> searchTheses(@RequestParam String query) {
        return searchService.searchTheses(query);
    }
}

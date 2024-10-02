package com.upu.mssearchservice.service;

import com.upu.mssearchservice.model.ThesisDocument;
import com.upu.mssearchservice.repository.ThesisSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class SearchService {

    @Autowired
    private ThesisSearchRepository thesisSearchRepository;

    public Flux<ThesisDocument> searchTheses(String query) {
        return Flux.fromIterable(thesisSearchRepository.search(queryStringQuery(query)));
    }
}

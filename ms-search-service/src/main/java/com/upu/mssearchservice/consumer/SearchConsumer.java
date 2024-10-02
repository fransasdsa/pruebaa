package com.upu.mssearchservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upu.mssearchservice.model.ThesisDocument;
import com.upu.mssearchservice.repository.ThesisSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class SearchConsumer {

    @Autowired
    private ThesisSearchRepository thesisSearchRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @StreamListener("input")
    public void handleSearchEvent(String eventPayload) {
        try {
            ThesisDocument thesis = objectMapper.readValue(eventPayload, ThesisDocument.class);
            thesisSearchRepository.save(thesis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

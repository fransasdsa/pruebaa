package com.upu.mssearchservice.repository;

import com.upu.mssearchservice.model.ThesisDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ThesisSearchRepository extends ElasticsearchRepository<ThesisDocument, String> {
}

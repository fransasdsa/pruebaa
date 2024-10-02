package com.upu.mssearchservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "theses")
public class ThesisDocument {
    @Id
    private String id;
    private String title;
    private String abstractText;
    private String author;
    private String advisor;
    private String keywords;
    private String department;
}

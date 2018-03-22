package com.example.controller.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolrController {

    @Autowired
    private SolrClient client;

    @RequestMapping("/index/solr")
    public String testSolr() throws IOException, SolrServerException {
        SolrDocument document = client.getById("test", "fe7a5124-d75b-40b2-93fe-5555512ea6d2");
        System.out.println(document);
        return document.toString();
    }
}


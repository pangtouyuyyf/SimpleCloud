package com.simple.base.component;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Description elastic search restful client
 * Author chen
 * CreateTime 2020-04-19 10:40
 **/
public enum ElsClient {
    INSTANCE;

    private RestHighLevelClient client;

    ElsClient() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    public RestHighLevelClient getInstance() {
        return client;
    }
}

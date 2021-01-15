package com.simple.base.service.impl;

import com.simple.base.component.ElsClient;
import com.simple.base.service.ElsService;
import com.simple.common.util.LogUtil;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * Description elastic search 操作服务接口实现
 * Author chen
 * CreateTime 2018-09-06 17:10
 **/
@Service
public class ElsServiceImpl implements ElsService {
    RestHighLevelClient client = ElsClient.INSTANCE.getInstance();

    public void get() {
        try {
            //根据id查单条信息
            GetRequest grq = new GetRequest("simple", "1");
            GetResponse grp = client.get(grq, RequestOptions.DEFAULT);
            System.out.println("------------------------get-------------------------");
            System.out.println(grp.getSource());
            System.out.println("-------------------------------------------------");

            //根据index[]查询
            SearchRequest srq = new SearchRequest("simple");
            SearchResponse srp = client.search(srq, RequestOptions.DEFAULT);
            System.out.println("----------------------search---------------------------");
            System.out.println(srp.getHits().getHits());
            System.out.println("-------------------------------------------------");

            //[put]update
            IndexRequest irq = new IndexRequest("simple");
            irq.id("1").source("user", "kimchy",
                            "postDate", new Date(),
                            "message", "trying out Elasticsearch");
            IndexResponse irp = client.index(irq, RequestOptions.DEFAULT);
            System.out.println("------------------------index-------------------------");
            System.out.println(irp.getResult());
            System.out.println("-------------------------------------------------");




        } catch (IOException e) {
            LogUtil.error(ElsServiceImpl.class, e.getMessage());
        } finally {
//            这部分开放出来下一次操作会抛异常,但是官网上提示是要这么写,bug未修复
//            try {
//                if (client != null) {
//                    client.close();
//                }
//            } catch (IOException e) {
//                LogUtil.error(ElsServiceImpl.class, e.getMessage());
//            }
        }
    }
}

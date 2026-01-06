//package com.example.tutorial;
//
//import com.example.entity.Traveler;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.springframework.web.client.RestClient;
//
//import java.io.IOException;
//
///**
// * @author : neo
// **/
//public class ESDemo {
//    public static void main(String[] args) throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost", 9200, "http")));
//
//        // 创建索引
//        CreateIndexRequest request = new CreateIndexRequest("my_index");
//        client.indices().create(request, RequestOptions.DEFAULT);
//
//        // 添加文档
//        IndexRequest indexRequest = new IndexRequest("my_index")
//                .source(XContentType.JSON, "field", "value");
//        client.index(indexRequest, RequestOptions.DEFAULT);
//
//        // 关闭客户端
//        client.close();
//
//        Traveler traveler = new Traveler().setTravelerId(1L).setPassport("TTT").setGender("M").setName("abc");
//
//
//    }
//}

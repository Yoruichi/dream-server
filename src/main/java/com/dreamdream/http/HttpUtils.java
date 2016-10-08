package com.dreamdream.http;

import java.util.function.Function;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
    private static final HttpClient client = HttpClientBuilder.create().build();
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static <R> R doPost(String uri, Function<String, R> callback) throws Exception {
        return doPost(new HttpPost(uri), callback);
    }

    public static <R> R doPost(HttpPost post, Function<String, R> callback) throws Exception {
        HttpResponse response = client.execute(post);
        String resp = EntityUtils.toString(response.getEntity());
        logger.info("Process POST request {} and got response {}", post.getURI(), resp);
        return callback.apply(resp);
    }

    public static <R> R doGet(String uri, Function<String, R> callback) throws Exception {
        return doGet(new HttpGet(uri), callback);
    }

    public static <R> R doGet(HttpGet get, Function<String, R> callback) throws Exception {
        HttpResponse response = client.execute(get);
        String resp = EntityUtils.toString(response.getEntity());
        logger.info("Process GET request {} and got response {}", get.getURI(), resp);
        return callback.apply(resp);
    }
}

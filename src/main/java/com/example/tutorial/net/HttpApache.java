package com.example.tutorial.net;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Apache Http demo
 */
public class HttpApache {
    public static void main(String[] args) {
        callCtripOpenApi();

    }

    public static void callCtripOpenApi() {
        CloseableHttpResponse response = null;
        InputStream inputStream = null;
        HttpUriRequest httpUriRequest = null;

        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            String requestUri = "https://stg-cloud.authorization.go.com/token";
            CloseableHttpClient httpClient = httpClientBuilder.build();

            httpClient = HttpClients.createDefault();
            Map<String, String> headers = new HashMap<>();
            headers.put("Accept", "*/*");
            headers.put("Content-Type", "application/x-www-form-urlencoded");

            JSONObject bodyJSON = new JSONObject()
                    .fluentPut("grant_type", "client_credentials")
                    .fluentPut("scope", "RETURN_ALL_CLIENT_SCOPES")
                    .fluentPut("client_id", "TPR-CTRIP.B2B-STAGE")
                    .fluentPut("client_secret", "52Px7KP5~NO.wrg6iJqbRjbgWf7jC8yV");

            String encodeName = "utf-8";
            HttpPost httpPost = new HttpPost(requestUri);
            /* httpPost.setEntity(new ByteArrayEntity(JSONObject.toJSONString(requestBody).getBytes(encodeName)));
             */

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }

            List<NameValuePair> form = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : bodyJSON.entrySet()) {
                form.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(form, encodeName);
            httpPost.setEntity(encodedFormEntity);
            httpUriRequest = httpPost;

            HttpClientContext context = HttpClientContext.create();
            response = httpClient.execute(httpUriRequest, context);

            inputStream = response.getEntity().getContent();
            int resCode = response.getStatusLine().getStatusCode();
            System.out.println("resCode=" + resCode);

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1000];
                int ri = 0;
                while ((ri = inputStream.read(buff, 0, 1000)) > 0) {
                    swapStream.write(buff, 0, ri);
                }
                String resBody = new String(swapStream.toByteArray(), encodeName);
                System.out.println("resBody=" + resBody);
            } else {
                throw new Exception(String.format("%s %s", response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase()));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (httpUriRequest != null) {
                httpUriRequest.abort();
            }
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }

    }

    public static void getRequest() {
        CloseableHttpResponse response = null;
        InputStream inputStream = null;
        HttpGet httpGet = null;
        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            String requestUri = "https://stg-cloud.authorization.go.com/token";
            CloseableHttpClient httpClient = httpClientBuilder.build();

            httpClient = HttpClients.createDefault();
            Map<String, String> headers = new HashMap<String, String>(){{
                put("Accept", "*/*");
                put("Content-Type", "application/x-www-form-urlencoded");
            }};

            JSONObject bodyJSON = new JSONObject()
                    .fluentPut("grant_type", "client_credentials")
                    .fluentPut("scope", "RETURN_ALL_CLIENT_SCOPES")
                    .fluentPut("client_id", "TPR-CTRIP.B2B-STAGE")
                    .fluentPut("client_secret", "52Px7KP5~NO.wrg6iJqbRjbgWf7jC8yV");

            String encodeName = "utf-8";

            httpGet = new HttpGet(requestUri);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }

            HttpClientContext context = HttpClientContext.create();
            response = httpClient.execute(httpGet, context);

            inputStream = response.getEntity().getContent();
            int resCode = response.getStatusLine().getStatusCode();
            System.out.println("resCode=" + resCode);

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1000];
                int ri = 0;
                while ((ri = inputStream.read(buff, 0, 1000)) > 0) {
                    swapStream.write(buff, 0, ri);
                }
                String resBody = new String(swapStream.toByteArray(), encodeName);
                System.out.println("resBody=" + resBody);
            } else {
                throw new Exception(String.format("%s %s", response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase()));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }
}

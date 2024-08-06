package com.example.tutorial.net;

import com.alibaba.fastjson.JSON;
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
import org.springframework.util.StopWatch;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestDemo {
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();

        sw.start();

        try{

            Thread.sleep(1000);
//            if(1==1)
//            throw new IllegalArgumentException("tt");
            sw.stop();
        }catch (Exception e){

        }finally {
            System.out.println(sw.getTotalTimeMillis());
        }

    }

    public static void callCtripOpenApi() {
        CloseableHttpResponse response = null;
        InputStream inputStream = null;
        HttpUriRequest httpUriRequest = null;

        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            String requestUri = "https://authent-sandbox.era.raileurope.com/oauth2/token";
            CloseableHttpClient httpClient = httpClientBuilder.build();

            httpClient = HttpClients.createDefault();
            Map<String, String> headers = new HashMap<>();
            headers.put("Accept", "*/*");
            headers.put("Content-Type", "application/x-www-form-urlencoded");

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("grant_type", "client_credentials");
            requestBody.put("client_id", "4bk806i04jgr10j2s5p71hega8");
            requestBody.put("client_secret", "1qkpf0giumi0dh6pf19trnr69jhpgqs0aflnbcfe3d774vurqm54");

            String encodeName = "utf-8";
            String method = "POST";
            if ("POST".equals(method)) {
                HttpPost httpPost = new HttpPost(requestUri);
                /* httpPost.setEntity(new ByteArrayEntity(JSONObject.toJSONString(requestBody).getBytes(encodeName)));
                 */
                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        httpPost.setHeader(entry.getKey(), entry.getValue());
                    }
                }


                List<NameValuePair> form = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : requestBody.entrySet()) {
                    form.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(form, encodeName);
                httpPost.setEntity(encodedFormEntity);
                httpUriRequest = httpPost;
            } else {
                HttpGet httpGet = new HttpGet(requestUri);
                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        httpGet.setHeader(entry.getKey(), entry.getValue());
                    }
                }
                httpUriRequest = httpGet;
            }

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
}

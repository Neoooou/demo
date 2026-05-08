package com.example.tutorial.net;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP 响应封装类
 */
public class HttpResponse {
    
    private final int statusCode;
    private final String statusText;
    private final Map<String, String> headers;
    private final String content;
    
    public HttpResponse(int statusCode, String statusText, Map<String, String> headers, String content) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.headers = headers;
        this.content = content;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public String getStatusText() {
        return statusText;
    }
    
    public Map<String, String> getHeaders() {
        return headers;
    }
    
    public String getHeader(String name) {
        return headers.get(name);
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP Response:\n");
        sb.append("  Status: ").append(statusCode).append(" ").append(statusText).append("\n");
        sb.append("  Headers:\n");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            sb.append("    ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("  Content Length: ").append(content != null ? content.length() : 0).append(" bytes\n");
        return sb.toString();
    }
}

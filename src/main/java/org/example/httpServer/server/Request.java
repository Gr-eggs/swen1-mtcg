package org.example.httpServer.server;



import org.example.httpServer.http.ContentType;
import org.example.httpServer.http.Method;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private Method method;
    private String urlContent;
    private String pathname;
    private List<String> pathParts;
    private String params;
    private HeaderMap headerMap =  new HeaderMap();
    private String body;
    private String payload;

    private ContentType contentType;

    private int contentLength;

    public void setPayload(String payload){
        this.payload = payload;
    }

    public String getPayload(){
        return payload;
    }



    public void setContentType(ContentType contentType){
        this.contentType = contentType;
    }

    public ContentType getContentType(){
        return contentType;
    }


    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public int getContentLength(){
        return contentLength;
    }

    public String getServiceRoute(){
        if (this.pathParts == null ||
                this.pathParts.isEmpty()) {
            return null;
        }

        return '/' + this.pathParts.get(0);
    }

    public static Method parseMethod(String line){
        if(line.startsWith(Method.GET.name())){
            return Method.GET;
        }else if(line.startsWith(Method.DELETE.name())){
            return Method.DELETE;
        }else if(line.startsWith(Method.PATCH.name())){
            return Method.PATCH;
        }else if(line.startsWith(Method.POST.name())){
            return Method.POST;
        }else if(line.startsWith(Method.PUT.name())){
            return Method.PUT;
        }

        throw new RuntimeException("Invalid Line passed. Could not determine method");
    }

    public static String parsePath(String requestLine){
        String[] requestParts = requestLine.split(" ");
       return  requestParts[1];
    }



    public String getUrlContent(){
        return this.urlContent;
    }

    public void setUrlContent(String urlContent) {
        this.urlContent = urlContent;
        Boolean hasParams = urlContent.indexOf("?") != -1;

        if (hasParams) {
            String[] pathParts =  urlContent.split("\\?");
            this.setPathname(pathParts[0]);
            this.setParams(pathParts[1]);
        }
        else
        {
            this.setPathname(urlContent);
            this.setParams(null);
        }
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getPathname() {
        return pathname;
    }


    public void setPathname(String pathname) {
        this.pathname = pathname;
        String[] stringParts = pathname.split("/");
        this.pathParts = new ArrayList<>();
        for (String part :stringParts)
        {
            if (part != null &&
                    part.length() > 0)
            {
                this.pathParts.add(part);
            }
        }

    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public HeaderMap getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(HeaderMap headerMap) {
        this.headerMap = headerMap;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getPathParts() {
        return pathParts;
    }

    public void setPathParts(List<String> pathParts) {
        this.pathParts = pathParts;
    }
}

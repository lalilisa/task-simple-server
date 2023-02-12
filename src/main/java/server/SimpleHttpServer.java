package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import payload.Response;
import util.JsonObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress=new InetSocketAddress("localhost",8080);
        HttpServer server=HttpServer.create(inetSocketAddress,0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        
    }
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            if(t.getRequestMethod().equalsIgnoreCase("GET")){
                File file=new File("index.html");
                FileInputStream fileInputStream=new FileInputStream(file);
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fileInputStream,StandardCharsets.UTF_8));
                StringBuilder buf=new StringBuilder();
                String line=null;
                while ((line = bufferedReader.readLine()) != null) {
                    buf.append(line);
                }
                String response =buf.toString();
                Headers h = t.getResponseHeaders();
                h.put("Content-Type", Collections.singletonList("text/html;charset=UTF-8"));
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                t.sendResponseHeaders(200, bytes.length);
                OutputStream os = t.getResponseBody();
                os.write(bytes);
                os.flush();
                os.close();
            }
            if(t.getRequestMethod().equalsIgnoreCase("POST")){

                BufferedReader in = new BufferedReader(new InputStreamReader(t.getRequestBody(),"UTF-8"));
                String line=null;
                StringBuilder stringBuilder=new StringBuilder();
                while ((line=in.readLine())!=null){
                    System.out.println(line);
                    stringBuilder.append(line);
                }
                String params=stringBuilder.toString();
                Map<String,String> map= JsonObject.parser(params);
                String name=map.get("name");
                Headers h = t.getResponseHeaders();
                String response=new Response(name,new Date()).toString();
                System.out.println(response);
                h.put("Content-Type", Collections.singletonList("application/json"));
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                t.sendResponseHeaders(200, bytes.length);
                OutputStream os = t.getResponseBody();
                os.write(bytes);
                os.flush();
                os.close();
            }

        }
    }
}

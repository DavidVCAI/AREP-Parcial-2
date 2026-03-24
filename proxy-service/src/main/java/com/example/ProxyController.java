package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    // String server1 = "http://localhost:8081";
    String server1 = "http://52.23.206.118:8080";
    String server2 = "http://3.93.150.133:8080";

    @GetMapping("/proxy")
    public String proxy(@RequestParam(value = "cmd") String cmd) {

        String res = callGet(server1 + "/" + cmd);
        if (res == null) {
            res = callGet(server2 + "/" + cmd);
        }
        if (res == null) {
            return "Ambos servidores sin conexion o el numero es muy grande.";
        }
        return res;
    }

    String callGet(String urlStr) {
        try {
            URL obj = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(2000);
            con.setReadTimeout(2000);
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            System.out.println("fallo: " + urlStr);
        }
        return null;
    }
}

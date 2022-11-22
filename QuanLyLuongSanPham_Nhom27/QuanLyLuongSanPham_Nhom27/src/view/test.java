/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author December
 */
public class test {

    public static void main(String[] args) throws IOException {
        try {

            String username = "admin";
            String password = "123456";
            String to = "+84327060328";
            String message = "Hello Vu";

            String requestUrl = "http://localhost:9710/http/send-message?"
                    + "username=" + URLEncoder.encode(username, "UTF-8")
                    + "&password=" + URLEncoder.encode(password, "UTF-8")
                    + "&to=" + URLEncoder.encode(to, "UTF-8")
                    + "&message-type=sms.automatic"
                    + "&message=" + URLEncoder.encode(message, "UTF-8");
            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            System.out.println(uc.getResponseMessage());
            uc.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}

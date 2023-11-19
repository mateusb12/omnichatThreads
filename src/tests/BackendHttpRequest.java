package tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import classes.HttpConnectionProperties;
import common.utils;
import mocks.ReqPropertiesMocks;
import org.json.JSONObject;

public class BackendHttpRequest {

    public static void main(String[] args) {
        try {

            String inputCellValue = "linguiça com requeijão";
            String selectedUrl = "http://localhost:8080/twilioSandbox";
            HashMap<String, String> headers = new HashMap<>();
            headers.put("CustomIp", utils.generateRandomIp());
            HttpConnectionProperties properties = ReqPropertiesMocks.reqProperties1;
            String response = getBotResponseFromFlask(inputCellValue, selectedUrl, headers, properties);
            System.out.println("Received response from server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBotResponseFromFlask(String messageContent, String desiredUrl, HashMap<String, String> headers, HttpConnectionProperties reqProperties ) throws Exception {
        URL url = new URL(desiredUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        reqProperties.setBody(messageContent);
        reqProperties.applyProperties(connection);


        for (Map.Entry<String, String> header : headers.entrySet()) {
            connection.setRequestProperty(header.getKey(), header.getValue());
        }

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = messageContent.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Server returned a non-200 response: " + connection.getResponseCode());
        }

        connection.disconnect();
        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getString("message");
    }
}

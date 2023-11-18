import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class BackendHttpRequest {

    public static void main(String[] args) {
        try {
            String inputCellValue = "linguiça com requeijão";
            String selectedUrl = "http://localhost:8080/twilioSandbox";
            HashMap<String, String> headers = new HashMap<>();
            headers.put("CustomIp", utils.generateRandomIp());
            String response = getBotResponseFromFlask(inputCellValue, selectedUrl, headers);
            System.out.println("Received response from server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBotResponseFromFlask(String messageContent, String desiredUrl, HashMap<String, String> headers ) throws Exception {
        URL url = new URL(desiredUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setRequestProperty("ProfileName",
                java.net.URLEncoder.encode("Mateus", StandardCharsets.UTF_8));
        connection.setRequestProperty("From",
                java.net.URLEncoder.encode("whatsapp:+558599171902", StandardCharsets.UTF_8));
        connection.setRequestProperty("WaId", "558599171902");
        connection.setRequestProperty("Body",messageContent);
        connection.setDoOutput(true);

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

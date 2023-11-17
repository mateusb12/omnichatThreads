import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class BackendHttpRequest {

    public static void main(String[] args) {
        try {
            String inputCellValue = "Oiii";
            String selectedUrl = "http://localhost:3000/twilioSandbox";

            String response = getBotResponseFromFlask(inputCellValue, selectedUrl);
            System.out.println("Received response from server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getBotResponseFromFlask(String messageContent, String desiredUrl) throws Exception {
        URL url = new URL(desiredUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setRequestProperty("ProfileName",
                java.net.URLEncoder.encode("Mateus", StandardCharsets.UTF_8));
        connection.setRequestProperty("From",
                java.net.URLEncoder.encode("whatsapp:+558599171902", StandardCharsets.UTF_8));
        connection.setRequestProperty("WaId", "558599171902");
        connection.setRequestProperty("Body",
                java.net.URLEncoder.encode(messageContent, StandardCharsets.UTF_8));
        connection.setDoOutput(true);

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

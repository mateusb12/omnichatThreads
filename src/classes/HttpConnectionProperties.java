package classes;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HttpConnectionProperties {
    private String profileName;
    private String from;
    private String waId;
    private String body;

    // Construtor
    public HttpConnectionProperties(String profileName, String from) {
        this.setProfileName(profileName);
        this.setFrom(from);
        String[] parts = from.split("\\+");
        if (parts.length > 0) {
            this.setWaId(parts[parts.length - 1]); // Obter o último elemento
        } else {
            this.setWaId(""); // Ou defina um valor padrão se 'from' estiver vazio
        }

    }

    public void setProfileName(String profileName) {
        this.profileName = URLEncoder.encode(profileName, StandardCharsets.UTF_8);
    }

    public void setFrom(String from) {
        this.from = URLEncoder.encode(from, StandardCharsets.UTF_8);
    }

    public void setWaId(String waId) {
        this.waId = waId;
    }

    public void setBody(String body){
        this.body = body;
    }

    public void applyProperties(HttpURLConnection connection) throws ProtocolException {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setRequestProperty("ProfileName", this.profileName);
        connection.setRequestProperty("From", this.from);
        connection.setRequestProperty("WaId", this.waId);
        connection.setRequestProperty("Body", this.body);
        connection.setDoOutput(true);
    }
}

package xyz.zielinus.mhider.utils;

import com.destroystokyo.paper.profile.ProfileProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ApiClient {

    public static String getUUID(String username) {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(String.format("https://api.mojang.com/users/profiles/minecraft/%s", username)).openConnection();
            if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                return null;
            }

            String content = new BufferedReader(new InputStreamReader(conn.getInputStream())).readLine();

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(content);
            JSONObject json = (JSONObject) obj;

            return (String) json.get("id");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProfileProperty getSkin(String uuid) {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", uuid)).openConnection();
            if (conn.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String content = "";

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                content += String.format("%s\n", line);
            }

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(content);
            JSONObject json = (JSONObject) obj;

            JSONArray properties = (JSONArray) json.get("properties");

            JSONObject skinObject = (JSONObject) properties.get(0);

            String skin = (String) skinObject.get("value");
            String signature = (String) skinObject.get("signature");

            return new ProfileProperty("textures", skin, signature);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}

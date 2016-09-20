import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AndroidPush {

    /**
     * Replace API_KEY with your API_KEY/SENDER_ID
     * Replace DEVICE_TOKEN with your DEVICE_TOKEN
     */
    private static String API_KEY = "YOUR_API_KEY OR SENDER_ID";
    private static String DEVICE_TOKEN = "YOUR_DEVICE_TOKEN";

    public static void main(String[] args) throws Exception {
        // Prepare JSON containing the FCM message content. What to send and where to send.
        JSONObject jFcmData = new JSONObject();
        JSONObject jData = new JSONObject();
        jData.put("title", "My First Notification");
        jData.put("message", "Hello, I'm push notification");

        jFcmData.put("to", DEVICE_TOKEN);
        // What to send in FCM message.
        jFcmData.put("data", jData);

        // Create connection to send FCM Message request.
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "key=" + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // Send FCM message content.
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(jFcmData.toString().getBytes());

        System.out.println(conn.getResponseCode());
        System.out.println(conn.getResponseMessage());
    }
}

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestByURLConnection {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=incheon&appid=96f77190b2dd2283a5502b6eeb3a8cbc");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setReadTimeout(30000);

            int responseCode = connection.getResponseCode();
            System.out.println("HTTP 상태 코드 : " + responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("커넥션 에러 발생");
                System.out.println(connection.getResponseMessage());
                return;
            }

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;

            String filePath = System.getProperty("user.dir") + "/REST_API/src/output.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                byte[] bytes = line.getBytes();
                for (byte b : bytes) {
                    fileOutputStream.write(b);
                }
            }
            fileOutputStream.close();
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

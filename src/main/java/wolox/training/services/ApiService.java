package wolox.training.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public abstract class ApiService {
    String obtainResponse(HttpURLConnection con) throws IOException {
        StringBuffer response = new StringBuffer();
        String inputLine;

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

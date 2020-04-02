package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendInHttp implements Runnable {
    private String jsonInputString = "{'name': 'hyunro', 'job': 'programmer'}";

    public SendInHttp(String jsonInputString) {
        this.jsonInputString = jsonInputString;
    }

    @Override
    public void run() {
        OutputStream os = null;
        HttpURLConnection con = null;
        try {
            URL url = new URL ("https://reqres.in/api/users");
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");

            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(os!=null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedReader br = null;
        InputStreamReader isr = null;

        try{
            isr = new InputStreamReader(con.getInputStream(), "utf-8");
            br = new BufferedReader(isr);
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
                if(isr != null) isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

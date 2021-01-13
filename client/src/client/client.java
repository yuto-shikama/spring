package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class client {
	public static void main(String[] args) {
		post("http://localhost:8080/chat");
		get("http://localhost:8080/chat/list");
	}

	static void get(String urlString) {
		try {
        	URL url = new URL(urlString);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestMethod("GET");
			http.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			String xml = "", line = "";
			while((line = reader.readLine()) != null)
			xml += line;
			System.out.println(xml);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		}
	}

	static void post(String urlString) {
        try {
        	URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.connect();

            //送信したいデータ
            String param = "name=client5&message=testMessag";

            //リクエストボディに送信したいデータを書き込む
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(param);
            writer.flush();
            writer.close();

            //クローズ処理
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

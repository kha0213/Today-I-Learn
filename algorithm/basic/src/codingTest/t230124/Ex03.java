package codingTest.t230124;

import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class Ex03 {
    public static void main(String[] args) {
        topArticles(1);
    }


    /*
     * Complete the 'topArticles' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER limit as parameter.
     * base url for copy/paste:
     * https://jsonmock.hackerrank.com/api/articles?page=<pageNumber>
     */

    public static List<String> topArticles(int limit) {
        try {
            URL url = new URL("https://jsonmock.hackerrank.com/api/articles?page=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)  {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();

            String response = stringBuffer.toString();

            
            System.out.println("response = " + response);
        } catch (Exception e) {
        }
        return Collections.singletonList("");
    }
}

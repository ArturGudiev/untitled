package azure;

import utilities.Clipboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.apache.http.protocol.HTTP.USER_AGENT;


public class Azure {
    private String getResponse(String key) throws Exception {

        String url = "https://stathelper.azurewebsites.net/api/HttpTriggerCSharp1?code=yn7OPFSU/" +
                "qFq4GLj7Vj0YLXsq7aipJ8z9cYGoEjUWaaRwaTuyXX22g==&key=" + key;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString().replaceAll("\"","");
    }

    public static void main(String[] args) throws Exception {
        Azure azure = new Azure();
        String response = azure.getResponse(args[0]);
        if("".equals(response)){
            System.out.println("\n\tNo results found!");
            return;
        }
        System.out.println("\n\t" + response);
        try {
            Clipboard.clip(response);
        }catch (Exception e){}
    }
}

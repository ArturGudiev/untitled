package utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class NounGender {
    public static void main(String[] args) throws Exception {
        String content = null;
        String word = args[0];
        word = word.replaceAll("ä", "ae");
        word = word.replaceAll("ö", "oe");
        word = word.replaceAll("ü", "ue");
        URLConnection connection = null;
        try {
            connection =  new URL("https://en.bab.la/dictionary/german-russian/"+word).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
//        System.out.println(content);
        //content= "<table><tr><td>Hello World!</td></tr></table>";
        Document doc = Jsoup.parse(content);

// then use something like this to get your element:
//        Elements tds = doc.getElementsByTag("td");
        Elements tds = doc.getElementsByClass("quick-result-option");
        Document doc2 = Jsoup.parse(tds.toString());
        Elements el = doc2.getElementsByTag("span");
        String answer="";
        try {
             answer = el.text();
            answer = answer.substring(1, 2);
        }catch (Exception e){
            System.out.println(e);
        }
        switch (answer) {
            case("m"):
                System.out.println("\n\t" + "Masculinum");
                break;
            case ("n"):
                System.out.println("\n\t" + "Neutral");
                break;
            case ("f"):
                System.out.println("\n\t" + "Femininum");
                break;
        }
// tds will contain this one element: <td>Hello World!</td>
    }

}


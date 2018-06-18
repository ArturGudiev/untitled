package utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class NounGender {
    public static void main(String[] args) throws Exception {

        Document doc = BaBla.makeRequest(args[0]);

// then use something like this to get your element:
//        Elements tds = doc.getElementsByTag("td");
        Elements tds = doc.getElementsByClass("quick-result-option");
        Document doc2 = Jsoup.parse(tds.toString());
        Elements el = doc2.getElementsByTag("span");
        String answer="";
        try {
             answer = el.text();
//            answer = answer.substring(1, 2);
        }catch (Exception e){
            System.out.println(e);
        }

        if (answer.contains("{m}")) {
            System.out.println("\n\t" + "Masculinum");
        } else if (answer.contains("{n}")) {
            System.out.println("\n\t" + "Neutral");
        } else if (answer.contains("{f}")) {
            System.out.println("\n\t" + "Femininum");
        }

// tds will contain this one element: <td>Hello World!</td>
    }

}


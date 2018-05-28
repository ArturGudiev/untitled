package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class German {
    /*
        Get gender and translation of word;
        ä ö ü ß
     */
    static void bla(String word){
        try {
            Process p=Runtime.getRuntime().exec("cmd /c bla " + word);
            p.waitFor();
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );
            String line;
            while((line = reader.readLine()) != null)
            {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> words = Arrays.asList("Apfel", "Zitrone", "Zwebel",
                "Ei", "Pilz", "Salat","Käse","Brot","Nudeln","Pommes","Kartaffel","Suppe","Fleisch","Fisch","Eis",
                "Kuchen","Schokolade");
        String row= "Obst Zucker Hunger Milch_Tee Durst Saft Eis";
//        for(String word : row.split(" ")){
//            String w = word.replace("_", "%20");
//            bla(w);
//        }
        row = "Birne Brot Kaffee Saft Tee Eis Bier Reis Wasser Nudeln" +
                " Glühwein Suppe Kuchen Wurst Rum";
        for(String word : row.split(" ")){
            bla(word);
        }

    }
}

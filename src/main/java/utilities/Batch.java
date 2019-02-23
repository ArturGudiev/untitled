package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Batch {
    String firstPart = "";
    String secondPart = "";
    String filename = "";
    ArrayList<String> fileLines = new ArrayList<String>();

    Batch(String filename) {
        this.filename = filename;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstPart = true;
            while ((line = br.readLine()) != null) {
                fileLines.add(line);
                if (isFirstPart && line.startsWith("goto end")) {
                    isFirstPart = false;
                    continue;
                }
                if (isFirstPart) {
                    firstPart = firstPart + line + "\n";
                }
                if (!isFirstPart) {
                    secondPart = secondPart + line + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        Batch batch = new Batch("C:\\Programming\\Batch\\azure.bat");
        Batch batch = new Batch(args[0]);
        if ("--modify".equals(args[1])) {
            batch.modify(args[2], args[3], args[4]);
        }
        if ("--echo".equals(args[1])) {
            batch.echo();
        }
        if ("--open".equals(args[1])) {
            batch.open(args[2]);
        }

    }

    private void open(String tag) throws IOException {
        TreeMap<String, List<String>> map = getStringListTreeMap();
        if (!map.keySet().contains(tag)) {
            System.out.println("Contains");
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                List<String> list = entry.getValue();
                if (list.contains(tag)) {
                    tag = key;
                    break;
                }
            }
        }

        for (int i = 0; i < fileLines.size(); i++) {
            String line = fileLines.get(i);
            if (line.toLowerCase().replaceAll(" ", "").startsWith(":" + tag.toLowerCase())) {
//                Runtime.getRuntime().exec("cmd /c start notepad++ " + filename + " -n" + (i + 1));
                Runtime.getRuntime().exec("cmd /c start vscode.lnk --goto " + filename + ":" + (i + 2));
                return;
            }
        }
    }

    private void echo() {
        System.out.println();

        TreeMap<String, List<String>> map = getStringListTreeMap();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            final int length = pair.getKey().toString().length();
            final String spaces = new String(new char[20 - length]).replace("\0", " ");
            System.out.printf("\t %s%s -     %s%n", pair.getKey(), spaces , pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    private TreeMap<String, List<String>> getStringListTreeMap() {
        TreeMap<String, List<String>> map = new TreeMap<String, List<String>>();
        Stream.of(firstPart.split("\n")).forEach(
                str -> {
                    try {
                        Pattern p = Pattern.compile("\"([^\"]*)\"");
                        Matcher m = p.matcher(str);
                        String key = null;
                        String value = null;
                        if (m.find(7)) {
                            value = m.group(1);
                        }
                        p = Pattern.compile("goto (.*)");
                        m = p.matcher(str);
                        if (m.find()) {
                            key = m.group(1);
                        }
                        if(key == null || value == null){return;}
                        if (map.containsKey(key)) {
                            map.get(key).add(value);
                        } else {
                            map.put(key, new ArrayList<String>(Arrays.asList(value)));
                        }
                    } catch (Exception e) {
                    }
                }
        );
        return map;
    }

    private void modify(String tag, String loop, String command) {
        System.out.println(tag + " " + loop + " " + command);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(firstPart);

            Stream.of(tag.split(" ")).forEach(el -> {
                try {
                    bw.write(String.format("if \"%%1\"==\"%s\" goto %s\n", el, loop));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.write("goto end\n\n");
            bw.write(String.format(":%s\n" +
                    "%s\n" +
                    "goto end\n", loop, command));
            bw.write(secondPart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

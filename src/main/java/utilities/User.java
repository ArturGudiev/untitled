package utilities;

import database.Postgres;

import java.io.IOException;
import java.util.Arrays;

public class User {

    public static void navigateToTask(){
        System.out.println("In navigateToTask");
        Postgres postgres = new Postgres();

        String task = postgres.getTask();
        System.out.println(task);
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "ch", task);
//        builder.redirectErrorStream(true);
        try {
            Process p = builder.start();
            p.destroy();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if("task".equals(args[0])){
            navigateToTask();
        }
        if("german".equals(args[0])){
            showGermanTasks();
        }
        if("settask".equals(args[0])){
            settingTheTask(args[1]);
        }
        if("adminpwd".equals(args[0])){
            settingThePassword();
        }
    }

    private static void settingThePassword() {
        Postgres postgres = new Postgres();
        postgres.setAdminPassword();
    }

    private static void settingTheTask(String task) {
        Postgres postgres = new Postgres();
        postgres.setTask(task);
    }

    private static void showGermanTasks() {
        Postgres postgres = new Postgres();
        postgres.showGerman();
    }
}

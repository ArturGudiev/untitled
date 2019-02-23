package utilities;

import database.Postgres;

import java.util.Arrays;
import java.util.logging.Logger;

import static utilities.Actions.executeCommand;



public class ActionsForUser {

    private static final Logger LOGGER = Logger.getLogger(ActionsForUser.class.getName());

    public static void navigateToTask(){
        String task = Postgres.getValue("work", "task");
        executeCommand("ch https://jira.cec.lab.emc.com:8443/browse/DPA-" + task);
    }

    public static void navigateToBoard(){
        String boardUrl = Postgres.getValue("work", "board");
        LOGGER.info("Get url from database: " + boardUrl);
        Inet.navigateToUrl(boardUrl);
    }

    public static String getTaskTemplate(){
        String str = "";
        String fileString = MyFile.getFileString("C:\\Programming\\Batch\\util\\tasktemplate.bat");
        return fileString;
    }

    public static String getCurrentTaskDir(){
        String task = Postgres.getValue("work", "task");
        String description = Postgres.getValue("work", "description");
        String[] dirs = Actions.getListOfDirectories("C:\\Artur\\Work\\tasks\\");
        String dirName = "DPA-" + task + "_" + description.replaceAll(" ", "_");
        for(int i = 0; i < dirs.length; i++){
            if(dirs[i].contains(dirName)){
                return "C:\\Artur\\Work\\tasks\\" + dirs[i];
            }
        }
        return dirName;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        if(args[0].equals("navigate-to-task")){
            LOGGER.info("Going to task");
            navigateToTask();
        }
        if(args[0].equals("navigate-to-board")){
            LOGGER.info("Going to board");
            navigateToBoard();
        }
    }
}

package utilities;

import java.util.Arrays;
import java.util.logging.Logger;

import static database.Postgres.*;
import static utilities.Actions.copyFile;
import static utilities.ActionsForUser.*;
import static utilities.ActionsForUser.getTaskTemplate;

public class Sequences {

    private static final Logger LOGGER = Logger.getLogger(Sequences.class.getName());



    private static int getLastNumberForTask() {
        String[] dirs = Actions.getListOfDirectories("C:\\Artur\\Work\\tasks\\");
        for(int i = 0; ; i++){
            final int index = i;
            boolean hasSuchDir = Arrays.stream(dirs).filter(dirName -> dirName.startsWith(index + "_")).toArray().length > 0;
            if(!hasSuchDir){
                return i;
            }
        }
    }


    public static void setNewTaskInDatabase (int number, String description){
        setValue("work", "task", Integer.toString(number));
        setValue("work", "description", description);
    }

    public static void setNewTask(int taskNumber, String description, Integer historyTaskNumber){
        // todo create new branch using Selenium
        // todo  checkout to branch
        // todo history task add

        saveCurrentTaskScript();
        setNewTaskInDatabase(taskNumber, description);

        String fullDescription = "DPA-" + taskNumber + " " + description;
        String fullOneDescription = fullDescription.replaceAll(" ", "_");
        String taskTemplate = getTaskTemplate();
        int lastNumberForTask = getLastNumberForTask();

        String dir = lastNumberForTask + "_" + fullOneDescription;

        taskTemplate = taskTemplate.replaceAll(":FD1:", fullOneDescription);
        taskTemplate = taskTemplate.replaceAll(":FD:", fullDescription);
        taskTemplate = taskTemplate.replaceAll(":DIR:", dir);
        taskTemplate = taskTemplate.replaceAll(":DESC:", description);
        taskTemplate = taskTemplate.replaceAll(":TASK:", "DPA-"+ taskNumber);
        if(historyTaskNumber != null){
            taskTemplate = taskTemplate.replaceAll(":HT:", "DPA-"+ historyTaskNumber);
        }

        Actions.deleteFile("C:\\Programming\\Batch\\ctask.bat");
        Actions.createFile("C:\\Programming\\Batch\\ctask.bat");
        Actions.appendToFile("C:\\Programming\\Batch\\ctask.bat", taskTemplate);
        Actions.createDirectory("C:\\Artur\\Work\\tasks\\" + dir);
        Actions.createFile("C:\\Artur\\Work\\tasks\\" + dir + "\\wiki.txt");
        Actions.createFile("C:\\Artur\\Work\\tasks\\" + dir + "\\text.txt");
    }

    private static void saveCurrentTaskScript() {
        try {
            String currentTaskDir = getCurrentTaskDir();
            copyFile("C:\\Programming\\Batch\\ctask.bat", currentTaskDir + "\\ctask.bat");

        }catch (Exception e){
            return;
        }
    }

    public static void setNewTask(int number, String description, int historyNumber, String historyDescription){}

    public static void main(String[] args) {
        if(args.length == 0) {
            LOGGER.warning("No arguments");
            return;
        }
        if(args.length > 0 && args[0].equals("set-task")){
            final int number = Integer.parseInt(args[1]);
            final String description = args[2];
            Integer historyTaskNumber = null;
            if(args.length > 3) {
                historyTaskNumber = Integer.parseInt(args[3]);
            }
            setNewTask(number, description, historyTaskNumber);
            LOGGER.info("Set task DPA-" + number + " " + description);
        }
        if(args.length > 0 && args[0].equals("set-db-task")){
            final int number = Integer.parseInt(args[1]);
            final String description = args[2];
            setNewTaskInDatabase(number, description);
            LOGGER.info("Set task in database DPA-" + number + " " + description);
        }
        //58584

//        setNewTask(57303, "Error 401 Unauthorized message appear when change password in 'View User Properties' dialog");
//        setNewTask(57355, "Implement Replication Alerts viewlet");
//        setNewTask(57445, "Implement Replication Exposure Age by Group viewlet");
//        setNewTask(57359, "Implement viewlet tip for Replication Alerts viewlet");
//        setNewTask(57565, "403 Error message appear if try save alerts filter by user " +
//                "which doesn't have permissions");
//        setNewTask(57461, "Can't login as a user without permissions");
//        setNewTask(57615, "Error appears while redirecting via link in Replication Alerts viewlet");
//        setNewTask(57628, "Transaction rolled back error appears");
//        saveCurrentTaskScript();
//        setNewTaskInDatabase(57303, "Error 401 Unauthorized message appear when change password " +
//        setNewTaskInDatabase(57758, "Related reports from Copy of Alert rule are not loaded and shown in report section");
//        copyFile("C:\\Artur\\Work\\tasks\\10_old_Unauthorised\\ctask.bat", "C:\\Programming\\Batch\\ctask.bat");

    }

}

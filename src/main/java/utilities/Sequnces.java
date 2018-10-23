package utilities;

import database.Postgres;

import java.util.Arrays;

public class Sequnces {

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

    public static void newTask(int number, String description){
        Postgres.setValue("work", "task", Integer.toString(number));
        Postgres.setValue("work", "description", description);
        int lastNumberForTask = getLastNumberForTask();
        System.out.println(lastNumberForTask);


    }

    public static void main(String[] args) {
        newTask(57303, "Error 401 Unauthorized message appear when change password in 'View User Properties' dialog");
//        newTask(111, " 111222");

    }

}

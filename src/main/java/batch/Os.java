package batch;

public class Os {
    public static void main(String[] args) {
        for (String s : args){
            String command = "tr os " + s;
            System.out.println(command);
        }
    }
}

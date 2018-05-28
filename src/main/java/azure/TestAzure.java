package azure;

public class TestAzure {
    public static void main(String[] args) throws Exception {
        while(true){
            Azure.main(new String[]{"normd"});
            Azure.main(new String[]{"lognormd"});
            Azure.main(new String[]{"plot"});
            Azure.main(new String[]{"vec"});
            Azure.main(new String[]{"table"});
//            Thread.sleep(1);
        }

    }
}

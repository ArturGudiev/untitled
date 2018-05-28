package marketyp;


import java.util.ArrayList;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class Env {
    public static final Env INSTANCE = new Env();
    //    private int count = 0;
    private volatile int count = 0;
    private volatile int round = 0;
    public volatile ArrayList<String> sellers = new ArrayList<String>();
    public volatile ArrayList<String> consumers = new ArrayList<String>();
    public volatile ArrayList<Integer> pathes = new ArrayList<Integer>();
    int proposal_senders = 0;
    int answers = 0;
    public enum EnvState {
        START,
        SEND_PROPOSALS,
        WAIT_ANSWERS,
        FINAL
    }

    public volatile EnvState state  = EnvState.START;


}


class MyFormatter extends SimpleFormatter {

    /* (non-Javadoc)
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getMessage()).append('\n');
        return sb.toString();
    }
}

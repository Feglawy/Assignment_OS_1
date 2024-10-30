package CLI;

import java.util.Arrays;

public record Command(String operator, String cmd, String[] args) {
    public Command(String cmd) {
        this(null, cmd, null);
    }

    public Command(String cmd, String[] args) {
        this(null, cmd, args);
    }

    public boolean hasOperator() {
        return operator != null;
    }

    @Override
    public String toString() {
        return "Command{" +
                "operator='" + operator + '\'' +
                ", cmd='" + cmd + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}

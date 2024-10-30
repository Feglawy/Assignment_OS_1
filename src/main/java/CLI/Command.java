package CLI;

import java.util.Arrays;
import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Command command = (Command) obj;
        return Objects.equals(operator, command.operator) &&
                Objects.equals(cmd, command.cmd) &&
                Arrays.equals(args, command.args);
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

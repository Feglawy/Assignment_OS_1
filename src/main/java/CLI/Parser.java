package CLI;

import CLI.Commands.CommandFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Parser {
    public static Stack<Command> parseCommands(String commandLine) {
        List<String> cmdArr = new ArrayList<String>();

        String[] parts = commandLine.trim().split(" ");

        StringBuilder cmd = new StringBuilder();
        for (String part : parts) {
            if (part.equals(">") || part.equals(">>") || part.equals("|")) {
                cmdArr.addLast(String.valueOf(cmd).trim());
                cmd = new StringBuilder(part);
            } else {
                cmd.append(" ").append(part);
            }
        }
        cmdArr.addLast(String.valueOf(cmd).trim());

        Stack<Command> cmdStk = new Stack<Command>();
        for (String command : cmdArr) {
            cmdStk.push(parseCommand(command));
        }
        return cmdStk;
    }

    public static Command parseCommand(String commandLine) {
        String[] parts = commandLine.trim().split(" ");
        String operator = null;
        String cmd = null;
        String[] args = null;
        if (parts[0].equals(">") || parts[0].equals(">>") || parts[0].equals("|")) {
            operator = parts[0];
            if (parts[0].equals(">") || parts[0].equals(">>")) {
                args = Arrays.copyOfRange(parts, 1, parts.length);
            }
            if (parts[0].equals("|")) {
                cmd = parts[1];
                args = Arrays.copyOfRange(parts, 2, parts.length);
            }
        }
        else {
            cmd = parts[0];
            args = Arrays.copyOfRange(parts, 1, parts.length);
        }
        return new Command(operator, cmd, args);
    }
}

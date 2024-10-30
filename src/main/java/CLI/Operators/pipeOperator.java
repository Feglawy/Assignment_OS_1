package CLI.Operators;

import CLI.Commands.ExecuteArgs;

import java.util.Arrays;

public class pipeOperator {
    public static void pipe(ExecuteArgs command, String[] args) {
        command.execute(args);
    }
}

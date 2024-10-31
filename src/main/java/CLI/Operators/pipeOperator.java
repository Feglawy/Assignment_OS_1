package CLI.Operators;

import CLI.Commands.IExecuteArgs;

public class pipeOperator {
    public static void pipe(IExecuteArgs command, String[] args) {
        command.execute(args);
    }
}

package CLI.Commands;

import CLI.CLIContext;

public class echoCommand extends Command implements IExecuteArgs, IHelp {
    public echoCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.err.println("Missing arguments : message");
            return;
        }
        String out = String.join(" ", args).trim();
        System.out.println(out);
    }

    @Override
    public void help() {
        System.out.println("Usage: echo <message> - prints a message.");
    }
}

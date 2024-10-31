package CLI.Commands;

import CLI.CLIContext;

public class exitCommand extends Command implements IExecute, IExecuteArgs, IHelp {
    protected exitCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute() {
        System.out.println("Exiting CLI");
        System.exit(0);
    }

    @Override
    public void execute(String[] args) {
        execute();
    }

    @Override
    public void help() {
        System.out.println("exit - Exits the program.");
    }
}

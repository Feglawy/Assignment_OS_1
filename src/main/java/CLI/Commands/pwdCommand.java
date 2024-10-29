package CLI.Commands;

import CLI.CLIContext;

public class pwdCommand extends Command implements Execute, Help{
    public pwdCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute() {
        System.out.println(context.getCurrentDirectory());
    }

    @Override
    public void help() {
        System.out.println("Usage: pwd - prints current directory.");
    }
}

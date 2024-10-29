package CLI.Commands;

import CLI.CLIContext;

import java.io.File;

public class rmCommand extends Command implements ExecuteArgs, Help {
    public rmCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.err.println("Missing arguments: <file>");
            return;
        }
        File file = new File(context.getCurrentDirectory(), args[0]);
        if (file.delete()) {
            System.out.println("File removed: "+ args[0]);
        } else {
            System.err.println("Couldn't remove " + args[0]);
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: rm <file>    - Removes a file");
    }
}

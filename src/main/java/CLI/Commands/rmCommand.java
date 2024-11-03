package CLI.Commands;

import CLI.CLIContext;

import java.io.File;

public class rmCommand extends Command implements IExecuteArgs, IHelp {
    public rmCommand() {super();}
    public rmCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.err.println("Missing arguments: <file>");
            return;
        }
        for (String arg : args) {
            File file = new File(context.getCurrentDirectory(), arg);
            if (file.delete()) {
                System.out.println("File removed: "+ arg);
            } else {
                System.err.println("Couldn't remove " + arg);
            }
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: rm <file> - Removes a file.");
    }
}

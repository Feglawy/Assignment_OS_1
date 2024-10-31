package CLI.Commands;

import CLI.CLIContext;

import java.io.File;
import java.io.IOException;

public class touchCommand extends Command implements IExecuteArgs, IHelp {
    public touchCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.err.println("Missing argument: <file>");
            return;
        }
        for (String arg : args) {
            File file = new File(context.getCurrentDirectory(), arg);
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + arg);
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.err.println("Failed to create file.");
            }
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: touch <file> - Creates a file.");
    }
}

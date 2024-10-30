package CLI.Commands;

import CLI.CLIContext;

import java.io.File;
import java.io.IOException;

public class touchCommand extends Command implements ExecuteArgs, Help {
    public touchCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.err.println("Missing argument: <file>");
            return;
        }
        File file = new File(context.getCurrentDirectory(), args[0]);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + args[0]);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Failed to create file.");
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: touch <file> - Creates a file.");
    }
}

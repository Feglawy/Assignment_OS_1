package CLI.Commands;

import CLI.CLIContext;

import java.io.File;

public class cdCommand extends Command implements ExecuteArgs, Help{
    public cdCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.err.println("missing argument <dir>");
            return;
        }

        String newPath = args[0];
        File newDir = new File(context.getCurrentDirectory(), newPath);

        if (newDir.isDirectory() && newDir.exists()) {
            context.setCurrentDirectory(newDir.getAbsolutePath());
        } else {
            System.out.println("cd: no such directory: " + newPath);
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: cd <dir>");
    }
}

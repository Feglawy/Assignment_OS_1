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
        String pathArg = args[0];
        pathArg = pathArg.replace("~", System.getProperty("user.home")); // replaces ~ with the user's home path

        File dirPath;

        if (new File(pathArg).isAbsolute()) {
            dirPath =  new File(pathArg);
        } else {
            dirPath = new File(context.getCurrentDirectory(), pathArg);
        }

        if (dirPath.isDirectory() && dirPath.exists()) {
            context.setCurrentDirectory(dirPath.getAbsolutePath());
        } else {
            System.out.println("cd - no such directory: " + pathArg);
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: cd <dir> - Changes directory.");
    }
}

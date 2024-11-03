package CLI.Commands;

import CLI.CLIContext;

import java.io.File;

public class mkdirCommand extends Command implements IExecuteArgs, IHelp {
    public mkdirCommand() {super();}
    public mkdirCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.err.println("Missing argument: directory name");
            return;
        }
        for (String arg : args) {
            File dir = new File(context.getCurrentDirectory(), arg);
            if (dir.mkdir()) {
                System.out.println("Directory created successfully");
            }else {
                System.err.println("Failed to create directory");
            }
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: mkdir <dir> - Creates a directory.");
    }
}

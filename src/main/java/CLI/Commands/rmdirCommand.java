package CLI.Commands;

import CLI.CLIContext;

import java.io.File;

public class rmdirCommand extends Command implements ExecuteArgs, Help{
    public rmdirCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args){
        if (args.length == 0) {
            System.err.println("Missing argument: dir");
            return;
        }

        for (String arg : args) {
            File dir = new File(context.getCurrentDirectory(), arg);
            if (dir.isDirectory() && dir.delete()) {
                System.out.println("Directory deleted successfully");
            }else {
                System.err.println("Failed to delete directory");
            }
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: rmdir <dir> - Removes a directory.");
    }
}

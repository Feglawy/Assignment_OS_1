package CLI.Commands;

import CLI.CLIContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class mvCommand extends Command implements IExecuteArgs, IHelp {
    public mvCommand(CLIContext context) {
        super(context);
    }


    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.err.println("missing arguments: <source> <destination>");
            return;
        }
        Path source = Paths.get(context.getCurrentDirectory(), args[0]);
        Path destination = Paths.get(context.getCurrentDirectory(), args[1]);

        Path sourceParent = source.toAbsolutePath().getParent();
        Path destinationParent = destination.toAbsolutePath().getParent();

        boolean renamed =  sourceParent.equals(destinationParent) ;

        try {
            Files.move(source, destination);
            if (renamed)
                System.out.println("Renamed " + args[0] + " to " + args[1]);
            else
                System.out.println("Moved " + args[0] + " to " + args[1]);
        } catch (Exception e) {
            System.err.println("Failed to move file.");
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: mv <source> <destination> - Move/rename a file or directory.");
    }
}

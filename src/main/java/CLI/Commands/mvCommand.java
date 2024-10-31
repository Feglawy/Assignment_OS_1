package CLI.Commands;

import CLI.CLIContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class mvCommand extends Command implements IExecuteArgs, IHelp {
    public mvCommand(CLIContext context) {
        super(context);
    }


    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.err.println("missing arguments: <source/s> <destination>");
            return;
        }

        ArrayList<Path> sources = new ArrayList<>();
        for (int i = 0; i < args.length - 1; i++) {
            sources.add(Paths.get(context.getCurrentDirectory(), args[i]).normalize());
        }
        Path destination = Paths.get(context.getCurrentDirectory(), args[args.length - 1]).normalize();

        if (isMoveNotValid(sources.toArray(new Path[0]), destination)) {
            System.err.println("Multiple file moves should have directory destination.");
            return;
        }

        boolean renamed = isRenaming(sources.toArray(new Path[0]), destination);

        try {
            for (Path source : sources) {
                Path temp = renamed ? destination : destination.resolve(source.getFileName());
                Files.move(source, temp);
                if (renamed)
                    System.out.println("Renamed " + source.getFileName() + " to " + destination.getFileName());
                else
                    System.out.println("Moved " + source.getFileName() + " to " + destination.getFileName());
            }
        } catch (Exception e) {
            System.err.println("Failed to move file.");
        }
    }

    @Override
    public void help() {
        System.out.println("Usage: mv <source> <destination> - Move/rename a file or directory.");
    }

    /// returns true when the destination is not a directory and one source
    private boolean isRenaming(Path[] source, Path destination) {
        return source.length == 1 && !destination.toFile().isDirectory();
    }

    /// if there are multiple sources the destination must be directory
    private boolean isMoveNotValid(Path[] sources, Path destination) {
        return sources.length > 1 && !destination.toFile().isDirectory();
    }
}

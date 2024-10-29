package CLI.Commands;

import CLI.CLIContext;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class lsCommand extends Command implements Execute, ExecuteArgs, Help{
    public lsCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute() {
        File dir = new File(context.getCurrentDirectory());
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file.getName() + (file.isDirectory()? "/": ""));
        }
    }

    @Override
    public void execute(String[] args) {
        File dir = new File(context.getCurrentDirectory());
        File[] files = dir.listFiles();

        boolean showAll = false;
        boolean reverse = false;

        for (String arg : args) {
            if (arg.equals("-a")) {
                showAll = true;
            } else if (arg.equals("-r")) {
                reverse = !reverse;
            } else {
                System.err.println(arg + " is not known argument for ls.");
                System.err.println("Available arguments -a and -r");
                return;
            }
        }

        if (files != null) {
            if (!showAll) {
                files = Arrays.stream(files)
                        .filter(file -> !file.getName().startsWith("."))
                        .toArray(File[]::new);
            }

            if (reverse) {
                Arrays.sort(files, Comparator.comparing(File::getName).reversed());
            }
            else {
                Arrays.sort(files, Comparator.comparing(File::getName));
            }

            for (File file : files) {
                System.out.println(file.getName() + (file.isDirectory()? "/": ""));
            }
        } else {
            System.err.println("Unable to list files.");
        }
    }

    @Override
    public void help() {
        System.out.println("Usage : ls  <arguments:optional>    - lists files in the current directory");
        System.out.println("arguments: ");
        System.out.println("arg: -r                             - reverses order of files");
        System.out.println("arg: -a                             - displays hidden files");

    }
}

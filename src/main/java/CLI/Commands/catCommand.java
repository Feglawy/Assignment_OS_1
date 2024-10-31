package CLI.Commands;

import CLI.CLIContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class catCommand extends Command implements IExecuteArgs, IHelp {
    public catCommand(CLIContext context) {
        super(context);
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.err.println("missing argument: <filename>");
            return;
        }
        try {
            File file = new File(context.getCurrentDirectory(), args[0]);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                System.out.println(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't read file : File not found.");
        }
    }

    @Override
    public void help() {
        System.out.println("cat <file> - Reads a file and outputs its content.");
    }
}

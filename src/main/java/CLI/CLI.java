package CLI;

import CLI.Commands.CommandFactory;
import CLI.Commands.Execute;
import CLI.Commands.ExecuteArgs;
import CLI.Commands.Help;

import java.util.Arrays;
import java.util.Scanner;

public class CLI {
    private final CLIContext context;
    private final CommandFactory commandFactory;

    public CLI() {
        context = new CLIContext();
        this.commandFactory = new CommandFactory(context);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print(context.getCurrentDirectory() + " > ");
            String input = scanner.nextLine();
            executeCommand(input);
        }
    }

    private void executeCommand(String commandLine) {
        String[] parts = commandLine.split(" ");
        String commandName = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        if (commandName.equalsIgnoreCase("help")) {
            displayHelp(args);
            return;
        }

        ExecuteArgs command = commandFactory.getExecuteCommand(commandName);
        if(command == null) {
            System.err.println(commandName + " is not defined.");
            return;
        }
        command.execute(args);
    }

    private void displayHelp(String[] args) {
        if (args.length == 0) {
            commandFactory.getAllHelpCommands().forEach((name, help) -> {
                help.help();
                System.out.println("_____");
            });
        } else {
            Help help = commandFactory.getHelpCommand(args[0]);
            if (help != null) {
                help.help();
            } else {
                System.out.println("No help available for: " + args[0]);
            }
        }
    }
}

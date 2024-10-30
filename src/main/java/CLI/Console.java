package CLI;

import CLI.Commands.CommandFactory;
import CLI.Commands.ExecuteArgs;
import CLI.Commands.Help;

import java.util.Scanner;
import java.util.Stack;


public class Console {
    private final CLIContext context;
    private final CommandFactory commandFactory;

    public Console() {
        context = new CLIContext();
        this.commandFactory = new CommandFactory(context);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.print(context.getCurrentDirectory() + " > ");
            String input = scanner.nextLine();
            Stack<Command> cmdStk = Parser.parseCommands(input);

            executeCommands(cmdStk);
        }
    }

    private void executeCommands(Stack<Command> stack) {
        while (!stack.isEmpty()) {
            Command cmd = stack.pop();
            if (cmd.cmd().equalsIgnoreCase("help")) {
                displayHelp(cmd.args());
                return;
            }

            ExecuteArgs command = commandFactory.getExecuteCommand(cmd.cmd());
            if(command == null) {
                System.err.println(cmd.cmd() + " is not defined.");
                return;
            }
            command.execute(cmd.args());
        }
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

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

            String[] commands = input.trim().split("&");

            for (String command : commands) {
                Stack<Command> cmdStk = Parser.parseCommands(command);
                CommandExecutor executor = new CommandExecutor(commandFactory);
                executor.executeCommands(cmdStk);
            }
        }
    }
}

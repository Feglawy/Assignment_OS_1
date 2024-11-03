package CLI;

import CLI.Commands.CommandFactory;

import java.util.Scanner;
import java.util.Stack;

/// The main starting point of The cli program
public class Console {
    private final CLIContext context;

    public Console() {
        context = CLIContext.getInstance();
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
                CommandExecutor executor = new CommandExecutor();
                executor.executeCommands(cmdStk);
            }
        }
    }
}

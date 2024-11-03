package CLI;

import CLI.Commands.CommandFactory;
import CLI.Commands.IExecuteArgs;
import CLI.Commands.IHelp;
import CLI.Operators.*;

import java.util.Stack;

public class CommandExecutor implements IExecutor {
    CLIContext context;
    CommandFactory commandFactory;
    public CommandExecutor() {
        this.context = CLIContext.getInstance();
        this.commandFactory = CommandFactory.getInstance();
    }

    @Override
    public void executeCommands(Stack<Command> stack) {
        while (!stack.isEmpty()) {
            Command cmd = stack.pop();
            if (cmd.hasOperator()) {
                OperatorHandler.handle(stack, cmd, this);
                return;
            }
            executeCommand(cmd);
        }
    }

    public void executeCommand(Command cmd) {
        if (cmd.cmd().equalsIgnoreCase("help")) {
            displayHelp(cmd.args());
            return;
        }

        IExecuteArgs command = commandFactory.getExecuteCommand(cmd.cmd());
        if(command == null) {
            System.err.println(cmd.cmd() + " is not defined.");
            return;
        }
        command.execute(cmd.args());
    }

    @SuppressWarnings("unused")
    public void displayHelp(String[] args) {
        if (args.length == 0) {
            commandFactory.getAllHelpCommands().forEach((name, help) -> {
                help.help();
                System.out.println("_____");
            });
        } else {
            IHelp help = commandFactory.getHelpCommand(args[0]);
            if (help != null) {
                help.help();
            } else {
                System.out.println("No help available for: " + args[0]);
            }
        }
    }
}

package CLI;

import CLI.Commands.CommandFactory;
import CLI.Commands.ExecuteArgs;
import CLI.Commands.Help;
import CLI.Operators.*;
        
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Stack;

public class CommandExecutor implements IExecutor {
    CommandFactory commandFactory;
    public CommandExecutor(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public void executeCommands(Stack<Command> stack) {
        while (!stack.isEmpty()) {
            Command cmd = stack.pop();
            if (cmd.hasOperator()) {
//                final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                final PrintStream originalOut = System.out;
//
//                System.setOut(new PrintStream(outputStream));
//
//                executeCommands(stack);
//                switch (cmd.operator()) {
//                    case ">>" -> redirectOperator.redirect(outputStream.toString(), cmd.args()[0], true);
//                    case ">" -> redirectOperator.redirect(outputStream.toString(), cmd.args()[0], false);
//                    case "|" -> {
//                        ExecuteArgs commandExecutable = commandFactory.getExecuteCommand(cmd.cmd());
//                        String[] commandArgs = outputStream.toString().trim().split(" ");
//                        outputStream.reset();
//                        pipeOperator.pipe(commandExecutable, commandArgs);
//                    }
//                }
//
//                System.setOut(originalOut);
//                System.out.println(outputStream.toString().trim());
//                outputStream.reset();
                OperatorHandler.handle(stack, cmd, commandFactory, this);
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

        ExecuteArgs command = commandFactory.getExecuteCommand(cmd.cmd());
        if(command == null) {
            System.err.println(cmd.cmd() + " is not defined.");
            return;
        }
        command.execute(cmd.args());
    }

    public void displayHelp(String[] args) {
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

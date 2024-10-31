package CLI.Operators;

import CLI.Command;
import CLI.Commands.CommandFactory;
import CLI.Commands.IExecuteArgs;
import CLI.IExecutor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Stack;

public class OperatorHandler {
    public static void handle(Stack<Command>stack, Command opcmd, CommandFactory commandFactory, IExecutor execute) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outputStream));

        execute.executeCommands(stack);
        switch (opcmd.operator()) {
            case ">>" -> {
                redirectOperator.redirect(outputStream.toString(), opcmd.args()[0], true);
                outputStream.reset();
            }
            case ">" -> {
                redirectOperator.redirect(outputStream.toString(), opcmd.args()[0], false);
                outputStream.reset();
            }
            case "|" -> {
                IExecuteArgs commandExecutable = commandFactory.getExecuteCommand(opcmd.cmd());
                String[] commandArgs = outputStream.toString().trim().split(" ");
                outputStream.reset();
                pipeOperator.pipe(commandExecutable, commandArgs);
            }
        }

        System.setOut(originalOut);

        // if there is an output print it
        String output = outputStream.toString().trim();
        if (!output.isEmpty()) {
            System.out.println(output);
        }

        outputStream.reset();
    }
}

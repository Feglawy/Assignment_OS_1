package CLI.Operators;

import CLI.CLIContext;
import CLI.Command;

import CLI.Commands.CommandFactory;
import CLI.Commands.IExecuteArgs;
import CLI.IExecutor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Stack;

public class OperatorHandler {
    public static void handle(Stack<Command>stack, Command opcmd, IExecutor execute) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outputStream));

        if (stack.size() > 0) {
            execute.executeCommands(stack);
        }
        switch (opcmd.operator()) {
            case ">>" -> {
                String fileName = opcmd.args()[0];
                String filePath = new File(CLIContext.getInstance().getCurrentDirectory(), fileName).getAbsolutePath();
                redirectOperator.redirect(outputStream.toString(), filePath, true);
                outputStream.reset();
            }
            case ">" -> {
                String fileName = opcmd.args()[0];
                String filePath = new File(CLIContext.getInstance().getCurrentDirectory(), fileName).getAbsolutePath();
                redirectOperator.redirect(outputStream.toString(), filePath, false);
                outputStream.reset();
            }
            case "|" -> {
                IExecuteArgs commandExecutable = CommandFactory.getInstance().getExecuteCommand(opcmd.cmd());
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

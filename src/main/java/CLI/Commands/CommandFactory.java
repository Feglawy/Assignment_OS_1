package CLI.Commands;

import CLI.CLIContext;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, IExecuteArgs> executeMap = new HashMap<>();
    private final Map<String, IHelp> helpMap = new HashMap<>();

    public CommandFactory(CLIContext context) {
        registerCommands(context);
    }

    private void registerCommands(CLIContext context) {
        addCommand("pwd", new pwdCommand(context));
        addCommand("cd", new cdCommand(context));
        addCommand("ls", new lsCommand(context));
        addCommand("echo", new echoCommand(context));
        addCommand("cat", new catCommand(context));
        addCommand("touch", new touchCommand(context));
        addCommand("mkdir", new mkdirCommand(context));
        addCommand("mv", new mvCommand(context));
        addCommand("rm", new rmCommand(context));
        addCommand("rmdir", new rmdirCommand(context));
        addCommand("exit", new exitCommand(context));
    }

    private void addCommand(String name, Command command) {
        if (command instanceof IExecuteArgs) {
            executeMap.put(name, (IExecuteArgs) command);
        }
        if (command instanceof IHelp) {
            helpMap.put(name, (IHelp) command);
        }
    }

    public IExecuteArgs getExecuteCommand(String name) {
        return executeMap.get(name);
    }

    public IHelp getHelpCommand(String name) {
        return helpMap.get(name);
    }

    public Map<String, IHelp> getAllHelpCommands() {
        return helpMap;
    }
}

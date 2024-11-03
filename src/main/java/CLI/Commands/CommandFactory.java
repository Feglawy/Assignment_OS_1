package CLI.Commands;

import CLI.CLIContext;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, IExecuteArgs> executeMap = new HashMap<>();
    private final Map<String, IHelp> helpMap = new HashMap<>();

    public CommandFactory() {
        registerCommands();
    }

    private void registerCommands() {
        addCommand("pwd", new pwdCommand());
        addCommand("cd", new cdCommand());
        addCommand("ls", new lsCommand());
        addCommand("echo", new echoCommand());
        addCommand("cat", new catCommand());
        addCommand("touch", new touchCommand());
        addCommand("mkdir", new mkdirCommand());
        addCommand("mv", new mvCommand());
        addCommand("rm", new rmCommand());
        addCommand("rmdir", new rmdirCommand());
        addCommand("exit", new exitCommand());
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

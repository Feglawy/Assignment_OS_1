package CLI.Commands;

import CLI.CLIContext;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, ExecuteArgs> executeMap = new HashMap<>();
    private final Map<String, Help> helpMap = new HashMap<>();

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
        if (command instanceof ExecuteArgs) {
            executeMap.put(name, (ExecuteArgs) command);
        }
        if (command instanceof Help) {
            helpMap.put(name, (Help) command);
        }
    }

    public ExecuteArgs getExecuteCommand(String name) {
        return executeMap.get(name);
    }

    public Help getHelpCommand(String name) {
        return helpMap.get(name);
    }

    public Map<String, Help> getAllHelpCommands() {
        return helpMap;
    }
}

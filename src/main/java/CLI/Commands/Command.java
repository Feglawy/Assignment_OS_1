package CLI.Commands;

import CLI.CLIContext;

public abstract class Command {
    protected CLIContext context;
    protected Command (CLIContext context) {
        this.context = context;
    }
}

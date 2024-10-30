package CLI;
import java.util.Stack;

@FunctionalInterface
public interface IExecutor {
    void executeCommands(Stack<Command> stack);
}
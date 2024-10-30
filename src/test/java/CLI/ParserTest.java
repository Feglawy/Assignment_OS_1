package CLI;

import CLI.Command;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    public void ParsingOneCommandMv() {
        String command = "mv a.txt b.txt";
        Command cmdParsed = Parser.parseCommand(command);

        Command expected = new Command(null, "mv", new String[]{"a.txt", "b.txt"});
        assertEquals(expected, cmdParsed);
    }

    @Test
    public void ParsingOneCommandLs() {
        String command = "ls -a -r";
        Command cmdParsed = Parser.parseCommand(command);

        Command expected = new Command(null, "ls", new String[]{"-a", "-r"});
        assertEquals(expected, cmdParsed);
    }

    @Test
    public void ParsingLineOfCommands() {
        String command = "cat a.txt | grep | sort >> b.txt";

        Stack<Command> parsedCmdStack = Parser.parseCommands(command);

        Stack<Command> expectedStack = new Stack<Command>();
        expectedStack.push(new Command(null, "cat", new String[]{"a.txt"}));
        expectedStack.push(new Command("|", "grep", new String[]{}));
        expectedStack.push(new Command("|", "sort", new String[]{}));
        expectedStack.push(new Command(">>", null, new String[]{"b.txt"}));

        assertEquals(expectedStack, parsedCmdStack);
    }

}
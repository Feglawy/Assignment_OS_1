package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class pwdCommandTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test
    public void pwdTest() {
        CLIContext context = new CLIContext("~");
        IExecute cmd = new pwdCommand(context);
        cmd.execute();

        String ExpectedOutput = System.getProperty("user.home");

        String output = outputStream.toString().trim();
        assertEquals(ExpectedOutput, output);
    }

    @Test
    public void pwdRootTest() {
        CLIContext context = new CLIContext("/");
        IExecute cmd = new pwdCommand(context);
        cmd.execute();

        String ExpectedOutput;

        if (System.getProperty("os.name").contains("Windows")) {
            ExpectedOutput = "C:\\";
        } else {
            ExpectedOutput = "\\";
        }
        String output = outputStream.toString().trim();
        assertEquals(ExpectedOutput, output);
    }
}
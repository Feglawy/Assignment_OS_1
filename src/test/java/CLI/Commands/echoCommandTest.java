package CLI.Commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class echoCommandTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private IExecuteArgs cmd;

    @BeforeEach
    public void setUp() {
        cmd = new echoCommand();

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void echoShouldNotThrow(){
        assertDoesNotThrow(()-> cmd.execute(new String[]{}));
    }

    @Test
    void echoTest() {
        String expectedOutput = "Hello, World!";
        cmd.execute(expectedOutput.trim().split(" "));
        String actualOutput = outputStream.toString().trim();

        assertEquals(expectedOutput, actualOutput);
    }

    @AfterEach
    public void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
        outputStream.reset();
    }
}
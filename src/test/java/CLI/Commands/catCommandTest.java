package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class catCommandTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private CLIContext context;
    private ExecuteArgs cmd;

    private final String fileName = "a.txt";
    private File file;

    @BeforeEach
    public void setUp() {
        context = new CLIContext("~");
        cmd = new catCommand(context);
        file = new File(context.getCurrentDirectory(), fileName); // make sure that the file exists

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
        outputStream.reset();

        //noinspection ResultOfMethodCallIgnored
        file.delete(); // deletes the file to reset it
    }

    private void writeToFile(String str) {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(str);
        } catch (IOException e) {
            System.err.println("Couldn't write to file");
        }
    }

    @Test
    void catShouldNotThrowTest() {
        assertDoesNotThrow(()-> {cmd.execute(new String[]{""});}); // missing argument
    }

    @Test
    void catTest() {
        String ExpectedOutput = "Hello, World!";
        writeToFile(ExpectedOutput);

        cmd.execute(new String[]{fileName});
        String output = outputStream.toString().trim();
        assertEquals(ExpectedOutput, output);
    }

}
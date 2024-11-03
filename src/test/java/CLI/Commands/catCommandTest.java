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

    private IExecuteArgs cmd;

    private final String fileName = "a.txt";
    private File file;

    String ExpectedOutput = "Hello, World!";

    private void writeToFile(String str) {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(str);
        } catch (IOException e) {
            System.err.println("Couldn't write to file");
        }
    }


    @BeforeEach
    public void setUp() {
        cmd = new catCommand();
        file = new File(CLIContext.getInstance().getCurrentDirectory(), fileName); // make sure that the file exists

        writeToFile(ExpectedOutput);

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void catShouldNotThrowTest() {

        assertDoesNotThrow(()-> {cmd.execute(new String[]{""});}); // missing argument

    }

    @Test
    void catTest() {
        cmd.execute(new String[]{fileName});
        String output = outputStream.toString().trim();
        assertEquals(ExpectedOutput, output);
    }

    @AfterEach
    public void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
        outputStream.reset();

        //noinspection ResultOfMethodCallIgnored
        file.delete(); // deletes the file to reset it
    }

}
package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class lsCommandTest {
    private final String TEST_DIR = "test_ls_dir";
    private final List<String> TEST_FILES_ARR = Arrays.asList(".a", "b", "c", "d");
    private final List<String> TEST_FILES_ARR_NO_HIDDEN = Arrays.asList("b", "c", "d");

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private CLIContext context;
    private IExecuteArgs ls;

    @BeforeEach
    public void setUp() {
        context = CLIContext.getInstance();
        ls = new lsCommand();

        File dir = new File(context.getCurrentDirectory(), TEST_DIR);

        assertTrue(dir.mkdir(), "Test Directory created successfully");

        context.setCurrentDirectory(dir.getAbsolutePath()); // changes current directory to the test_dir
        for (String file : TEST_FILES_ARR) {
            File temp = new File(context.getCurrentDirectory(), file);
            assertDoesNotThrow(temp::createNewFile);
        }

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void lsTest() {
        ls.execute(new String[] {});
        String ExpectedOutput = String.join("\r\n", TEST_FILES_ARR_NO_HIDDEN);
        String actualOutput = outputStream.toString().trim();

        assertEquals(ExpectedOutput, actualOutput);
    }

    @Test
    public void lsHiddenTest() {
        ls.execute(new String[] { "-a" });

        String ExpectedOutput = String.join("\r\n", TEST_FILES_ARR);
        String actualOutput = outputStream.toString().trim();

        assertEquals(ExpectedOutput, actualOutput);
    }

    @Test
    public void lsRevertTest() {
        ls.execute(new String[] { "-r" });
        String ExpectedOutput = String.join("\r\n", TEST_FILES_ARR_NO_HIDDEN.reversed());
        String actualOutput = outputStream.toString().trim();

        assertEquals(ExpectedOutput, actualOutput);
    }

    @Test
    public void lsHiddenRevertTest() {
        ls.execute(new String[] { "-r", "-a" });
        String ExpectedOutput = String.join("\r\n", TEST_FILES_ARR.reversed());
        String actualOutput = outputStream.toString().trim();

        assertEquals(ExpectedOutput, actualOutput);
    }

    @AfterEach
    public void cleanUp() {
        File dir = new File(context.getCurrentDirectory());

        File[] children = dir.listFiles();

        for (File child : children) {
            child.delete();
        }
        dir.delete();
        context.setCurrentDirectory("../");
        System.setOut(originalOut);
        outputStream.reset();
    }
}
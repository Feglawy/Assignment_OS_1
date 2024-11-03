package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class lsCommandTest {
    private final String TEST_DIR = "test_ls_dir";
    private final String[] testFilesArr = {"a", "b", "c"};

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
        for (String file : testFilesArr) {
            File temp = new File(context.getCurrentDirectory(), file);
            assertDoesNotThrow(temp::createNewFile);
        }

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void lsTest() {
        ls.execute(new String[]{});
        String ExpectedOutput = String.join("\r\n", testFilesArr);;
        String actualOutput = outputStream.toString().trim();

        assertEquals(ExpectedOutput, actualOutput);
    }

    @Test
    public void lsHiddenTest() {

    }

    @Test
    public void lsRevertTest() {

    }

    @Test
    public void lsHiddenRevertTest() {

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
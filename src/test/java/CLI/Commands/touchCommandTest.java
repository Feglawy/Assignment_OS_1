package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class touchCommandTest {
    private final String TEMP_TEST_FILE = "test_file.txt";
    private CLIContext context;
    private IExecuteArgs touch;

    @BeforeEach
    public void setUp() {
        context = CLIContext.getInstance();
        touch = new touchCommand();
    }

    @Test
    public void touchTest() {
        touch.execute(new String[]{TEMP_TEST_FILE});

        File file = new File(context.getCurrentDirectory(), TEMP_TEST_FILE);
        assertTrue(file.exists());
    }

    @AfterEach
    public void cleanUp() {
        File file = new File(context.getCurrentDirectory(), TEMP_TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}
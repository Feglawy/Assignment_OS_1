package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class rmCommandTest {
    private final String TEMP_TEST_FILE = "test_file.txt";
    private CLIContext context;
    private IExecuteArgs rm;

    @BeforeEach
    public void setUp() {
        context = CLIContext.getInstance();
        rm = new rmCommand();
    }

    @Test
    public void rmTest() throws IOException {
        File file = new File(context.getCurrentDirectory(), TEMP_TEST_FILE);

        assertTrue(file.createNewFile(), "File created successfully");

        assertTrue(file.exists(), "");

        rm.execute(new String[]{TEMP_TEST_FILE});

        assertFalse(file.exists(), "File deleted ");
    }

    @AfterEach
    public void cleanUp() {
        File file = new File(context.getCurrentDirectory(), TEMP_TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}

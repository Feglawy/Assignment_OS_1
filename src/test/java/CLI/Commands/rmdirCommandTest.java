package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class rmdirCommandTest {
    private final String TEMP_TEST_Dir = "test_Directory";
    private CLIContext context;
    private IExecuteArgs rmdir;

    @BeforeEach
    public void setUp() {
        context = new CLIContext();
        rmdir = new rmCommand(context);
    }

    @Test
    public void rmTest() throws IOException {
        File dir = new File(context.getCurrentDirectory(), TEMP_TEST_Dir);

        assertTrue(dir.createNewFile(), "Directory created successfully");

        assertTrue(dir.exists(), "");

        rmdir.execute(new String[]{TEMP_TEST_Dir});

        assertFalse(dir.exists(), "Directory deleted ");
    }

    @AfterEach
    public void cleanUp() {
        File dir = new File(context.getCurrentDirectory(), TEMP_TEST_Dir);
        if (dir.exists()) {
            dir.delete();
        }
    }
}
package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class mkdirCommandTest {
    private final String TEMP_TEST_DIR = "test_dir";
    private CLIContext context;
    private IExecuteArgs mk;

    @BeforeEach
    public void setUp() {
        context = CLIContext.getInstance();
        mk = new mkdirCommand();
    }

    @Test
    public void mkDirTest() {
        mk.execute(new String[]{TEMP_TEST_DIR});

        File file = new File(context.getCurrentDirectory(), TEMP_TEST_DIR);
        assertTrue(file.exists() && file.isDirectory());
    }

    @Test
    public void mkDirShouldNotThrow()  {

    }

    @AfterEach
    public void cleanUp() {
        File file = new File(context.getCurrentDirectory(), TEMP_TEST_DIR);
        if (file.exists()) {
            file.delete();
        }
    }
}
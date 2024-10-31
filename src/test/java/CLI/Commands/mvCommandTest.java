package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class mvCommandTest {

    private final String TEMP_TEST_DIR = "test_dir";
    private final String TEMP_TEST_FILE = "test.txt";
    private final String RENAMED_TEST_FILE = "a.txt";

    private CLIContext context;
    private IExecuteArgs mv;

    private File dir, file;

    @BeforeEach
    public void setUp() {
        context = new CLIContext();
        mv = new mvCommand(context);

        dir = new File(context.getCurrentDirectory(), TEMP_TEST_DIR);
        file = new File(context.getCurrentDirectory(), TEMP_TEST_FILE);
        dir.mkdir();
        assertDoesNotThrow(()-> {
            file.createNewFile();
        }, "Test file created successfully.");
    }

    @Test
    public void mvTest() {
        String[] args = {TEMP_TEST_FILE, TEMP_TEST_DIR};
        mv.execute(args);
        file = new File(dir, TEMP_TEST_FILE);
        assertTrue(Files.exists(file.toPath()));
    }

    @Test
    public void renameTest() {
        String[] args = {TEMP_TEST_FILE, RENAMED_TEST_FILE};
        mv.execute(args);

        file = new File(context.getCurrentDirectory(), RENAMED_TEST_FILE);
        assertTrue(Files.exists(file.toPath()));
    }

    @AfterEach
    public void cleanUp() {
        if (file.exists()) {
            file.delete();
        }
        if (dir.exists()) {
            dir.delete();
        }
    }
}
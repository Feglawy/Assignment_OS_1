package CLI.Commands;

import CLI.CLIContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class cdCommandTest {
    private CLIContext context;
    private IExecuteArgs cmd;

    @BeforeEach
    public void setUp() {
        context = new CLIContext();
        cmd = new cdCommand(context);
    }

    @Test
    public void cdShouldNotThrow() {
        assertAll(
                () -> assertDoesNotThrow(()-> cmd.execute(new String[]{})),
                () -> assertDoesNotThrow(()-> cmd.execute(new String[]{""}))
        );
    }

    @Test
    public void cdHomeTest(){
        cmd.execute(new String[]{"~"});
        assertEquals(System.getProperty("user.home"), context.getCurrentDirectory());
    }

    @Test
    public void cdBackTest() {
        final String currentDirectory = context.getCurrentDirectory();

        String parentDir = new File(currentDirectory, "../")
                .getAbsoluteFile().toPath().normalize().toString();

        cmd.execute(new String[]{"../"});

        assertEquals(parentDir, context.getCurrentDirectory());
    }

    @Test
    public void cdAbsolutePathTest() {
        String[] arg =
                (System.getProperty("os.name").contains("Windows")) ?
                        new String[]{"C:\\"}: new String[]{"\\"};
        cmd.execute(arg);

        assertEquals(arg[0], context.getCurrentDirectory());
    }
}
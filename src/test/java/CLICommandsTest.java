import CLI.CLICommands;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CLICommandsTest {
    @Test
    void init() {
        CLICommands cli = new CLICommands("\\");
        assertEquals("\\", cli.pwd());
    }

    @Test
    void initDir() {
        CLICommands cli = new CLICommands();
        assertEquals(System.getProperty("user.dir"), cli.pwd());
    }
}

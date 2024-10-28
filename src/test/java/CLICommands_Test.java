import CLI.CLICommands;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CLICommands_Test {
    @Test
    void testCd() throws Exception {
        CLICommands cli = new CLICommands("\\");
        assertEquals("\\", cli.pwd());
    }
}

import CLI.CLI;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CLI_Test {
    @Test
    void testCd() throws Exception {
        CLI cli = new CLI("\\");
        assertEquals("\\", cli.pwd());
    }
}
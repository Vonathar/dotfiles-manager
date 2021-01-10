package uk.caputo.dotfilesmanager.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.test.context.TestPropertySource;
import uk.caputo.dotfilesmanager.Application;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
class HelpCommandTest {

  @Autowired Shell shell;

  @Test
  void help_ShouldPrintAllCommandsWithDescription() {
    List<Command> commands = Command.getImplementers();
    String actualResult = shell.evaluate(() -> "help").toString();
    for (Command c : commands) {
      assertTrue(actualResult.contains(c.getSimpleInfo()));
    }
  }

  @Test
  void help_ShouldRespectMaxLineLength() throws IOException {
    int MAX_LINE_LENGTH = Application.LINE_LENGTH;
    String output = shell.evaluate(() -> "help").toString();
    BufferedReader br = new BufferedReader(new StringReader(output));
    String line;
    while ((line = br.readLine()) != null) {
      assertTrue(line.length() <= MAX_LINE_LENGTH);
    }
  }
}

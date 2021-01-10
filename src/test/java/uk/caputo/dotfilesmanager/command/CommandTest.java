package uk.caputo.dotfilesmanager.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import uk.caputo.dotfilesmanager.StringFormatter;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
class CommandTest {

  @Autowired StringFormatter stringFormatter;

  @Test
  void printInfo_ShouldPrintFormattedCommandWithDescription() {
    Command command = new TestCommand();
    ReflectionTestUtils.setField(command, "stringFormatter", stringFormatter);
    String expectedResult =
        "test           Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vu\n"
            + "               lputate luctus purus, nec cursus enim euismod in. Vivamus in bibe\n"
            + "               ndum ex, nec consequat libero. Proin molestie turpis nec aliquam.\n";
    String actualResult = command.getInfo();
    assertEquals(expectedResult, actualResult);
  }

  static class TestCommand extends Command {
    @Override
    public String getDescription() {
      return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec v"
          + "ulputate luctus purus, nec cursus enim euismod in. Vivamus in bib"
          + "endum ex, nec consequat libero. Proin molestie turpis nec aliquam.";
    }
  }
}

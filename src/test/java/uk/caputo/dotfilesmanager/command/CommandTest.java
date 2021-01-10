package uk.caputo.dotfilesmanager.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
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
  void getSimpleInfo_ShouldPrintFormattedCommandWithDescription() {
    Command command = new TestCommand();
    ReflectionTestUtils.setField(command, "stringFormatter", stringFormatter);
    String expectedResult =
        "test           Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vu\n"
            + "               lputate luctus purus, nec cursus enim euismod in. Vivamus in bibe\n"
            + "               ndum ex, nec consequat libero. Proin molestie turpis nec aliquam.\n";
    String actualResult = command.getSimpleInfo();
    assertEquals(expectedResult, actualResult);
  }

  @Test
  void getDetailedInfo_ShouldPrintFormattedCommandWithUsageExamplesAndOptions() {
    Command command = new TestCommand();
    ReflectionTestUtils.setField(command, "stringFormatter", stringFormatter);
    String actualResult = command.getDetailedInfo();
    String expectedResult =
        "Test.\n"
            + "\n"
            + "Usage:\n"
            + "  test -f <x>\n"
            + "  test -b <y>\n\n"
            + "Options:\n"
            + "  -f --foo     description of foo.\n"
            + "  -b --bar     description of bar.\n\n";
    assertEquals(expectedResult, actualResult);
  }

  static class TestCommand extends Command {
    @Override
    public String getDescription() {
      return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec v"
          + "ulputate luctus purus, nec cursus enim euismod in. Vivamus in bib"
          + "endum ex, nec consequat libero. Proin molestie turpis nec aliquam.";
    }

    @Override
    public LinkedHashMap<String, String> getOptions() {
      LinkedHashMap<String, String> map = new LinkedHashMap<>();
      map.put("-f --foo", "description of foo.");
      map.put("-b --bar", "description of bar.");
      return map;
    }

    @Override
    public String[] getUsageExamples() {
      return new String[] {"test -f <x>", "test -b <y>"};
    }
  }
}

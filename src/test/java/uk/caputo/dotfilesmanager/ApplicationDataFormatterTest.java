package uk.caputo.dotfilesmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
class ApplicationDataFormatterTest {

  @Autowired ApplicationDataFormatter applicationDataFormatter;

  @Test
  void getTitle_ShouldReturnTitleWithPaddedVersionNumberAndTwoClosingNewLines() {
    String VERSION = "v" + Application.VERSION;
    String actualTitle = applicationDataFormatter.getTitle();
    String expectedTitle =
        "********************************************************************************\n"
            + "**********                      Dotfiles Manager                      **********\n"
            + "**********                           "
            + VERSION
            + "                           **********\n"
            + "********************************************************************************\n\n";
    assertEquals(expectedTitle, actualTitle);
  }
}

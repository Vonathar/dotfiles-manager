package uk.caputo.dotfilesmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class serves formatted strings using the application data, ready to be printed to the shell.
 *
 * @author Gianmarco Caputo
 */
@Component
public class ApplicationDataFormatter {

  @Autowired private StringFormatter stringFormatter;

  /**
   * Builds a title string with the application name and version.
   *
   * @return the formatted title string.
   */
  public String getTitle() {
    String APPLICATION_NAME = Application.NAME;
    String VERSION = "v" + Application.VERSION;
    return stringFormatter.addAsteriskBorder(APPLICATION_NAME, VERSION);
  }
}

package uk.caputo.dotfilesmanager;

import org.springframework.stereotype.Component;

/**
 * This class holds multiple methods that can be used to format strings.
 *
 * @author Gianmarco Caputo
 */
@Component
public class StringFormatter {

  /**
   * Adds padding to the left side of a string. The padding is only added if the original length of
   * the string is smaller than the given one, and only enough to match the given length.
   *
   * @param content the string that has to be padded.
   * @param length the length of the padded string.
   * @return the padded string.
   */
  public String addLeftPadding(String content, int length) {
    String formatting = "%" + length + "s";
    return String.format(formatting, content);
  }

  /**
   * Adds padding to the right side of a string. The padding is only added if the original length of
   * the string is smaller than the given one, and only enough to match the given length.
   *
   * @param content the string that has to be padded.
   * @param length the length of the padded string.
   * @return the padded string.
   */
  public String addRightPadding(String content, int length) {
    String formatting = "%-" + length + "s";
    return String.format(formatting, content);
  }
}

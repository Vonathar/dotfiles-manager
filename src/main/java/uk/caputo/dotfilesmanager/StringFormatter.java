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
    if (length < content.length()) {
      throw new IllegalArgumentException("Content length is greater than requested length.");
    }
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
    if (length < content.length()) {
      throw new IllegalArgumentException("Content length is greater than requested length.");
    }
    String formatting = "%-" + length + "s";
    return String.format(formatting, content);
  }

  /**
   * Adds padding to both sides of a string. The padding is only added if the original length of the
   * string is smaller than the given one, and only enough to match the given length. If the given
   * length is an odd integer, the space in excess will be added to the right side of the string.
   *
   * @param content the string that has to be padded.
   * @param length the length of the padded string.
   * @return the padded string.
   */
  public String addBilateralPadding(String content, int length) {
    length -= content.length();
    if (length <= 0) {
      throw new IllegalArgumentException("Content length is greater than requested length.");
    }
    int leftPadding = length / 2;
    int rightPadding = (length / 2) + length % 2;
    return " ".repeat(leftPadding) + content + " ".repeat(rightPadding);
  }

  /**
   * Adds a surrounding border using asterisk characters. The text content is automatically centered
   * in the border, and two newline characters are added at the end. The size of the border is
   * dynamically calculated based on the line length constant from the main class.
   *
   * @param content the strings to add in the border
   * @return the content with an asterisk border.
   */
  public String addAsteriskBorder(String... content) {
    int LINE_LENGTH = DotfilesManagerApplication.LINE_LENGTH;
    StringBuilder sb = new StringBuilder();
    sb.append("*".repeat(LINE_LENGTH)).append("\n");
    for (String s : content) {
      sb.append("*".repeat(10))
          .append(addBilateralPadding(s, LINE_LENGTH - 20))
          .append("*".repeat(10))
          .append("\n");
    }
    sb.append("*".repeat(LINE_LENGTH)).append("\n\n");
    return sb.toString();
  }
}

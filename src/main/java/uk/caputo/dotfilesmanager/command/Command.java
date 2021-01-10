package uk.caputo.dotfilesmanager.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import uk.caputo.dotfilesmanager.StringFormatter;

/**
 * This class represents a generic shell command, and must be implemented by each concrete
 * implementation of a command. Whenever a new command extends this class, a reference to it is
 * automatically added in the static list 'implementers'; this is done in the constructor, to allow
 * easier access to all existing commands.
 *
 * @author Gianmarco Caputo
 */
public abstract class Command {

  private static final List<Command> implementers = new ArrayList<>();
  private final String command;
  @Autowired private StringFormatter stringFormatter;

  public Command() {
    this.command = this.getClass().getSimpleName().replace("Command", "").toLowerCase();
    implementers.add(this);
  }

  /**
   * Returns the list of all classes that extend the Command class.
   *
   * @return the list of Command implementers.
   */
  public static List<Command> getImplementers() {
    return implementers;
  }

  public abstract String getDescription();

  public abstract LinkedHashMap<String, String> getOptions();

  public abstract String[] getUsageExamples();

  /**
   * Returns the formatted command information, ready to be printed to the shell.
   *
   * @return the formatted command information.
   */
  public String getSimpleInfo() {
    return stringFormatter.separateSides(command, getDescription(), 15) + "\n";
  }

  /**
   * Returns the formatted command information with additional details. If available, all available
   * options for the command are listed, as well as the usage examples.
   *
   * @return the formatted command, its options, and usage examples.
   */
  public String getDetailedInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append(stringFormatter.capitalizeFirstLetter(command))
        .append(".\n\n")
        .append(stringFormatter.listWithIndent(2, "Usage:", getUsageExamples()));
    List<String> formattedOptions = new ArrayList<>();
    for (Map.Entry<String, String> entry : getOptions().entrySet()) {
      formattedOptions.add(stringFormatter.separateSides(entry.getKey(), entry.getValue(), 13));
    }
    sb.append(
        stringFormatter.listWithIndent(2, "Options:", formattedOptions.toArray(new String[0])));
    return sb.toString();
  }
}

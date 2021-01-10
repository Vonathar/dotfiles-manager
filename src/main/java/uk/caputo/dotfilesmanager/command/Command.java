package uk.caputo.dotfilesmanager.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

  public abstract String getDescription();

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
}

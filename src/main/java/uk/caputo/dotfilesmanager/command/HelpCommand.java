package uk.caputo.dotfilesmanager.command;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Help;
import uk.caputo.dotfilesmanager.ApplicationDataFormatter;

/**
 * This class represents the 'help' command in the shell.
 *
 * @author Gianmarco Caputo
 */
@ShellComponent
public class HelpCommand implements Help.Command {

  @Autowired private ApplicationDataFormatter applicationDataFormatter;

  /**
   * Prints a list of all available commands, as well as their descriptions.
   *
   * @return the list of commands with their descriptions.
   */
  @ShellMethod("Lists all available commands.")
  public String help() {
    List<Command> commands = Command.getImplementers();
    StringBuilder sb = new StringBuilder(applicationDataFormatter.getTitle());
    sb.append("The following commands are available:\n\n");
    for (Command c : commands) {
      sb.append(c.getInfo());
    }
    return sb.toString();
  }
}

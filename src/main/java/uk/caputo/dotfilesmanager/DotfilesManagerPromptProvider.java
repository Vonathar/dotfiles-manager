package uk.caputo.dotfilesmanager;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * This class provides a custom prompt for the interactive shell.
 *
 * @author Gianmarco Caputo
 */
@Component
public class DotfilesManagerPromptProvider implements PromptProvider {

  @Override
  public AttributedString getPrompt() {
    return new AttributedString("DFM:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
  }
}

package uk.caputo.dotfilesmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static final String VERSION = "1.0.0";
  public static final String NAME = "Dotfiles Manager";
  public static final int LINE_LENGTH = 80;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

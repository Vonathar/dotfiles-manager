package uk.caputo.dotfilesmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
class StringFormatterTest {

  @Autowired StringFormatter stringFormatter;

  @Test
  void addLeftPadding_ShouldAddLeftPadding() {
    assertEquals(stringFormatter.addLeftPadding("X", 1), "X");
    assertEquals(stringFormatter.addLeftPadding("X", 3), "  X");
    assertEquals(stringFormatter.addLeftPadding("X", 5), "    X");
  }

  @Test
  void addRightPadding_ShouldAddRightPadding() {
    assertEquals(stringFormatter.addRightPadding("X", 1), "X");
    assertEquals(stringFormatter.addRightPadding("X", 3), "X  ");
    assertEquals(stringFormatter.addRightPadding("X", 5), "X    ");
  }

  @Test
  void addBilateralPadding_OddLength_ShouldAddPaddingOnBothSides() {
    assertEquals(stringFormatter.addBilateralPadding("X", 3), " X ");
    assertEquals(stringFormatter.addBilateralPadding("X", 5), "  X  ");
    assertEquals(stringFormatter.addBilateralPadding("X", 15), "       X       ");
  }

  @Test
  void addBilateralPadding_EvenLength_ShouldAddPaddingOnBothSidesWithExcessOnRightSide() {
    assertEquals(stringFormatter.addBilateralPadding("X", 4), " X  ");
    assertEquals(stringFormatter.addBilateralPadding("X", 6), "  X   ");
    assertEquals(stringFormatter.addBilateralPadding("X", 10), "    X     ");
  }
}

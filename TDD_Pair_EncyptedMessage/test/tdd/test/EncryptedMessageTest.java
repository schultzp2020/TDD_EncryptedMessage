/**
 * 
 */
package tdd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tdd.EncryptedMessage;

/**
 * @author Paul, Lucas
 *
 */
class EncryptedMessageTest {

  /**
   * @throws java.lang.Exception
   */
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  /**
   * Test to see if the two constructors are not null
   */
  void constructorNotNull() {
    EncryptedMessage encryptedMessage1 = new EncryptedMessage("ab dd", "cz");
    
    EncryptedMessage encryptedMessage2 = new EncryptedMessage("BCE");

    assertNotNull(encryptedMessage1);

    assertNotNull(encryptedMessage2);
  }
  
  @Test
  /**
   * Test to see if the two constructors are the equal
   */
  void constructorCompare() {
    EncryptedMessage encryptedMessage1 = new EncryptedMessage("ab dd", "cz");
    
    EncryptedMessage encryptedMessage2 = new EncryptedMessage("BCE");

    assertEquals(encryptedMessage1, encryptedMessage2);
  }
  
  @Test
  /**
   * Test to see if the encrypted message is upper case
   */
  void encryptedMessageUpperCase() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("dadsad", "dsad");

    boolean isAllUpperCase = true;
    for (char character : encryptedMessage.getMessage().toCharArray()) {
      if (!Character.isUpperCase(character)) {
        isAllUpperCase = false;
      }
    }
    
    assertTrue(isAllUpperCase);
  }
  
  @Test
  /**
   * Test to see if the decrypted message is upper case
   */
  void decryptedMessageUpperCase() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("dadsad", "dsad");

    boolean isAllUpperCase = true;
    for (char character : encryptedMessage.decryptMessage("dsad").toCharArray()) {
      if (!Character.isUpperCase(character)) {
        isAllUpperCase = false;
      }
    }
    
    assertTrue(isAllUpperCase);
  }
  
  @Test
  /**
   * Test to see if the decrypted message converted the space to ASCII code 64
   */
  void encryptedMessageSpace() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("dad sad", "a");


    int index = encryptedMessage.getMessage().indexOf("a");

    
    assertEquals(index, 3);
  }
  
  @Test
  /**
   * Test to see if the decrypted message does not contain ASCII code 64
   */
  void decryptedMessageSpace() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("dadsad", "a");


    int index = encryptedMessage.getMessage().indexOf("a");

    
    assertEquals(index, -1);
  }

  @Test
  /**
   * Example 1 from the Pair Programming Assignment
   */
  void example1Test() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("abd", "a");

    assertEquals(encryptedMessage.getMessage(), "BCE");

    assertEquals(encryptedMessage.decryptMessage("a"), "ABD");
  }

  @Test
  /**
   * Example 2 from the Pair Programming Assignment
   */
  void example2Test() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab dd", "cz");

    assertEquals(encryptedMessage.getMessage(), "DACCG");

    assertEquals(encryptedMessage.decryptMessage("CZ"), "AB DD");
  }
  
  @Test
  /**
   * Example 1 from the Pair Programming Assignment but meant to fail
   */
  void example1TestFail() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("abd", "a");

    assertEquals(encryptedMessage.getMessage(), "BCE");

    assertEquals(encryptedMessage.decryptMessage("a"), "ABD");
  }

  @Test
  /**
   * Example 2 from the Pair Programming Assignment but meant to fail
   */
  void example2TestFail() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab dd", "cz");

    assertNotEquals(encryptedMessage.getMessage(), "DACCG");

    assertNotEquals(encryptedMessage.decryptMessage("PS"), "AB DD");
  }

}

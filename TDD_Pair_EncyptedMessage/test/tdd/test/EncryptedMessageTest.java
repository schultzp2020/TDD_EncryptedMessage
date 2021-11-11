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
 * @author Paul, Lucas, Amine
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
   * Test to see if space in message gets converted correctly for encrypted message 
   */
  void encryptedMessageSpace() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("dad sad", "a");


    int index = encryptedMessage.getMessage().indexOf("a");

    
    assertEquals(index, 3);
  }
  
  @Test
  /**
   * Test to see if no space in message gets converted correctly for encrypted message 
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
   * Test for a space in key
   */
  void spaceKeyTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab dd", "c z");

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for special characters in key
   */
  void specialCharactersKeyTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab dd", "c#");

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for empty string parameter for key
   */
  void specialCharactersKeyTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab dd", "");

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for null parameter for key
   */
  void nullKeyTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab dd", null);

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for null parameter for message
   */
  void nullMessageTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage(null, "a");

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for special characters in message
   */
  void specialCharactersMessageTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab#dd", "a");

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for empty string parameter for message
   */
  void specialCharactersMessageTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("", "a");

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for key longer than message
   */
  void keyLongerThanMessageTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("a", "abcd");

    assertEquals('B',encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for upper case message
   */
  void upperCaseMessageTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("A", "a");

    assertEquals('B',encryptedMessage.getMessage());
    assertEquals('A',encryptedMessage.decryptMessage('A'))
  }
  
  @Test
  /**
   * Test for special characters in message 
   */
  void specialCharactersMessageTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("ab!", "a");

    assertNull(encryptedMessage.getMessage());
  }
  
  @Test
  /**
   * Test for mixed letters in message
   */
  void mixedCaseTest() {
    EncryptedMessage encryptedMessage = new EncryptedMessage("aBcD", "a");

    assertEquals('BCDE',encryptedMessage.getMessage());
    assertEquals('ABCD',encryptedMessage.decryptMessage('A'))
  }
  
  
}

/**
 * 
 */
package tdd;

import java.util.regex.Pattern;

/**
 * @author Paul, Amine
 *
 */
public class EncryptedMessage {

  private String mEncryptedMessage;

  /**
   * 
   */
  public EncryptedMessage(String message, String key) {
    encryptMessage(message, key);

  }

  public EncryptedMessage(String encryptedMessage) {
    
    if (Pattern.matches("[A-Z@]+", encryptedMessage)) {
      mEncryptedMessage = encryptedMessage;
      return;
    }
    
    mEncryptedMessage = null;
  }

  public String getMessage() {
    return mEncryptedMessage;
  };

  public String decryptMessage(String key) {
    if (!validate(key, false)) {
      return null;
    }

    char[] resultArray = new char[mEncryptedMessage.length()];

    for (int i = 0; i < mEncryptedMessage.length(); i++) {
      // Get the upper case letter at the index
      char upperCaseKeyChar = Character.toUpperCase(key.charAt(i % key.length()));

      int difference = (int) mEncryptedMessage.charAt(i) - (int) upperCaseKeyChar;

      // Get the absolute value of the difference
      if (difference < 0) {
        difference += 27;
      }

      char result = (char) (difference + 64);

      // If character is a space, turn into @ symbol.
      if ((int) result == 64) {
        result = (char) 32;
      }

      resultArray[i] = result;
    }

    return new String(resultArray);
  }

  private Boolean validate(String data, Boolean allowSpaces) {
    if (data == null) {
      return false;
    }

    if (allowSpaces) {
      return Pattern.matches("[a-zA-Z\s]+", data);
    }

    return Pattern.matches("[a-zA-Z]+", data);
  }

  private void encryptMessage(String message, String key) {
    // validate the message and key
    if (!(validate(key, false) && validate(message, true))) {
      mEncryptedMessage = null;
      return;
    }

    char[] resultArray = new char[message.length()];

    for (int i = 0; i < message.length(); i++) {
      // Get the upper case letter at the index
      char upperCaseMessageChar = Character.toUpperCase(message.charAt(i));
      char upperCaseKeyChar = Character.toUpperCase(key.charAt(i % key.length()));

      // If character is a space, turn into @ symbol.
      if ((int) upperCaseMessageChar == 32) {
        upperCaseMessageChar = (char) 64;
      }

      int difference = (int) upperCaseKeyChar - 64;

      int result = (int) upperCaseMessageChar + difference;

      if (result > 90) {
        result -= 27;
      }

      resultArray[i] = (char) result;
    }

    mEncryptedMessage = new String(resultArray);

  }

}

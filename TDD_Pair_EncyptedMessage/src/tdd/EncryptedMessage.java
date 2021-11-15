/**
 * 
 */
package tdd;

import java.util.regex.Pattern;

/**
 * EncryptedMessage is a class for implementing the Vigenere Cipher
 * 
 * @author Paul, Amine
 */
public class EncryptedMessage {

  /*
   * The encrypted message for the Vigenere Cipher
   */
  private String mEncryptedMessage;

  /**
   * A constructor given a message and a key
   * 
   * @param message - The message you want to encrypt
   * @param key     - The secret key you want to encrypt the message with
   */
  public EncryptedMessage(String message, String key) {
    encryptMessage(message, key);
  }

  /**
   * A constructor given an already encrypted message
   * 
   * @param encryptedMessage - The already encrypted message
   */
  public EncryptedMessage(String encryptedMessage) {
    if (encryptedMessage == null) {
      mEncryptedMessage = null;
    }

    if (Pattern.matches("[A-Z@]+", encryptedMessage)) {
      mEncryptedMessage = encryptedMessage;
      return;
    }

    mEncryptedMessage = null;
  }

  /**
   * A getter for the stored encrypted message
   * 
   * @return The encrypted message
   */
  public String getMessage() {
    return mEncryptedMessage;
  };

  /**
   * Decrypts the encrypted message given a key
   * 
   * @param key - The secret key the encrypted message was encrypted with
   * @return The decrypted message
   */
  public String decryptMessage(String key) {
    if (!validate(key, false)) {
      return null;
    }

    char[] resultArray = new char[mEncryptedMessage.length()];

    for (int i = 0; i < mEncryptedMessage.length(); i++) {
      // Get the upper case letter at the index
      char upperCaseKeyChar = Character.toUpperCase(key.charAt(i % key.length()));

      int difference = (int) mEncryptedMessage.charAt(i) - (int) upperCaseKeyChar;

      // If the result is before A go forward 27 letters
      if (difference < 0) {
        difference += 27;
      }

      char result = (char) (difference + 64);

      // If character is an @, turn into a space
      if ((int) result == 64) {
        result = (char) 32;
      }

      resultArray[i] = result;
    }

    return new String(resultArray);
  }

  /**
   * A helper function to validate string input
   * 
   * @param data        - The string to validate
   * @param allowSpaces - A boolean on whether to allow spaces on the string
   * @return True if the string is valid
   */
  private Boolean validate(String data, Boolean allowSpaces) {
    if (data == null) {
      return false;
    }

    if (allowSpaces) {
      return Pattern.matches("[a-zA-Z\s]+", data);
    }

    return Pattern.matches("[a-zA-Z]+", data);
  }

  /**
   * A helper function to encrypt a message
   * 
   * @param message - The message you want to encrypt
   * @param key     - The secret key you want to encrypt the message with
   */
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

      // If character is a space, turn into @ symbol
      if ((int) upperCaseMessageChar == 32) {
        upperCaseMessageChar = (char) 64;
      }

      int difference = (int) upperCaseKeyChar - 64;

      // The resulting char as an int
      int result = (int) upperCaseMessageChar + difference;

      // If the result is past Z go back 27 letters
      if (result > 90) {
        result -= 27;
      }

      resultArray[i] = (char) result;
    }

    mEncryptedMessage = new String(resultArray);

  }

}

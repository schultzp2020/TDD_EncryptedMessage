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
    // TODO Auto-generated constructor stub
  }

  public String getMessage() {
    return mEncryptedMessage;
  };

  public String decryptMessage(String key) {
    return "";
  }

  private Boolean validate(String data, Boolean allowSpaces) {
    return false;
  }

  private void encryptMessage(String message, String key) {
    // key can only have letters
    if (!Pattern.matches("[a-zA-Z]+", key)) {
      mEncryptedMessage = null;
      return;
    }
    // message can have spaces and letters
    if (!Pattern.matches("[a-zA-Z\s]+", message)) {
      mEncryptedMessage = null;
      return;
    }

    char[] messageChArray = message.toCharArray();
    char[] keyChArray = key.toCharArray();
    char[] resultArray = new char[messageChArray.length];

    for (int i = 0; i < messageChArray.length; i++) {
      // upper case letter for message at index
      char messageCh = Character.toUpperCase(messageChArray[i]);

      // if character is a space, turn into @ symbol.
      if ((int) messageCh == 32) {
        messageCh = (char) 64;
      }

      // upper case letter for key at index
      char keyCh = Character.toUpperCase(keyChArray[i % keyChArray.length]);

      int difference = (int) keyCh - 64;
      char result = (char) ((int) messageCh + difference);
      resultArray[i] = result;
    }

    mEncryptedMessage = new String(resultArray);

  }

}

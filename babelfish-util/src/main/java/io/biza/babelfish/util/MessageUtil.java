package io.biza.babelfish.util;

import org.slf4j.helpers.MessageFormatter;

public class MessageUtil {

  public static String format(String inputMessage, Object... objects) {
    return MessageFormatter.arrayFormat(inputMessage, objects).getMessage();
  }



}

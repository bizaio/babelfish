package io.biza.babelfish.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.helpers.MessageFormatter;

public class MessageUtil {

  public static String format(String inputMessage, Object... objects) {
    return MessageFormatter.arrayFormat(inputMessage, objects).getMessage();
  }
  
  public static String exceptionString(Exception e) {
	  StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      return sw.toString();
  }



}

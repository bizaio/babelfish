package io.biza.cdr.babelfish.tests.v1;

import java.net.URI;

/**
 * ModelConstants
 * This defines valid models for manipulation within test cases
 *
 */
public class ModelConstants {
  public static URI DEFAULT_FIRST_URI = URI.create("http://localhost/?page=1");
  public static URI DEFAULT_SELF_URI = URI.create("http://localhost/?page=3");
  public static URI DEFAULT_LAST_URI = URI.create("http://localhost/?page=10");
  public static URI DEFAULT_PREV_URI = URI.create("http://localhost/?page=2");
  public static URI DEFAULT_NEXT_URI = URI.create("http://localhost/?page=4");

}

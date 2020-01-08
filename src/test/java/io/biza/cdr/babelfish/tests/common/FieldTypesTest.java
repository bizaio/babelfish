package io.biza.cdr.babelfish.tests.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.support.FormatChecker;

@DisplayName("CDR Common Field Types")
public class FieldTypesTest {
  // TODO: Verify ACN

  @Test
  @DisplayName("Verify ABN")
  void verifyAbn() {
    // invalid abn
    assertFalse(FormatChecker.isAbn("invalid abn"));
    
    // invalid length abn
    assertFalse(FormatChecker.isAbn("12341234"));
    
    // checksum failure abn
    assertFalse(FormatChecker.isAbn("12345678901"));
    
    // valid abn (Biza Pty Ltd) with spaces
    assertTrue(FormatChecker.isAbn("54 624 797 655"));
    
    // valid abn (Biza Pty Ltd) without spaces
    assertTrue(FormatChecker.isAbn("54624797655"));
    
  }
  
  @Test
  @DisplayName("Verify Phone Number")
  void verifyPhoneNumber() {
    // Verify invalid string
    assertFalse(FormatChecker.isPhoneNumber("invalid phone", false));

    // valid Australian phone number
    assertTrue(FormatChecker.isPhoneNumber("(02) 3307 1234", false));

    // valid Australian phone number in international format
    assertTrue(FormatChecker.isPhoneNumber("+61233071234", false));

    // valid Australian mobile number with area code (should be valid for FNN's too)
    assertTrue(FormatChecker.isPhoneNumber("0401123123", false));
  }

  @Test
  @DisplayName("Verify ASCII String")
  void verifyASCIIString() {
    // Verify valid string
    assertTrue(FormatChecker.isASCIIString("Basic String"));
    // Check against null value
    assertFalse(FormatChecker.isASCIIString(Character.toString('\0')));
  }

  @Test
  @DisplayName("Verify Natural Number")
  void verifyNaturalNumber() {
    // Verify valid positive integer
    assertTrue(FormatChecker.isNaturalNumber(1));
    // Verify valid zero value
    assertTrue(FormatChecker.isNaturalNumber(0));
    // Verify invalid negative value
    assertFalse(FormatChecker.isNaturalNumber(-99));
  }

  @Test
  @DisplayName("Verify Positive Integer")
  void verifyPositiveInteger() {
    // Verify valid positive integer
    assertTrue(FormatChecker.isPositiveInteger(1));
    // Verify invalid zero value
    assertFalse(FormatChecker.isPositiveInteger(0));
    // Verify invalid negative value
    assertFalse(FormatChecker.isPositiveInteger(-99));
  }

  @Test
  @DisplayName("Verify Negative Integer")
  void verifyNegativeInteger() {
    // Verify invalid positive integer
    assertFalse(FormatChecker.isNegativeInteger(1));
    // Verify valid zero value
    assertTrue(FormatChecker.isNegativeInteger(0));
    // Verify valid negative value
    assertTrue(FormatChecker.isNegativeInteger(-99));
  }

  @Test
  @DisplayName("Verify Date Time String")
  void verifyDateTimeString() {
    // Verify valid string
    assertTrue(FormatChecker.isDateTimeString("1985-04-12T23:20:50.52"));
    // Verify invalid string (missing time)
    assertFalse(FormatChecker.isDateTimeString("2007-05-01"));
    // Verify invalid string (missing date)
    assertFalse(FormatChecker.isDateTimeString("15:43:00"));
  }

  @Test
  @DisplayName("Verify Date String")
  void verifyDateString() {
    // Verify valid string
    assertTrue(FormatChecker.isDateString("2007-05-01"));
    // Verify invalid string (includes time)
    assertFalse(FormatChecker.isDateString("2007-05-01T15:43:00.12345Z"));
    // Verify invalid string (is a time value)
    assertFalse(FormatChecker.isDateString("15:43:00"));
  }

  @Test
  @DisplayName("Verify Currency String")
  void verifyCurrencyString() {
    // Verify valid string (AUD)
    assertTrue(FormatChecker.isCurrencyString("AUD"));
    // Verify valid string (XAU - Edge Case but Valid)
    assertTrue(FormatChecker.isCurrencyString("XAU"));
    // Verify invalid string (BTC - Invalid for ISO-4217)
    assertFalse(FormatChecker.isCurrencyString("BTC"));
    // Verify invalid string (wonky text)
    assertFalse(FormatChecker.isCurrencyString("Invalid Currency"));
  }

  @Test
  @DisplayName("Verify Rate String")
  void verifyRateString() {
    // Verify valid rate value
    assertTrue(FormatChecker.isRateString("0.05"));
    // Verify invalid rate value (missing left side of decimal)
    assertFalse(FormatChecker.isRateString(".05"));
    // Verify invalid rate value (missing right side of decimal)
    assertFalse(FormatChecker.isRateString("1"));
    // Verify invalid rate value (out of range beyond -1)
    assertFalse(FormatChecker.isRateString("-1.5"));
    // Verify invalid rate value (out of range beyond 1)
    assertFalse(FormatChecker.isRateString("1.5"));
    // Verify invalid rate value (wonky text)
    assertFalse(FormatChecker.isRateString("Invalid"));
  }

  @Test
  @DisplayName("Verify Amount String")
  void verifyAmountString() {
    // Verify valid amount string
    assertTrue(FormatChecker.isAmountString("0.01"));
    // Verify valid amount string
    assertTrue(FormatChecker.isAmountString("0.0122222"));
    // Verify valid amount string
    assertTrue(FormatChecker.isAmountString("1111111111111111.11"));
    // Verify valid amount string
    assertTrue(FormatChecker.isAmountString("-111111111111111.11"));
    // Verify valid zero amount string
    assertTrue(FormatChecker.isAmountString("0.00"));
    // Verify invalid amount string (missing right side)
    assertFalse(FormatChecker.isAmountString("1"));
    // Verify invalid amount string (missing enough decimals)
    assertFalse(FormatChecker.isAmountString("1.1"));
    // Verify invalid amount string (too many digits on left side)
    assertFalse(FormatChecker.isAmountString("11111111111111111.1"));
    // Verify invalid amount string (wonky text)
    assertFalse(FormatChecker.isAmountString("Invalid"));
  }

  @Test
  @DisplayName("Verify URIString")
  void verifyUriString() {
    // Verify valid uri
    assertTrue(FormatChecker.isUriString("http://google.com/"));
    // Verify valid uri
    assertTrue(FormatChecker.isUriString("http://google.com/?query=string"));
    // Verify valid uri (the rfc is very broad)
    assertTrue(FormatChecker.isUriString("google.com"));
    // Verify invalid uri
    assertFalse(FormatChecker.isUriString("definitely not a valid uri"));
  }


}

package io.biza.babelfish.cdr.abstracts.payloads.common;

import java.time.OffsetDateTime;
import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.CommonDiscoveryStatusType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class CommonDiscoveryStatusV1 {

  public abstract OffsetDateTime detectionTime();

  public abstract CommonDiscoveryStatusType status();

  public abstract String explanation();

  public abstract OffsetDateTime expectedResolutionTime();

  @AssertTrueBabelfish(message = "Explanation is MANDATORY if Status is not OK",
      fields = {"status", "explanation"})
  private boolean isExplanationPresent() {
    if (status() == null) {
      return true;
    }
    return !Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.OK})
        .contains(status()) ? FormatChecker.isNotEmpty(explanation()) : true;
  }

  @AssertTrueBabelfish(
      message = "Detection Time should be PRESENT when status is PARTIAL_FAILURE or UNAVAILABLE",
      fields = {"status", "detectionTime"})
  private boolean isDetectionTimePresent() {
    if (status() == null) {
      return true;
    }
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.PARTIAL_FAILURE,
        CommonDiscoveryStatusType.UNAVAILABLE}).contains(status())
            ? FormatChecker.isDefined(detectionTime())
            : true;
  }

  @AssertTrueBabelfish(
      message = "Detection Time should be ABSENT when status isn't PARTIAL_FAILURE or UNAVAILABLE",
      fields = {"status", "detectionTime"})
  private boolean isDetectionTimeAbsent() {
    if (status() == null) {
      return true;
    }
    return !Arrays.asList(new CommonDiscoveryStatusType[] {
        CommonDiscoveryStatusType.PARTIAL_FAILURE, CommonDiscoveryStatusType.UNAVAILABLE})
        .contains(status()) ? !FormatChecker.isDefined(detectionTime()) : true;
  }

  @AssertTrueBabelfish(message = "Resolution Time should be ABSENT when status is OK",
      fields = {"status", "expectedResolutionTime"})
  private boolean isExpectedResolutionTimeAbsent() {
    if (status() == null) {
      return true;
    }
    return Arrays.asList(new CommonDiscoveryStatusType[] {CommonDiscoveryStatusType.OK})
        .contains(status()) ? !FormatChecker.isDefined(expectedResolutionTime()) : true;
  }
}

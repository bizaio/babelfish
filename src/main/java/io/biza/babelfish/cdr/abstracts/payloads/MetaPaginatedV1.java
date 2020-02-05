package io.biza.babelfish.cdr.abstracts.payloads;

import io.biza.babelfish.cdr.support.AssertTrueBabelfish;

public abstract class MetaPaginatedV1 {

  public abstract Integer totalRecords();

  public abstract Integer totalPages();

  @AssertTrueBabelfish(message = "If totalRecords is 0 totalPages MUST be 0",
      fields = {"totalRecords", "totalPages"})
  private boolean isZeroTotalRecordsMatchesZeroPages() {
    return (totalRecords() != null && totalRecords() == 0)
        ? ((totalPages() != null && totalPages() == 0) ? true : false)
        : true;
  }

  @AssertTrueBabelfish(message = "If totalPages is 0 then totalRecords should be 0",
      fields = {"totalRecords", "totalPages"})
  private boolean isZeroTotalPagesButNotZeroRecords() {
    return (totalPages() != null && totalPages() == 0)
        ? ((totalRecords() != null && totalRecords() == 0) ? true : false)
        : true;
  }
}

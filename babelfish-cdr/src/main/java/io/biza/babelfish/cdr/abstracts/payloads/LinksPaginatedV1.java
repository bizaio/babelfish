package io.biza.babelfish.cdr.abstracts.payloads;

import java.net.URI;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class LinksPaginatedV1 {

  public abstract URI self();

  public abstract URI first();

  public abstract URI prev();

  public abstract URI next();

  public abstract URI last();

  /**
   * Minimal field validation possible at POJO level Scenario: [ self, first, prev, next, last ]
   * First Page and not Last: [ Yes, No, No, Yes, Yes ] Last Page and not First: [ Yes, Yes, Yes,
   * No, No ] First and Last Page: [ Yes, No, No, No, No ] Page not First or Last: [ Yes, Yes, Yes,
   * Yes, Yes ]
   */
  @JsonIgnore
  @AssertTrueBabelfish(message = "Previous page set but First Page not set",
      fields = {"prev", "first"})
  private boolean isFirstSetWhenPrevExists() {
    return prev() != null && first() == null ? false : true;
  }

  @AssertTrueBabelfish(message = "Next page set but Last Page not set", fields = {"next", "last"})
  private boolean isLastSetWhenNextExists() {
    return next() != null && last() == null ? false : true;
  }

  @AssertTrueBabelfish(
      message = "While on first and last page (next & prev null), zero links should be defined outside of self",
      fields = {"next", "prev", "first", "last"})
  private boolean isFirstAndLastPage() {
    return (next() == null && prev() == null) ? ((first() != null || last() != null) ? false : true)
        : true;
  }

  @AssertTrueBabelfish(message = "First Page URI should contain a parameter of page == 1",
      fields = {"first"})
  private boolean isFirstPagePageParamValid() {
    return first() != null
        ? (FormatChecker.mapifyQueryString(first()).get("page") != null
            && FormatChecker.mapifyQueryString(first()).get("page").equals("1"))
        : true;
  }

  @AssertTrueBabelfish(message = "Next Page URI should have a page equal to current page plus 1",
      fields = {"self", "next"})
  private boolean isNextPageParamValid() {
    if (self() != null && next() != null) {
      int selfPage = Integer.parseInt(FormatChecker.mapifyQueryString(self()).get("page"));
      int nextPage = Integer.parseInt(FormatChecker.mapifyQueryString(next()).get("page"));
      selfPage++;

      if (selfPage == nextPage) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

  @AssertTrueBabelfish(message = "Prev Page URI should have a page equal to current page minus 1",
      fields = {"self", "prev"})
  private boolean isPrevPageParamValid() {
    if (self() != null && prev() != null) {
      int selfPage = Integer.parseInt(FormatChecker.mapifyQueryString(self()).get("page"));
      int prevPage = Integer.parseInt(FormatChecker.mapifyQueryString(prev()).get("page"));
      selfPage--;

      if (selfPage == prevPage) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }
}

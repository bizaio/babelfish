package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description = "Paginated Meta Details", parent = Meta.class)
public class MetaPaginated {
  @BabelFishModelProperty(description = "The total number of records in the full set.",
      required = true)
  @NonNull
  @NotNull
  @Min(0)
  Integer totalRecords;

  @BabelFishModelProperty(description = "The total number of pages in the full set.",
      required = true)
  @NonNull
  @NotNull
  @Min(0)
  Integer totalPages;
}

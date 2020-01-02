package io.biza.cdr.babelfish.model.common;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@BabelFishModel(description = "Paginated Links", parent = Links.class)
public class LinksPaginated {
	
	/**
	 * Minimal field validation possible at POJO level
	 * Scenario: [ self, first, prev, next, last ]
	 * First Page and not Last: [ Yes, No, No, Yes, Yes ]
	 * Last Page and not First: [ Yes, Yes, Yes, No, No ]
	 * First and Last Page:     [ Yes, No, No, No, No ]
	 * Page not First or Last:  [ Yes, Yes, Yes, Yes, Yes ]
	 */
	@BabelFishModelProperty(description = "Fully qualified link that generated the current response document", required = true, dataType = "java.lang.String")
	@NonNull
	@NotNull
	@JsonSerialize(converter = UriToUriStringConverter.class)
	URI self;

	@BabelFishModelProperty(description = "URI to the first page of this set. Mandatory if this response is not the first page", dataType = "java.lang.String")
	@JsonSerialize(converter = UriToUriStringConverter.class)
	URI first;

	@BabelFishModelProperty(description = "URI to the previous page of this set. Mandatory if this response is not the first page", dataType = "java.lang.String")
	@JsonSerialize(converter = UriToUriStringConverter.class)
	URI prev;

	@BabelFishModelProperty(description = "URI to the next page of this set. Mandatory if this response is not the last page", dataType = "java.lang.String")
	@JsonSerialize(converter = UriToUriStringConverter.class)
	URI next;

	@BabelFishModelProperty(description = "URI to the last page of this set. Mandatory if this response is not the last page", dataType = "java.lang.String")
	@JsonSerialize(converter = UriToUriStringConverter.class)
	URI last;
	
    @AssertTrue(message = "Previous page set but First Page not set")
    private boolean isFirstSetWhenPrevExists() {
    	return prev != null && first == null ? false : true; 
    }
    
    @AssertTrue(message = "Next page set but Last Page not set")
    private boolean isLastSetWhenNextExists() {
    	return next != null && last == null ? false : true; 
    }
    
    @AssertTrue(message = "While on first and last page (next & prev null), zero links should be defined outside of self")
    private boolean isFirstAndLastPage() {
    	return (next == null && prev == null) ? ((first != null || last != null) ? false : true) : true;
    }


}

package io.biza.babelfish.cdr.models.requests.cdr;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CDRRequestClaimsIdToken {
  
  /**
   * CDR Specified Request Object
   * https://consumerdatastandardsaustralia.github.io/standards/#request-object
   */
  @JsonProperty("acr")
  CDRRequestClaimsIdTokenAcr acr;
  
}
package io.biza.babelfish.cdr.models.requests.cdr;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.oidc.CDRLoA;
import io.biza.babelfish.oidc.enumerations.OAuth2ResponseType;
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
public class CDRRequestClaimsIdTokenAcr {
  
  /**
   * CDR Specified Request Object
   * https://consumerdatastandardsaustralia.github.io/standards/#request-object
   */
  @JsonProperty("essential")
  @Builder.Default
  Boolean essential = true;
  
  @JsonProperty("values")
  List<CDRLoA> values;
  
}

package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import io.biza.cdr.babelfish.enumerations.AddressPAFFlatUnitType;
import io.biza.cdr.babelfish.enumerations.AddressPAFFloorLevelType;
import io.biza.cdr.babelfish.enumerations.AddressPAFPostalDeliveryType;
import io.biza.cdr.babelfish.enumerations.AddressPAFStateType;
import io.biza.cdr.babelfish.enumerations.AddressPAFStreetSuffix;
import io.biza.cdr.babelfish.enumerations.AddressPAFStreetType;
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
@BabelFishModel(description = "Australian address formatted according to the file format defined by the [PAF file format](https://auspost.com.au/content/dam/auspost_corp/media/documents/australia-post-data-guide.pdf)")
public class CommonPAFAddress {

	@BabelFishModelProperty(description = "Unique identifier for an address as defined by Australia Post.  Also known as Delivery Point Identifier")
	// AMBIGUOUS: CDS Specifies this as optional but it is Mandatory from AusPost PAF
	@NotNull
	@NonNull
	@Pattern(regexp = "[0-9]{8}")
	String dpid;

	@BabelFishModelProperty(description = "Thoroughfare number for a property (first number in a property ranged address)")
	// AMBIGUOUS: CDS Specifies this as Integer but it is a 0 padded string, supply custom String based setter
	@Min(1)
	Integer thoroughfareNumber1;

	public void setThoroughfareNumber1(String thoroughfareNumber1) {
		if (thoroughfareNumber1 != null) {
			this.thoroughfareNumber1 = thoroughfareNumber1.equals("00000") ? null
					: Integer.parseInt(thoroughfareNumber1);
		}
	}

	@BabelFishModelProperty(description = "Suffix for the thoroughfare number. Only relevant is thoroughfareNumber1 is populated")
	String thoroughfareNumber1Suffix;

	@AssertTrue(message = "Thoroughfare Suffixes should only be set when Thoroughfare Numbers are set")
	private boolean isInvalidSuffixes() {
		if (thoroughfareNumber1Suffix != null && thoroughfareNumber1 == null) {
			return false;
		}
		if (thoroughfareNumber2Suffix != null && thoroughfareNumber2 == null) {
			return false;
		}
		return true;
	}

	@BabelFishModelProperty(description = "Second thoroughfare number (only used if the property has a ranged address eg 23-25)")
	@Min(1)
	// AMBIGUOUS: CDS Specifies this as Integer but it is a 0 padded string, supply custom String based setter
	Integer thoroughfareNumber2;

	public void setThoroughfareNumber2(String thoroughfareNumber2) {
		if (thoroughfareNumber2 != null) {
			this.thoroughfareNumber2 = thoroughfareNumber2.equals("00000") ? null
					: Integer.parseInt(thoroughfareNumber2);
		}
	}

	@BabelFishModelProperty(description = "Suffix for the second thoroughfare number. Only relevant is thoroughfareNumber2 is populated")
	String thoroughfareNumber2Suffix;
	
	@AssertTrue(message = "Thoroughfare Number 2 must only be set when Thoroughfare Number 1 exists and must be greater than Thoroughfare Number 1")
	private boolean isThoroughFareNumber2LargerThan1() {
		if (thoroughfareNumber1 == null) {
			if (thoroughfareNumber2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (thoroughfareNumber2 == null) {
				return false;
			} else {
				if (thoroughfareNumber2 > thoroughfareNumber1) {
					return true;
				} else {
					return false;
				}
			}

		}
	}

	@BabelFishModelProperty(description = "Type of flat or unit for the address", dataType = "java.lang.String")
	AddressPAFFlatUnitType flatUnitType;

	@BabelFishModelProperty(description = "Unit number (including suffix, if applicable)")
	@Pattern(regexp = "^([A-Za-z0-9]){1,7}$")
	String flatUnitNumber;

	@BabelFishModelProperty(description = "Type of floor or level for the address", dataType = "java.lang.String")
	AddressPAFFloorLevelType floorLevelType;

	@BabelFishModelProperty(description = "Floor or level number (including alpha characters)")
	@Pattern(regexp = "^([A-Za-z0-9]){1,5}$")
	String floorLevelNumber;

	@BabelFishModelProperty(description = "Allotment number for the address")
	@Pattern(regexp = "^([A-Za-z0-9]){1,6}$")
	String lotNumber;

	@BabelFishModelProperty(description = "Building/Property name 1")
	@Pattern(regexp = "^([A-Za-z0-9]){1,30}$")
	String buildingName1;

	@BabelFishModelProperty(description = "Building/Property name 2")
	@Pattern(regexp = "^([A-Za-z0-9]){1,30}$")
	String buildingName2;

	@BabelFishModelProperty(description = "The name of the street")
	@Pattern(regexp = "^([A-Za-z0-9]){1,30}$")
	String streetName;

	@BabelFishModelProperty(description = "The street type. Valid enumeration defined by Australia Post PAF code file", dataType = "java.lang.String")
	AddressPAFStreetType streetType;

	@BabelFishModelProperty(description = "The street type suffix. Valid enumeration defined by Australia Post PAF code file", dataType = "java.lang.String")
	AddressPAFStreetSuffix streetSuffix;

	@BabelFishModelProperty(description = "Postal delivery type. (eg. PO BOX). Valid enumeration defined by Australia Post PAF code file", dataType = "java.lang.String")
	AddressPAFPostalDeliveryType postalDeliveryType;

	@BabelFishModelProperty(description = "Postal delivery number if the address is a postal delivery type")
	@Min(1)
	// AMBIGUOUS: CDS Specifies this as Integer but it is a 0 padded string, supply custom String based setter
	Integer postalDeliveryNumber;

	public void setPostalDeliveryNumber(String postalDeliveryNumber) {
		if (postalDeliveryNumber != null) {
			this.postalDeliveryNumber = postalDeliveryNumber.equals("00000") ? null
					: Integer.parseInt(postalDeliveryNumber);
		}
	}

	@BabelFishModelProperty(description = "Postal delivery number prefix related to the postal delivery number")
	@Pattern(regexp = "^([A-Za-z){1,3}$")
	String postalDeliveryNumberPrefix;

	@BabelFishModelProperty(description = "Postal delivery number suffix related to the postal delivery number")
	@Pattern(regexp = "^([A-Za-z){1,3}$")
	String postalDeliveryNumberSuffix;

	@BabelFishModelProperty(description = "Full name of locality", required = true)
	@NonNull
	@NotNull
	@Pattern(regexp = "^([A-Za-z0-9]){1,46}$")
	String localityName;

	@BabelFishModelProperty(description = "Postcode for the locality", required = true)
	@NonNull
	@NotNull
	// AMBIGUOUS: The PAF file format specifies alphanumeric up to 12 characters?
	@Pattern(regexp = "^([A-Za-z0-9]){1,12}$")
	String postcode;

	@BabelFishModelProperty(description = "State in which the address belongs. Valid enumeration defined by Australia Post PAF code file", required = true, dataType = "java.lang.String")
	@NonNull
	@NotNull
	AddressPAFStateType state;
}

# Babelfish CDR 

[![Maven Central](https://img.shields.io/maven-central/v/io.biza/babelfish-cdr?label=latest%20release)](https://search.maven.org/artifact/io.biza/babelfish-cdr) ![Nexus Latest Snapshot](https://img.shields.io/nexus/s/io.biza/babelfish-cdr?label=latest%20snapshot&server=https%3A%2F%2Foss.sonatype.org) [![Consumer Data Standards v1.2.0](https://img.shields.io/badge/Consumer%20Data%20Standards-v1.2.0-success)](https://consumerdatastandardsaustralia.github.io/standards)

[![develop build](https://img.shields.io/travis/com/bizaio/babelfish-cdr/develop?label=develop%20build)](https://travis-ci.com/bizaio/babelfish-cdr) [![master build](https://img.shields.io/travis/com/bizaio/babelfish-cdr/master?label=master%20build)](https://travis-ci.com/bizaio/babelfish-cdr) [![GitHub issues](https://img.shields.io/github/issues/bizaio/babelfish-cdr)](https://github.com/bizaio/babelfish-cdr/issues) ![GitHub](https://img.shields.io/github/license/bizaio/babelfish-cdr) 

The Babelfish CDR Library is a Java based Maven artifact for the efficient processing of JSON payloads associated with the Australian Consumer Data Right (aka *open banking*). It provides the ability to parse, manipulate, generate and validate compliant payloads associated with the Consumer Data Standards. It is annotated with industry standard [Jackson](https://github.com/FasterXML/jackson) and [OpenAPI (*fka Swagger*)](https://github.com/swagger-api/swagger-core) annotations to enable smooth integration into downstream projects.

## Features

  - Jackson mappings for **all payloads** defined within the [Consumer Data Standards](https://consumerdatastandardsaustralia.github.io/standards)
  - On demand conversion **between payload versions** powered by [Orika Mapper](https://github.com/orika-mapper/orika)
  - **All enumerations** defined within [Consumer Data Standards](https://consumerdatastandardsaustralia.github.io/standards) with **human readable descriptions** for presentation layer
  - Serialisation and Deserialisation to **strong Java types** for all [Common Field Types](https://consumerdatastandardsaustralia.github.io/standards/#common-field-types)
  - Java Validation (JSR-303) rules for all **required attributes**, **[Pagination](https://consumerdatastandardsaustralia.github.io/standards/#pagination)** and **[Product & Account Components](https://consumerdatastandardsaustralia.github.io/standards/#product-amp-account-components)**
  - Fluent Builders for all payloads [powered by Lombok](https://projectlombok.org/)
  - [OpenAPI 3 (fka Swagger)](https://github.com/swagger-api/swagger-core) annotations for all payloads suitable and [tested](https://github.com/bizaio/deepthought) for Spring Framework and [Springdoc](https://springdoc.org)
  - **Reasonably** well tested, over **350 validation rules** and nearly **300 tests** (but there is always room for more!)

## Quick Start

Processing an input payload and validating it is as simple as:

```java
    ResponseBankingProductByIdV2 productResponse = new ObjectMapper().readValue(
        "{\"links\":{\"self\":\"http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f\"},\"meta\":{},"
        + "\"data\":{\"productId\":\"073e7e70-357d-4858-8f52-92283f4edd6f\",\"lastUpdated\":\"2020-02-03T06:32:27Z\","
        + "\"productCategory\":\"TRANS_AND_SAVINGS_ACCOUNTS\",\"name\":\"Example Product\",\"description\":\"Example Product Description\","
        + "\"brand\":\"ACME\",\"brandName\":\"ACME Bank\",\"isTailored\":false}}",
        ResponseBankingProductByIdV2.class);
```

Generating a compliant payload is as simple as:

```java
    ResponseBankingProductByIdV2 productResponse =
        ResponseBankingProductByIdV2.builder()
            .links(LinksV1.builder().self(URI.create(
                "http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f"))
                .build())
            .meta(MetaV1.builder().build())
            .data(BankingProductDetailV2.builder().productId("073e7e70-357d-4858-8f52-92283f4edd6f")
                .lastUpdated(OffsetDateTime.now())
                .productCategory(BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS)
                .name("Example Product").description("Example Product Description").brand("ACME")
                .tailored(false).build())
            .build();
    Set<ConstraintViolation<ResponseBankingProductByIdV2>> productResponseValidation =
        Validation.buildDefaultValidatorFactory().getValidator().validate(productResponse);
    if (productResponseValidation.isEmpty()) {
      System.out.println(new ObjectMapper().setSerializationInclusion(Include.NON_ABSENT).writeValueAsString(productResponse));
    } else {
      System.out.println(
          "Object failed validation with errors of: " + productResponseValidation.toString());
    }
```

Which yields: 
```json
{
   "data":{
      "productId":"073e7e70-357d-4858-8f52-92283f4edd6f",
      "lastUpdated":"2020-02-05T10:02:25.26832+11:00",
      "productCategory":"TRANS_AND_SAVINGS_ACCOUNTS",
      "name":"Example Product",
      "description":"Example Product Description",
      "brand":"ACME",
      "isTailored":false
   },
   "links":{
      "self":"http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f"
   },
   "meta":{

   }
}
```

Babelfish CDR is currently developed and maintained by [Biza.io](https://www.biza.io).


## Table of Contents

- [Features](#features)
- [Quick Start](#quick-start)
- [Usage](#usage)
- [Projects](#projects)
- [Support](#support)
- [Compatibility](#compatibility)
- [Prerequisites](#prerequisites)
- [Using Babelfish CDR in your project](#using-babelfish-cdr-in-your-project)
- [Extended Features](#extended-features)
- [Building](#building)
- [Contributing](#contributing)
- [License](#license)

## Usage

[(Back to top)](#table-of-contents)

This library is available on [Maven Central](https://search.maven.org/artifact/io.biza/babelfish-cdr) and therefore can be utilised by adding the following lines to your `pom.xml`:
```xml
<dependency>
    <groupId>io.biza</groupId>
    <artifactId>babelfish-cdr</artifactId>
    <version>1.2.0</version>
</dependency>
```

## Projects

`babelfish-cdr` is utilised in a number of public projects:
- [Deep Thought](https://github.com/bizaio/deepthought)
- [Electronic Thumb](https://github.com/bizaio/electronic-thumb)

If you are using `babelfish-cdr` in your project drop us an [email](mailto:hello@biza.io) and we will add it here.

## Support

[Biza Pty Ltd](https://biza.io/) are currently the primary maintainers of this software. 

We welcome bug reports via [GitHub Issues](https://github.com/bizaio/babelfish-cdr/) or if you prefer via [email](mailto:hello@biza.io).

If you are looking for commercial support for this library please contact us via [email](mailto:hello@biza.io).


## Compatibility

[(Back to top)](#table-of-contents)

The Babelfish CDR library tries to provide full coverage of payloads defined within the [Consumer Data Standards](https://consumerdatastandardsaustralia.github.io/standards). While we try to align our version numbers to those the Standards unfortunately the Standards have used all of the *x.y.z* versioning of the semantic versioning scheme. Consequently the following table outlines the alignment between Babelfish versions and the Standards:

Babelfish CDR Version                | Release Date | CDS Spec Compatibility     | Notes                                                             | Status
------------------------------------ | ------------ | -------------------------- | ----------------------------------------------------------------- | --------
1.2.1-SNAPSHOT (**current develop**) | Regular      | 1.2.0                      | Snapshot Development Release                                      | Active Development
1.2.0 (**current stable**)           | 2020-02-05   | 1.2.0                      | [tag v1.2.0](https://github.com/bizaio/babelfish-cdr/tree/v1.2.0) | Supported
1.0.0                                | 2020-02-04   | 1.0.0                      | Initial Release, **do not use**                                   | Deprecated

## Prerequisites

[(Back to top)](#table-of-contents)

You need the following installed and available in your $PATH during compilation:
- Java 11+
- Apache Maven 3.6.3 or later

## Using Babelfish CDR in your project

[(Back to top)](#table-of-contents)

Babelfish CDR is intended for embedding within your CDR client or server project as a Maven (or Gradle) dependency. Once it is added to your project you gain access to the ability to manipulate CDR payloads in a number of different ways.

### Parse and Validate CDR Payload

[(Back to top)](#table-of-contents)

Parsing a sample JSON payload can be conducted using Jackson and then verified using Java Validation API:

```java
    ResponseBankingProductByIdV2 productResponse = new ObjectMapper().readValue(
        "{\"links\":{\"self\":\"http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f\"},\"meta\":{},"
        + "\"data\":{\"productId\":\"073e7e70-357d-4858-8f52-92283f4edd6f\",\"lastUpdated\":\"2020-02-03T06:32:27Z\","
        + "\"productCategory\":\"TRANS_AND_SAVINGS_ACCOUNTS\",\"name\":\"Example Product\",\"description\":\"Example Product Description\","
        + "\"brand\":\"ACME\",\"brandName\":\"ACME Bank\",\"isTailored\":false}}",
        ResponseBankingProductByIdV2.class);
    Set<ConstraintViolation<ResponseBankingProductByIdV2>> productResponseValidation =
        Validation.buildDefaultValidatorFactory().getValidator().validate(productResponse);
```

### Validate and Generate CDR Payload

[(Back to top)](#table-of-contents)

Generating a CDR Payload can be conducted using the fluent builder API within Babelfish. Additionally the payload can be validated using Java Validation API.

An example is as follows:

```java
ResponseBankingProductByIdV2 productResponse =
        ResponseBankingProductByIdV2.builder()
            .links(LinksV1.builder().self(URI.create(
                "http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f"))
                .build())
            .meta(MetaV1.builder().build())
            .data(BankingProductDetailV2.builder().productId("073e7e70-357d-4858-8f52-92283f4edd6f")
                .lastUpdated(OffsetDateTime.now())
                .productCategory(BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS)
                .name("Example Product").description("Example Product Description").brand("ACME")
                .tailored(false).build())
            .build();
    Set<ConstraintViolation<ResponseBankingProductByIdV2>> productResponseValidation =
        Validation.buildDefaultValidatorFactory().getValidator().validate(productResponse);
    if (productResponseValidation.isEmpty()) {
      System.out.println(new ObjectMapper().setSerializationInclusion(Include.NON_ABSENT).writeValueAsString(productResponse));
    } else {
      System.out.println(
          "Object failed validation with errors of: " + productResponseValidation.toString());
    }
```

### Convert CDR Payload between versions

[(Back to top)](#table-of-contents)

Babelfish enables you to convert between two different payload versions. This means that a Data Recipient can build for a newer (or older) version of a payload while maintaining ongoing compatibility from individual Data Holders who may be ahead or behind the version expected by the Recipient. We use Orika and a set of specific mappers for each different payload version and then make it available in `BabelFishConverter`.

An example is as follows:

```java
    BankingProductDetailV2 detail = BankingProductDetailV2.builder()
        .productId("073e7e70-357d-4858-8f52-92283f4edd6f").lastUpdated(OffsetDateTime.now())
        .productCategory(BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS).name("Example Product")
        .description("Example Product Description").brand("ACME").isTailored(false).build();

    // Downgrade it to V1
    BankingProductDetailV1 downgradedDetail =
        BabelFishConverter.convert(detail, BankingProductDetailV1.class);
    // Upgrade back to V2
    BankingProductDetailV2 upgradedDetail = BabelFishConverter.convert(downgradedDetail, BankingProductDetailV2.class);

```

### Using in Spring

We are actively using Babelfish CDR payload descriptions within [Deep Thought](https://github.com/bizaio/deepthought) as part of a Spring + Springdoc project. The following demonstrates the definition of a `Get Product Detail` endpoint:

```java
@Operation(summary = "Get Product Detail",
      description = "Returns details product information based on the specified product identifier")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Response Successful",
              content = @Content(schema = @Schema(oneOf = {ResponseBankingProductByIdV1.class,
                  ResponseBankingProductByIdV2.class}))),
          @ApiResponse(responseCode = "404",
              description = "Unable to find requested Product Identifier")})
  @GetMapping(value = "/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE})
  default ResponseEntity<ResponseBankingProductByIdV2> getProductDetail(
      @NotNull @Valid @PathVariable("productId") UUID productId) {
    return getDelegate().getProductDetail(productId);
  }
```

## Extended Features

[(Back to top)](#table-of-contents)

Babelfish incorporates a number of customised features to aid developers with integrating with and using the CDR payloads.

### Enumeration Labels

[(Back to top)](#table-of-contents)

All enumerations used within Babelfish provide a human readable label available via the `label()` method:

```java
    System.out.println("name: " + BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS.name()
        + " label: " + BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS.label());
    // Result: name: TRANS_AND_SAVINGS_ACCOUNTS label: Transaction & Savings
```

### JSON (De)Serialisators

[(Back to top)](#table-of-contents)

Babelfish employs a set of `StdConverter` extensions to facilitate the conversion to/from [Common Field Types](https://consumerdatastandardsaustralia.github.io/standards/#common-field-types) specified within the Standards. These are embedded as `@JsonSerialize` and `@JsonDeserialize` annotations within payloads but can be used independently if desired.

The following provides a table of the converters available:

Common Field Type          | CDS JSON Type  | Babelfish Java Type       | Serializer                              | Deserializer 
-------------------------- | -------------- | ------------------------- | ----------------------------------------| -----------------------------------------
DateTimeString             | String         | OffsetDateTime            | OffsetDateTimeToDateTimeStringConverter | DateTimeStringToOffsetDateTimeConverter
DateString                 | String         | LocalDate                 | LocalDateToStringConverter              | StringToLocalDateConverter
CurrencyString             | String         | Currency                  | CurrencyToStringConverter               | StringToCurrencyConverter
RateString                 | String         | BigDecimal                | BigDecimalToRateStringConverter         | RateStringToBigDecimalConverter
AmountString               | String         | BigDecimal                | BigDecimalToAmountStringConverter       | AmountStringToBigDecimalConverter
URIString                  | String         | URI                       | UriToUriStringConverter                 | UriStringToUriConverter
ExternalRef (Country)      | String         | Locale                    | CountryStringToLocaleConverter          | LocaleToCountryStringConverter
ExternalRef (Duration)     | String         | Duration                  | StringToDurationConverter               | DurationToStringConverter
ExternalRef (Period)       | String         | Period                    | StringToPeriodConverter                 | PeriodToStringConverter
ExternalRef (MCC)          | String         | MerchantCategoryCodeType  | StringToMerchantCategoryCodeConverter   | MerchantCategoryCodeToStringConverter
ExternalRef (APCA Number)  | String         | ApcaNumberType            | StringToApcaNumberConverter             | ApcaNumberToStringConverter
Base64                     | String (Base64)| String                    | StringToBase64StringConverter           | Base64StringToStringConverter

### Custom Assertions

[(Back to top)](#table-of-contents)

Many of the payloads contain a custom `javax.validation.Constraint` called `AssertTrueBabelfish`.

`AssertTrueBabelfish` behaves like the default `AssertTrue` validation assertion but includes an additional `String[]` array called `fields` which provides information about the specific field(s) which were involved within a failure of the assertion. This annotation is used **extensively** for validating CDR payloads which utilise the `additionalValue` conditional field structure and are intended to provide human presentable error messages along with a pointer to the field(s) in question.

One such example is in `BankingConstraintV1` which verifies that when a Constraint Type of `MIN_BALANCE` is specified the `additionalValue` field must be in CDS AmountString format.

Another notable example demonstrating compound reporting is within `BankingProductFeeV1`
```java
  @AssertTrueBabelfish(
      message = "One of amount, balanceRate, transactionRate or accruedRate is MANDATORY",
      fields = {"amount", "balanceRate", "accruedRate", "transactionRate"})
```

This assertion reports a set of field names which can be highlighted but for which only one at a time is accepted.

### Phone Number Parsing

[(Back to top)](#table-of-contents)

The `CommonPhoneNumberV1` class contains a number of additional functions using Google's PhoneNumberUtil library. Most notably there is a method of `setWithFullNumber` which accepts a *standard* Australian number (for example 0733006000) and internationalises it into the requested format and fields. Use of this extension functionality is **entirely** optional.


## Building

[(Back to top)](#table-of-contents)

`babelfish-cdr` uses Maven for compilation activies.

To build the library and install it in your local Maven cache:

1. `cd babelfish-cdr`
2. `mvn clean install`


## Contributing

[(Back to top)](#table-of-contents)

1. Clone repository and create a new branch: `$ git checkout https://github.com/bizaio/babelfish-cdr -b my_new_branch`
2. Make changes (including tests please!)
3. Submit Pull Request for integration of changes

## License

[(Back to top)](#table-of-contents)

GNU Lesser General Public License v3.0 2020 - [Biza Pty Ltd](https://biza.io/). Please have a look at the [LICENSE.md](LICENSE.md) for more details.



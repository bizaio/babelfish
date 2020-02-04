# Babelfish CDR 

[![Maven Central](https://img.shields.io/maven-central/v/io.biza/babelfish-cdr?label=latest%20release)](https://search.maven.org/artifact/io.biza/babelfish-cdr) ![Nexus Latest Snapshot](https://img.shields.io/nexus/s/io.biza/babelfish-cdr?label=latest%20snapshot&server=https%3A%2F%2Foss.sonatype.org) [![Consumer Data Standards v1.2.0](https://img.shields.io/badge/Consumer%20Data%20Standards-v1.2.0-success)](https://consumerdatastandardsaustralia.github.io/standards)

[![develop build](https://img.shields.io/travis/com/bizaio/babelfish-cdr/develop?label=develop%20build)](https://travis-ci.com/bizaio/babelfish-cdr) [![master build](https://img.shields.io/travis/com/bizaio/babelfish-cdr/master?label=master%20build)](https://travis-ci.com/bizaio/babelfish-cdr) [![GitHub issues](https://img.shields.io/github/issues/bizaio/babelfish-cdr)](https://github.com/bizaio/babelfish-cdr/issues) ![GitHub](https://img.shields.io/github/license/bizaio/babelfish-cdr) 

The Babelfish CDR Library is a Java based Maven artifact for the efficient processing of JSON payloads associated with the Australian Consumer Data Right (aka *open banking*). It is annotated with industry standard [Jackson](https://github.com/FasterXML/jackson) and [OpenAPI (*fka Swagger*)](https://github.com/swagger-api/swagger-core) annotations to enable smooth integration into downstream projects.

Processing an input payload and validating it is as simple as:

```
    ObjectMapper mapper = new ObjectMapper();
    try {
      ResponseBankingProductV2ById productResponse = mapper.readValue(
          "{\"links\":{\"self\":\"http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f\"},\"meta\":{},"
          + "\"data\":{\"productId\":\"073e7e70-357d-4858-8f52-92283f4edd6f\",\"lastUpdated\":\"2020-02-03T06:32:27Z\","
          + "\"productCategory\":\"TRANS_AND_SAVINGS_ACCOUNTS\",\"name\":\"Example Product\",\"description\":\"Example Product Description\","
          + "\"brand\":\"ACME\",\"brandName\":\"ACME Bank\",\"isTailored\":false}}",
          ResponseBankingProductV2ById.class);
      Validation.buildDefaultValidatorFactory().getValidator().validate(productResponse);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
```

Babelfish CDR is currently developed and maintained by [Biza.io](https://www.biza.io).

## Features

  - Jackson mappings for **all payloads** defined within the [Consumer Data Standards](https://consumerdatastandardsaustralia.github.io/standards)
  - [OpenAPI 3 (fka Swagger)](https://github.com/swagger-api/swagger-core) annotations for all payloads
  - **All enumerations** defined within [Consumer Data Standards](https://consumerdatastandardsaustralia.github.io/standards) with **human readable descriptions** for presentation layer
  - Serialisation and Deserialisation to **strong Java types** for all [Common Field Types](https://consumerdatastandardsaustralia.github.io/standards/#common-field-types)
  - Java Validation (JSR-303) rules for all **required attributes**, **[Pagination](https://consumerdatastandardsaustralia.github.io/standards/#pagination)** and **[Product & Account Components](https://consumerdatastandardsaustralia.github.io/standards/#product-amp-account-components)**
  - On demand conversion **between available payload versions**


## Unsupported Components
BankingProductRateTier.subTier - We believe this is being deprecated at the moment.

## TODO

Add tests to do JSON -> POJO -> JSON verification beyond static object generate and compare


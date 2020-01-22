/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.v1.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.cdr.exception.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Street Type for the Address", enumAsRef = true)
public enum AddressPAFStreetType implements LabelValueEnumInterface {
  // @formatter:off
  ACCS("ACCS", "Access"),
  ALLY("ALLY", "Alley"),
  ALWY("ALWY", "Alleyway"),
  AMBL("AMBL", "Amble"),
  ANCG("ANCG", "Anchorage"),
  APP("APP", "Approach"),
  ARC("ARC", "Arcade"),
  ART("ART", "Artery"),
  AVE("AVE", "Avenue"),
  BASN("BASN", "Basin"),
  BCH("BCH", "Beach"),
  BEND("BEND", "Bend"),
  BLK("BLK", "Block"),
  BVD("BVD", "Boulevard"),
  BRCE("BRCE", "Brace"),
  BRAE("BRAE", "Brae"),
  BRK("BRK", "Break"),
  BDGE("BDGE", "Bridge"),
  BDWY("BDWY", "Broadway"),
  BROW("BROW", "Brow"),
  BYPA("BYPA", "Bypass"),
  BYWY("BYWY", "Byway"),
  CAUS("CAUS", "Causeway"),
  CTR("CTR", "Centre"),
  CNWY("CNWY", "Centreway"),
  CH("CH", "Chase"),
  CIR("CIR", "Circle"),
  CLT("CLT", "Circlet"),
  CCT("CCT", "Circuit"),
  CRCS("CRCS", "Circus"),
  CL("CL", "Close"),
  CLDE("CLDE", "Colonnade"),
  CMMN("CMMN", "Common"),
  CON("CON", "Concourse"),
  CPS("CPS", "Copse"),
  CNR("CNR", "Corner"),
  CSO("CSO", "Corso"),
  CT("CT", "Court"),
  CTYD("CTYD", "Courtyard"),
  COVE("COVE", "Cove"),
  CRES("CRES", "Crescent"),
  CRST("CRST", "Crest"),
  CRSS("CRSS", "Cross"),
  CRSG("CRSG", "Crossing"),
  CRD("CRD", "Crossroad"),
  COWY("COWY", "Crossway"),
  CUWY("CUWY", "Cruiseway"),
  CDS("CDS", "Cul-De-Sac"),
  CTTG("CTTG", "Cutting"),
  DALE("DALE", "Dale"),
  DELL("DELL", "Dell"),
  DEVN("DEVN", "Deviation"),
  DIP("DIP", "Dip"),
  DSTR("DSTR", "Distributor"),
  DR("DR", "Drive"),
  DRWY("DRWY", "Driveway"),
  EDGE("EDGE", "Edge"),
  ELB("ELB", "Elbow"),
  END("END", "End"),
  ENT("ENT", "Entrance"),
  ESP("ESP", "Esplanade"),
  EST("EST", "Estate"),
  EXP("EXP", "Expressway"),
  EXTN("EXTN", "Extension"),
  FAWY("FAWY", "Fairway"),
  FTRK("FTRK", "Fire Track"),
  FITR("FITR", "Firetrail"),
  FLAT("FLAT", "Flat"),
  FOLW("FOLW", "Follow"),
  FTWY("FTWY", "Footway"),
  FSHR("FSHR", "Fore shore"),
  FORM("FORM", "Formation"),
  FWY("FWY", "Freeway"), 
  FRNT("FRNT", "Front"), 
  FRTG("FRTG", "Frontage"), 
  GAP("GAP", "Gap"), 
  GDN("GDN", "Garden"),
  GTE("GTE", "Gate"), 
  GDNS("GDNS", "Gardens"), 
  GTES("GTES", "Gates"), 
  GLD("GLD", "Glade"), 
  GLEN("GLEN", "Glen"),
  GRA("GRA", "Grange"), 
  GRN("GRN", "Green"), 
  GRND("GRND", "Ground"), 
  GR("GR", "Grove"), 
  GLY("GLY", "Gully"),
  HTS("HTS", "Heights"), 
  HRD("HRD", "Highroad"), 
  HWY("HWY", "Highway"), 
  HILL("HILL", "Hill"),
  INTG("INTG", "Interchange"), 
  INTN("INTN", "Inter section"), 
  JNC("JNC", "Junction"), 
  KEY("KEY", "Key"),
  LDG("LDG", "Landing"), 
  LANE("LANE", "Lane"), 
  LNWY("LNWY", "Laneway"), 
  LEES("LEES", "Lees"), 
  LINE("LINE", "Line"),
  LINK("LINK", "Link"), 
  LT("LT", "Little"), 
  LKT("LKT", "Lookout"), 
  LOOP("LOOP", "Loop"), 
  LWR("LWR", "Lower"),
  MALL("MALL", "Mall"), 
  MNDR("MNDR", "Meander"), 
  MEW("MEW", "Mew"), 
  MEWS("MEWS", "Mews"), 
  MWY("MWY", "Motorway"),
  MT("MT", "Mount"), 
  NOOK("NOOK", "Nook"), 
  OTLK("OTLK", "Outlook"), 
  PDE("PDE", "Parade"), 
  PARK("PARK", "Park"),
  PKLD("PKLD", "Parklands"), 
  PKWY("PKWY", "Parkway"), 
  PART("PART", "Part"), 
  PASS("PASS", "Pass"),
  PSGE("PSGE", "Passage"), 
  PATH("PATH", "Path"), 
  PHWY("PHWY", "Pathway"), 
  PIAZ("PIAZ", "Piazzo"), 
  PL("PL", "Place"),
  PLAT("PLAT", "Plateau"), 
  PLZA("PLZA", "Plazo"), 
  PKT("PKT", "Pocket"), 
  PNT("PNT", "Point"), 
  PORT("PORT", "Port"),
  PROM("PROM", "Promenade"), 
  QUAD("QUAD", "Quad"), 
  ODGL("ODGL", "Quadrangle"), QDRT("QDRT", "Quadrant"),
  OY("OY", "Quay"), 
  OYS("OYS", "Quays");
  // @formatter:on
  private String value;

  private String label;

  AddressPAFStreetType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPAFStreetType fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (AddressPAFStreetType b : AddressPAFStreetType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of AddressPAFStreetType from " + text,
        AddressPAFStreetType.class.getSimpleName(), AddressPAFStreetType.values(), text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}

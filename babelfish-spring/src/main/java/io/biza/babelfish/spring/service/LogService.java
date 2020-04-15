/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogService  {

  @Value("${babelfish.log.enableConfidential:false}")
  Boolean enableConfidential;
  
  @Value("${babelfish.log.assumeConfidential:false}")
  Boolean assumeConfidential;
  
  public void info(String msg, Object... objects) {
    info(msg, assumeConfidential, objects);
  }

  public void error(String msg, Object... objects) {
    error(msg, assumeConfidential, objects);
  }

  public void warn(String msg, Object... objects) {
    warn(msg, assumeConfidential, objects);
  }

  public void debug(String msg, Object... objects) {
    debug(msg, assumeConfidential, objects);
  }

  public void info(String msg, Boolean confidential, Object... objects) {
    if(confidential && !enableConfidential) { return; }
    LOG.info(msg, objects);
  }
  
  public void error(String msg, Boolean confidential, Object... objects) {
    if(confidential && !enableConfidential) { return; }
    LOG.error(msg, objects);
  }
  
  public void warn(String msg, Boolean confidential, Object... objects) {
    if(confidential && !enableConfidential) { return; }
    LOG.warn(msg, objects);
  }

  public void debug(String msg, Boolean confidential, Object... objects) {
    if(confidential && !enableConfidential) { return; }
    LOG.debug(msg, objects);
  }

}

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
package io.biza.babelfish.spring.persistence.repository.config;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import io.biza.babelfish.spring.persistence.model.config.ConfigData;

@Repository
public interface ConfigRepository
    extends JpaRepository<ConfigData, UUID>, JpaSpecificationExecutor<ConfigData> {

	  public boolean existsByObjectAndKey(String object, String key);
	  public void deleteByObjectAndKey(String object, String key);
	  public void deleteByObject(String object);
	  public Optional<ConfigData> findByObjectAndKey(String object, String key);
	  public List<ConfigData> findByObject(String object);
}

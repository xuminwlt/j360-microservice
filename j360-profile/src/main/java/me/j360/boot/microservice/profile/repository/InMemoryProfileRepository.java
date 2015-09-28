/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.j360.boot.microservice.profile.repository;

import java.util.ArrayList;
import java.util.List;

import me.j360.boot.microservice.profile.domain.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class InMemoryProfileRepository implements ProfileRepository {

	private final List<Profile> customers = new ArrayList<Profile>();

	public InMemoryProfileRepository() {
		this.customers.add(new Profile(1L, "Oliver", "Gierke"));
		this.customers.add(new Profile(2L, "Andy", "Wilkinson"));
		this.customers.add(new Profile(2L, "Dave", "Syer"));
	}

	@Override
	public List<Profile> findAll() {
		return this.customers;
	}

	@Override
	public Profile findOne(Long id) {
		for (Profile customer : this.customers) {
			if (ObjectUtils.nullSafeEquals(customer.getId(), id)) {
				return customer;
			}
		}
		return null;
	}

}

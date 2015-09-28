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

package me.j360.boot.microservice.profile.web;

import me.j360.boot.microservice.profile.domain.Profile;
import me.j360.boot.microservice.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profiles")
@ExposesResourceFor(Profile.class)
public class ProfileController {

	private final ProfileRepository repository;

	private final EntityLinks entityLinks;

	@Autowired
	public ProfileController(ProfileRepository repository, EntityLinks entityLinks) {
		this.repository = repository;
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET)
	HttpEntity<Resources<Profile>> showCustomers() {
		Resources<Profile> resources = new Resources<Profile>(this.repository.findAll());
		resources.add(this.entityLinks.linkToCollectionResource(Profile.class));
		return new ResponseEntity<Resources<Profile>>(resources, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	HttpEntity<Resource<Profile>> showCustomer(@PathVariable Long id) {
		Resource<Profile> resource = new Resource<Profile>(this.repository.findOne(id));
		resource.add(this.entityLinks.linkToSingleResource(Profile.class, id));
		return new ResponseEntity<Resource<Profile>>(resource, HttpStatus.OK);
	}

}

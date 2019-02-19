package com.prs.business.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByUserNameAndPassword(String userName, String password);
}

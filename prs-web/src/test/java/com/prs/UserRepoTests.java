package com.prs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.user.User;
import com.prs.business.user.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepoTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void findUserByUsernamePasswordShouldReturnUser() {
		entityManager.persist(new User(0, "tuser", "pwd", "fn", "ln", "phone", "email", true, true, true));
		
		Optional<User> u = userRepo.findByUserNameAndPassword("tuser", "pwd");
		
		assertThat(u.get().getFirstName()).isEqualTo("fn");
	}
}

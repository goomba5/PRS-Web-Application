package com.prs;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.user.User;
import com.prs.business.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PRSUserTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void getAllUsersAndCheckNotNull() {
		// Get all users
		Iterable<User> users = userRepo.findAll();
		Assert.assertNotNull(users);
	}
	
//	@Before
}

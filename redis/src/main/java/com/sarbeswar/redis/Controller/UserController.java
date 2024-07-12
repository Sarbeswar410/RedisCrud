package com.sarbeswar.redis.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarbeswar.redis.Dao.UserDao;
import com.sarbeswar.redis.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserDao userDao;

//For save User
	@PostMapping("/saveUser")
	public User createUser(@RequestBody User user) {
		user.setUserId(UUID.randomUUID().toString());
		return userDao.save(user);
	}

	// For get Single User
	@GetMapping("/getUser/{userId}")
	public User getUser(@PathVariable("userId") String userId) {
		return userDao.get(userId);
	}

	// for Find All
	@GetMapping("/getAll")
	public List<User> getAll() {

		Map<Object, Object> allUsers = userDao.findAll();
		Collection<Object> values = allUsers.values();
		List<User> collect = values.stream().map(value -> (User) value).collect(Collectors.toList());
		return collect;

	}

	// delete User
	@DeleteMapping("deleteUser/{userId}")
	public void deleteUser(@PathVariable("userId") String userId) {
		userDao.delete(userId);
	}

}

package com.upen.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private int usersCount = 3;
	
	static {
		users.add(new User( 1,"Adam",new Date()));
		users.add(new User(2,"Eve",new Date()));
		users.add(new User(3,"Jack",new Date()));		
	};
	
	public List<User> findAll(){
		return users;
	}

	public User save(User user){
		if (user.getId()==null) {
			++usersCount;
			user.setId(usersCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(Integer userId){
		for(User user:users) {
			if (user.getId()==(Integer)userId) return user;
		}
		return null;
	}

	public User deleteById(Integer Id){
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId()==(Integer)Id) {
				iterator.remove();
				return user;				
			}
		}
		return null;
	}

	
}

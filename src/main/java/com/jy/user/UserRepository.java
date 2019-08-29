package com.jy.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUEmail(String uEmail);
	public User findByUId(String uId);
	public User findByUName(String uName);


}

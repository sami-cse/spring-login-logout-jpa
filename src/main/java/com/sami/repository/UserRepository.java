package com.sami.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sami.models.Permission;
import com.sami.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Page<User> findAllByOrderByIdDesc(Pageable page);

	public User findByNameAndPasswordAndId(String name, String password, Integer userId);

	/*@Query(value = "select u from User u WHERE u.role=1")
	List<User> getAdminRole();*/
	@Query(value = "SELECT * FROM User WHERE user=?1", nativeQuery = true)
	List<User> getUserByuserId(Integer userId);
}

package com.sami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sami.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	List<Role> findAllByOrderByIdAsc();

	@Query(value = "SELECT * FROM Role WHERE user=?1", nativeQuery = true)
	List<Role> getUserByuserId(Integer userId);
}

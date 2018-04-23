package com.sami.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sami.models.Permission;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	Page<Permission> findAllByOrderByIdAsc(Pageable page);
	
	@Query(value = "SELECT * FROM Permission WHERE userId=?1", nativeQuery = true)
	List<Permission> userAccessByuserId(Integer userId);

}

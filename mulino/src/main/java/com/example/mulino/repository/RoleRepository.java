package com.example.mulino.repository;

import com.example.mulino.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
}

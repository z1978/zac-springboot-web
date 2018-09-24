package com.zac.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zac.spring.entity.Role;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

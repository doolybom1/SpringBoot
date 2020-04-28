package com.biz.sec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.sec.domain.UserRole;
import com.biz.sec.domain.UserVO;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {


	public List<UserVO> findByUsername(String username);
}

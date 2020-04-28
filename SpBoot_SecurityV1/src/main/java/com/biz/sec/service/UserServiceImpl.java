package com.biz.sec.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserRole;
import com.biz.sec.domain.UserVO;
import com.biz.sec.repository.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService{

	private final UserDao uDao;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserVO> userVO = uDao.findByUsername(username);
		
		// Optional<UserVO> 
		Collection<GrantedAuthority> authorities = this.getUserAuthority(userVO.get().getUserRoles());
		
		return null;
	}
	
	private Collection<GrantedAuthority> getUserAuthority(Set<UserRole> userRoles){
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(UserRole uRole : userRoles) {
			authorities.add(new SimpleGrantedAuthority(uRole.getRoleName()));
		}
		return authorities;
	}
	
}

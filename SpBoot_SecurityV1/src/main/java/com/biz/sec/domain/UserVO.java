package com.biz.sec.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "tbl_users")
public class UserVO implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 64)
	private String username;
	
	// column을 설정하지 않으면  length = 255 자동설정
	private String password;
	
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	// DB에 칼럼으로 생성하지 말라
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	private String email;
	private String phone;
	private String address;
	
	// jpa에서 1:N 관계를 설정하는 부분
	// FetchType LAZY 두 테이블을 Join했을때
	// master table의 데이터를 SELECT 한 후 바로 slave table을 SELECT 하지않고 slave table의 데이터가
	// 필요한 시점에 SELECT를 수행하도록 하는 지연 옵션
	@OneToMany(mappedBy = "userVO",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;
}
	

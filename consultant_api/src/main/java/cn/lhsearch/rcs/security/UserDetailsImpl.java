package cn.lhsearch.rcs.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.lhsearch.rcs.entity.User;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private String email;

  @JsonIgnore
  private String password;

  private boolean isApproved;

  public UserDetailsImpl(String email, String password, boolean isApproved) {
    this.email = email;
    this.password = password;
    this.isApproved = isApproved;
  }

  public static UserDetailsImpl build(User user) {
    return new UserDetailsImpl(
        user.getEmail(),
        user.getPassword(),
        user.getIsApproved() 
        );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>() {};
    return authorities;
  }

  @Override
  public String getUsername()
  {
    return email;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public boolean getIsApproved() {
    return isApproved;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.isApproved;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(email, user.email);
  }
}

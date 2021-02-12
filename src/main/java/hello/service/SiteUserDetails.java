package hello.service;

import hello.model.Role;
import hello.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SiteUserDetails implements UserDetails {
    private User user;


    public SiteUserDetails (User user) {
        this.user = user;
    }

    public SiteUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()) );
        }
        return authorities;
    }

    public String getFullName() {
        return this.user.getUserName();
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    public Boolean isPasswordConfirmPasswordMatched(User user) {
        return (user.getPassword() != null && user.getConfirmPassword() != null && user.getPassword().equals(user.getConfirmPassword()));
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return user.isEnabled();
    }


}


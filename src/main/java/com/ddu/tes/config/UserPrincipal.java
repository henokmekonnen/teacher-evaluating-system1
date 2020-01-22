package com.ddu.tes.config;

import com.ddu.tes.data.modle.User;
import com.ddu.tes.security.SecurityInfoMgn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {


    private Integer id;

    private String name;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Boolean isLocked;
    private Boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;

    @Autowired
    private SecurityInfoMgn securityInfoManager;


    public UserPrincipal(Integer id, String name, String username, String email, String password, Boolean isLocked, Boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isLocked = isLocked;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user, List<GrantedAuthority> authorities) {

        return new UserPrincipal(
                user.getUserId(),
                user.getFirstName(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getLocked(),
                user.getEnabled(),
                authorities
        );



    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}

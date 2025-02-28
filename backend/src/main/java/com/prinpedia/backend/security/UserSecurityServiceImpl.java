package com.prinpedia.backend.security;

import com.prinpedia.backend.entity.Role;
import com.prinpedia.backend.entity.User;
import com.prinpedia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserSecurityServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if(username == null || username.equals("")) {
            throw new RuntimeException("Empty username");
        }

        User user = userService.findUserByName(username);
        if(user == null) {
            throw new RuntimeException("User not exist");
        }

        List<Role> roleList = user.getRoleList();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for(Role role : roleList) {
            GrantedAuthority grantedAuthority =
                    new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorityList.add(grantedAuthority);
        }

        Boolean enabled = user.getEnabled();

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), enabled,
                true, true, true,
                grantedAuthorityList);
    }
}

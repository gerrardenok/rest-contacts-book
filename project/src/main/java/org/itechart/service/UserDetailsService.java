package org.itechart.service;

import org.itechart.domain.UserRole;
import org.itechart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {

        org.itechart.domain.User user;

        List<org.itechart.domain.User> result = userRepository.findByEmail(email);

        System.out.println("result:" + result.size());

        if(result.isEmpty()) {
            throw new UsernameNotFoundException("User with email not found");
        } else {
            user = result.get(0);
        }

        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRoles());

        return buildUserForAuthentication(user, authorities);

    }

    private User buildUserForAuthentication(org.itechart.domain.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

}
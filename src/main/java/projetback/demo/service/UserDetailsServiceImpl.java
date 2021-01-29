package projetback.demo.service;


import projetback.demo.model.Student;
import projetback.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;
@AllArgsConstructor
@Service

public class UserDetailsServiceImpl implements UserDetailsService {
    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        Optional<Student> userOptional = studentRepository.findByEmail(email);
        Student user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with email : " + email));
        if(user.isEnabled() == false){

        }
        return new org.springframework.security
                .core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isEnabled(), user.isUnexpired(), true,
                true, getAuthorities("USER"));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}

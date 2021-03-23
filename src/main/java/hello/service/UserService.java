package hello.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hello.dao.RoleRepository;
import hello.dao.UserRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Role;
import hello.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    static Logger log = Logger.getLogger(UserService.class);

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }


    public boolean hasRole (String roleName) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));

    }


    public User findUserByDetails(String firstName, String lastName, String email, String id) {
        return userRepository.findByName(firstName, lastName, email, id);

    }


    public User saveNewUser(User user) {
        List<User> users = userRepository.findByEmailList(user.getEmail());

        if (users.size() >= 1) {
            log.info("email already exists");
            throw new BadRequestException("email already exists");

        } else {
            try {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setConfirmPassword(bCryptPasswordEncoder.encode(user.getConfirmPassword()));
                user.setEnabled(true);
                userRepository.save(user);
                return user;
            } catch (BadRequestException e) {
                throw new BadRequestException("email already exists");
            }
        }
    }


    public Boolean deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("id " + id + "  not found");
        }
        userRepository.deleteById(id);
        return true;
    }

    public User updateUser(String id, User userUpdated) {
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");
        }

        if (StringUtils.isBlank(userUpdated.getId())) {
            throw new BadRequestException("Please enter an id ");
        }

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("id " + id + " not found");
        }
        return userRepository.save(userUpdated);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(email);
        if(user != null) {
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    } else {
        throw new UsernameNotFoundException("username not found");
    }
}



    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }



}
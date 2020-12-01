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
    private CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    static Logger log = Logger.getLogger(UserService.class);

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }


    public User findUserByDetails(String firstName, String lastName, String email, String id) {
        return userRepository.findByName(firstName,lastName, email, id);

    }

    public User saveNewUser(User user) {
        List<User> users = userRepository.findByEmailList(user.getEmail());
        if (users.size() >= 1) {
            log.info("email already exists");
            throw new BadRequestException("email already exists");

        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            Role userRole = roleRepository.findByRole("USER"); //new user's role is set as admin
            user.setRoles(new HashSet(Arrays.asList(userRole)));
            userRepository.save(user);

            return user;
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


//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        /*Here add user data layer fetching from the MongoDB.
//          I have used userRepository*/
//        User user = userRepository.findByEmail(email);
//        if(user == null){
//            throw new UsernameNotFoundException(email);
//        }else{
//            UserDetails details = new LoggedUser(user);
//            return details;
//        }
//    }

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
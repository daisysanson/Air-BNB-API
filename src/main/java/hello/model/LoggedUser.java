//package hello.model;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Date;
//
//public class LoggedUser implements UserDetails {
//
//    User user = new User();
//    private boolean enabled;
//    private boolean accountNonExpired;
//    private boolean credentialsNonExpired;
//    private boolean accountNonLocked;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public LoggedUser() {
//    }
//
//    public LoggedUser(User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked) {
//        setUser(user);
//        this.enabled=enabled;
//        this.accountNonExpired=accountNonExpired;
//        this.credentialsNonExpired=credentialsNonExpired;
//        this.accountNonLocked=accountNonLocked;
//        this.authorities=authorities;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//
//    }
//
//    private User getUser() {
//        return this.user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }
//
//    public String getEmail(){
//        return this.user.getEmail();
//    }
//
//    public String getId(){
//        return this.user.getId();
//    }
//
//    public Date getRegistrationDate(){
//        return this.user.getRegistrationDate();
//    }
//}
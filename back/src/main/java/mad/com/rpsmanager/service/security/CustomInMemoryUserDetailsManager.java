package mad.com.rpsmanager.service.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import mad.com.rpsmanager.domain.model.users.User;

public class CustomInMemoryUserDetailsManager implements UserDetailsManager {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void createUser(UserDetails user) {
        if(!userExists(user.getUsername())){
            users.put(user.getUsername().toLowerCase(), (User) user);
        }
    }

    @Override
    public void updateUser(UserDetails user) {
        if(userExists(user.getUsername())){
            users.put(user.getUsername().toLowerCase(), (User) user);
        }
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username.toLowerCase());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return users.containsKey(username.toLowerCase());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username.toLowerCase());

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}

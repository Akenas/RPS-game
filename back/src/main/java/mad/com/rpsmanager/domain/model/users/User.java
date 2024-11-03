package mad.com.rpsmanager.domain.model.users;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User implements UserDetails{


    private Integer id;

    private String alias;

    private String email;

    private String password;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public User(String alias,String email, String password){
        this.alias = alias;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); 
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    
}

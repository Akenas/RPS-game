package mad.com.rpsmanager.domain.model.users;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class User implements UserDetails{

    private Long id;

    
    private String alias;

   
    private String email;

    @JsonIgnore
    private String password;
    
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

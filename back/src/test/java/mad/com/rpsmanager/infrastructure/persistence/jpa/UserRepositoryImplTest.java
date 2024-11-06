package mad.com.rpsmanager.infrastructure.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mad.com.rpsmanager.domain.model.users.User;
import mad.com.rpsmanager.domain.repositories.UserRepository;
import mad.com.rpsmanager.infrastructure.config.JpaPersistanceConfiguration;
import mad.com.rpsmanager.infrastructure.persistence.jpa.entities.JpaUserEntity;
import mad.com.rpsmanager.infrastructure.persistence.jpa.repositories.JpaUserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = true)
@Import(UserRepositoryImpl.class)
@ContextConfiguration(classes = JpaPersistanceConfiguration.class)
public class UserRepositoryImplTest {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        JpaUserEntity userEntity = new JpaUserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setAlias("testAlias");
        userEntity.setPassword("password");

        jpaUserRepository.save(userEntity);
    }

    @Test
    public void testFindByEmail() {
        Optional<User> user = userRepository.findByEmail("test@example.com");
        assertTrue(user.isPresent());
        assertEquals("test@example.com", user.get().getEmail());
    }

    @Test
    public void testFindByAlias() {
        Optional<User> user = userRepository.findByAlias("testAlias");
        assertTrue(user.isPresent());
        assertEquals("testAlias", user.get().getAlias());
    }

    @Test
    public void testSave() {
        User newUser = new User("newAlias", "new@example.com", "newPassword");
        User savedUser = userRepository.save(newUser);

        assertEquals("new@example.com", savedUser.getEmail());
        assertEquals("newAlias", savedUser.getAlias());
    }

    @Test
public void testFindById() {
    // Retrieve the user by email to get the generated ID
    Optional<User> user = userRepository.findByEmail("test@example.com");
    assertTrue(user.isPresent());

    Long generatedId = user.get().getId();
    Optional<User> userById = userRepository.findById(generatedId);

    // Test the retrieval by ID
    assertTrue(userById.isPresent());
    assertEquals(generatedId, userById.get().getId());
}
}

package com.junitdemo.tdd;

import com.junitdemo.tdd.exception.EmailNotificationServiceException;
import com.junitdemo.tdd.model.User;
import com.junitdemo.tdd.repository.UserRepository;
import com.junitdemo.tdd.service.EmailVerificationService;
import com.junitdemo.tdd.exception.UserServiceException;
import com.junitdemo.tdd.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Mock anotasyonları için tanımladık.
public class UserServiceTest {

    // Inject edilecek sınıf
    @InjectMocks
    UserServiceImpl userService;

    // Inject edilen sınıf
    @Mock
    UserRepository userRepository;

    @Mock
    EmailVerificationService emailVerificationService;

    String username;
    String password ;
    String email;
    String passwordConfirm;

    @BeforeEach
    void init(){
        username = "test";
        password = "test";
        email = "test@test.com";
        passwordConfirm = "test";
    }

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailProvided_returnUserObject() {
        // Arrange
        // Metodu sahte bir şekilde true döndürecek halde çalıştırdık.
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(true);

        // Act
        User user = userService.createUser(username, password, email, passwordConfirm);

        // Assert
        assertNotNull(user, "User is null");
        assertEquals(username, user.getUsername(), "Username is not correct");
        assertEquals(email, user.getEmail(), "Email is not correct");

        // Bu metodun mockito yaparken çağırılıp çağırılmadığı kontrolü
        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.any(User.class));
    }

    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllagelArgumentException() {
        // Arrange
        username = "";

        // Act & Assert
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(username, password, email, passwordConfirm);
        },"Username is empty" );

        // Assert
        assertEquals(illegalArgumentException.getMessage(), "Username is empty",
                "Exception message is not correct");
    }

    @Test
    void testCreateUser_whenSaveMethodTrowsException_thenThrowsUserServiceException() {
        // Arrange
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(UserServiceException.class, ()->{
            userService.createUser(username, password, email, passwordConfirm);
        }, "Should have thrown UserServiceException instead");
    }

    @DisplayName("EmailNotificationException is handled")
    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException() {
        // Arrange
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);

        // Klasik bir when işlemi ancak metot bir şey döndürmediğinden yazım tarzı farklı
        doThrow(EmailNotificationServiceException.class)
                .when(emailVerificationService)
                .scheduleEmailConfirmation(Mockito.any(User.class));

        // Act & Assert
        assertThrows(UserServiceException.class, ()->{
            userService.createUser(username, password, email, passwordConfirm);
        }, "Should have thrown UserServiceException instead");

        // Assert
        Mockito.verify(emailVerificationService, Mockito.times(1)).
                scheduleEmailConfirmation(Mockito.any(User.class));
    }

    @Disabled
    @Test
    void testCreateUser_whenUserCreated_schedulesEmailConfirmation() {
        // Arrange
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);

        // Metodun gerçek hali çalışır.
        Mockito.doCallRealMethod().when(emailVerificationService)
                .scheduleEmailConfirmation(Mockito.any(User.class));

        // Act
        userService.createUser(username, password, email, passwordConfirm);

        // Assert
        Mockito.verify(emailVerificationService, Mockito.times(1))
                .scheduleEmailConfirmation(Mockito.any(User.class));
    }
}

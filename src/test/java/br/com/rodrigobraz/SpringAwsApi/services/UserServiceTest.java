package br.com.rodrigobraz.SpringAwsApi.services;

import br.com.rodrigobraz.SpringAwsApi.domain.User;
import br.com.rodrigobraz.SpringAwsApi.domain.dto.UserDTO;
import br.com.rodrigobraz.SpringAwsApi.domain.enums.Role;
import br.com.rodrigobraz.SpringAwsApi.repositories.UserRepository;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.DataIntegrityViolationException;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "Rodrigo";
    public static final String EMAIL = "rodrigo@email.com";
    public static final String PASSWORD = "123";
    public static final Role ADMIN = Role.ADMIN;
    public static final String USER_NOT_FOUND = "User not found";
    public static final int INDEX = 0;
    public static final String EMAIL_ALREADY_REGISTERED = "Email already registered";

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> userOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdMustReturnSucess() {
        when(repository.findById(anyLong())).thenReturn(userOptional);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(ADMIN, response.getRole());
    }

    @Test
    void whenFindByIdMustReturnObjectNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USER_NOT_FOUND, ex.getMessage());
        }
    }

    @Test
    void whenFindAllMustReturnSuccess() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = repository.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
        assertEquals(ADMIN, response.get(INDEX).getRole());
    }

    @Test
    void whenCreateMustReturnSuccess() {
        when(service.createUser(userDTO)).thenReturn(user);

        User response = service.createUser(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(ADMIN, response.getRole());
    }

    @Test
    void whenCreateOrUpdateMustReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            service.createUser(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_REGISTERED, ex.getMessage());
        }
    }

    @Test
    void whenUpdateUserMustReturnSuccess() {
        when(service.updateUser(userDTO)).thenReturn(user);

        User response = service.updateUser(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(ADMIN, response.getRole());
    }

    @Test
    void whenDeleteByIdMustReturnNoContent() {
        when(repository.findById(anyLong())).thenReturn(userOptional);
        doNothing().when(repository).deleteById(anyLong());
        service.deleteUserById(ID);

        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void whenDeleteByIdMustReturnObjectNotFoundException() {
        when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));
        try {
            service.deleteUserById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(USER_NOT_FOUND, ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD, ADMIN, null, null);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD, ADMIN, null, null);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD, ADMIN, null, null));
    }
}
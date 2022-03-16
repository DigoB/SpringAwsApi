package br.com.rodrigobraz.SpringAwsApi.services;

import br.com.rodrigobraz.SpringAwsApi.domain.User;
import br.com.rodrigobraz.SpringAwsApi.domain.dto.UserDTO;
import br.com.rodrigobraz.SpringAwsApi.repositories.UserRepository;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.DataIntegrityViolationException;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User createUser(UserDTO dto) {
        findById(dto.getId());
        return repository.save(mapper.map(dto, User.class));
    }

    public User updateUser(UserDTO dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, User.class));
    }

    public void deleteUserById(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    private void findByEmail(UserDTO dto) {
        Optional<User> possibleUser = repository.findByEmail(dto.getEmail());
        if (possibleUser.isPresent() && !possibleUser.get().getId().equals(dto.getId())) {
            throw new DataIntegrityViolationException("Email already registered");
        }
    }
}

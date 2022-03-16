package br.com.rodrigobraz.SpringAwsApi.services;

import br.com.rodrigobraz.SpringAwsApi.domain.Request;
import br.com.rodrigobraz.SpringAwsApi.domain.dto.RequestDTO;
import br.com.rodrigobraz.SpringAwsApi.repositories.RequestRepository;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.DataIntegrityViolationException;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private ModelMapper mapper;

    public Request findById(Long id) {
        Optional<Request> possibleRequest = repository.findById(id);
        return possibleRequest.orElseThrow(
                () -> new ObjectNotFoundException("Request not found"));
    }

    public List<Request> findAll() {
        return repository.findAll();
    }

    public Request createRequest(RequestDTO dto) {
        Optional<Request> request = repository.findById(dto.getId());
        if (request.isPresent()) {
            throw new DataIntegrityViolationException("Request already exists");
        }
        return repository.save(mapper.map(dto, Request.class));
    }

    public Request updateRequest(RequestDTO dto) {
        Optional<Request> request = repository.findById(dto.getId());
        if (request.isPresent()) {
            throw new DataIntegrityViolationException("Request already exists");
        }
        return repository.save(mapper.map(dto, Request.class));
    }

    public void deleteRequest(Long id) {
        findById(id);
        repository.deleteById(id);
    }

}

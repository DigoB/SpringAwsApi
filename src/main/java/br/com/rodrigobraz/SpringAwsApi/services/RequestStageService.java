package br.com.rodrigobraz.SpringAwsApi.services;

import br.com.rodrigobraz.SpringAwsApi.domain.RequestStage;
import br.com.rodrigobraz.SpringAwsApi.domain.dto.RequestStageDTO;
import br.com.rodrigobraz.SpringAwsApi.repositories.RequestStageRepository;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.DataIntegrityViolationException;
import br.com.rodrigobraz.SpringAwsApi.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestStageService {

    @Autowired
    private RequestStageRepository repository;

    @Autowired
    private ModelMapper mapper;

    public RequestStage findById(Long id) {
        Optional<RequestStage> stage = repository.findById(id);
        return stage.orElseThrow(() -> new ObjectNotFoundException("Stage not found"));
    }

    public List<RequestStage> findAll() {
        return repository.findAll();
    }

    public RequestStage createRequestStage(RequestStageDTO dto) {
        Optional<RequestStage> stage = repository.findById(dto.getId());
        if (stage.isPresent()) {
            throw new DataIntegrityViolationException("Request Stage already exists");
        }
        return repository.save(mapper.map(dto, RequestStage.class));
    }

    public RequestStage updateRequestStage(RequestStageDTO dto) {
        findById(dto.getId());
        return repository.save(mapper.map(dto, RequestStage.class));
    }

    public void deleteRequestStage(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}

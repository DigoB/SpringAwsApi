package br.com.rodrigobraz.SpringAwsApi.repository;

import br.com.rodrigobraz.SpringAwsApi.domain.RequestStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

    public List<RequestStage> findAllByRequestId(Long id);
}

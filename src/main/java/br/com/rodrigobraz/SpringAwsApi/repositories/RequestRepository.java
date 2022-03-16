package br.com.rodrigobraz.SpringAwsApi.repositories;

import br.com.rodrigobraz.SpringAwsApi.domain.Request;
import br.com.rodrigobraz.SpringAwsApi.domain.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    public List<Request> findAllByUserId(Long id);

    @Query("UPDATE Request SET status = ?2 WHERE id = ?1")
    public Request updateStatus(Long id, RequestStatus status);
}

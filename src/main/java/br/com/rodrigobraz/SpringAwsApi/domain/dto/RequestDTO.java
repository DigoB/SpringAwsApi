package br.com.rodrigobraz.SpringAwsApi.domain.dto;

import br.com.rodrigobraz.SpringAwsApi.domain.RequestStage;
import br.com.rodrigobraz.SpringAwsApi.domain.User;
import br.com.rodrigobraz.SpringAwsApi.domain.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private Long id;
    private String subject;
    private String description;
    private Date timestamp;
    private RequestStatus status;
    private User user;

    @JsonIgnore
    private List<RequestStage> statuses = new ArrayList<>();
}

package br.com.rodrigobraz.SpringAwsApi.domain.dto;

import br.com.rodrigobraz.SpringAwsApi.domain.Request;
import br.com.rodrigobraz.SpringAwsApi.domain.User;
import br.com.rodrigobraz.SpringAwsApi.domain.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStageDTO {

    private Long id;
    private String description;
    private Date date;
    private RequestStatus status;
    private Request request;
    private User user;
}

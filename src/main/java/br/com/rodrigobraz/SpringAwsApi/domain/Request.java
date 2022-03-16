package br.com.rodrigobraz.SpringAwsApi.domain;

import br.com.rodrigobraz.SpringAwsApi.domain.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private Long id;
    private String subject;
    private String description;
    private Date timestamp;
    private RequestStatus status;
    private User user;
    private List<RequestStage> statuses = new ArrayList<>();
}

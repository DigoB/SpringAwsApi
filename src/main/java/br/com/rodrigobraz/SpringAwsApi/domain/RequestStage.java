package br.com.rodrigobraz.SpringAwsApi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStage {

    private Long id;
    private String description;
    private Date date;
    private RequestStage stage;
    private Request request;
    private User user;
}

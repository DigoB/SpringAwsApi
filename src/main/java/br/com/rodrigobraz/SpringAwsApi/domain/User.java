package br.com.rodrigobraz.SpringAwsApi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> stages = new ArrayList<>();

}

package br.com.rodrigobraz.SpringAwsApi.domain.dto;

import br.com.rodrigobraz.SpringAwsApi.domain.Request;
import br.com.rodrigobraz.SpringAwsApi.domain.RequestStage;
import br.com.rodrigobraz.SpringAwsApi.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Role role;

    @JsonIgnore
    private List<Request> requests = new ArrayList<>();

    @JsonIgnore
    private List<RequestStage> stages = new ArrayList<>();
}

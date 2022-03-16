package br.com.rodrigobraz.SpringAwsApi.domain;

import br.com.rodrigobraz.SpringAwsApi.domain.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String subject;

    @Column(length = 75, nullable = false, columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date timestamp;

    @Column(length = 12, nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "request")
    private List<RequestStage> statuses = new ArrayList<>();
}

package com.interview.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Repository {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="repository_seq_gen")
    @SequenceGenerator(name="repository_seq_gen", sequenceName="repository_seq")
    private Long repositoryId;

    @ManyToOne
    private Owner repositoryOwner;

    private String repositoryName;
}

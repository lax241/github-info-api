package com.interview.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Owner {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="owner_seq_gen")
    @SequenceGenerator(name="owner_seq_gen", sequenceName="owner_seq")
    private Long ownerId;

    private String ownerName;
}

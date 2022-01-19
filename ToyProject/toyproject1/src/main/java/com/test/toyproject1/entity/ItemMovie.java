package com.test.toyproject1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("MOVIE")
public class ItemMovie extends Item {
    private String director;
    private String actor;
}

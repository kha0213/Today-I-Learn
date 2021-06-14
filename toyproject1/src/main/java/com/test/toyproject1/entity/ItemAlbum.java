package com.test.toyproject1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("ALBUM")
public class ItemAlbum extends Item {
    private String artist;
    private String etc;
}

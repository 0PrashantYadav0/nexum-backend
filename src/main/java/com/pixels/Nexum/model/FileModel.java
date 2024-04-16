package com.pixels.Nexum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Files")
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int fileId;

    private String fileName;

    private String fileType;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] fileData;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

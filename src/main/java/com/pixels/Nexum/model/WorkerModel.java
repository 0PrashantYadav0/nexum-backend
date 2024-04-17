package com.pixels.Nexum.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Workers")
public class WorkerModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;

    @Column(unique= true, nullable=false,updatable = false)
    private String userName;

    @Column(nullable=false, updatable=false)
    private String firstName;

    private String middleName;

    private String lastName;

    private String password;

    private String email;

    @Column(unique= true, nullable= false)
    private String phoneNo;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(unique = true,nullable = false)
    private String aadharNo;

    @Column(nullable = false)
    private String skills;

    private String rating;

    private String experience;

    private String photoUrl;

    @CreationTimestamp
    @Column(nullable= false, updatable= false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @UpdateTimestamp
    @Column(nullable= false, updatable= true)
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

    private int modifiedBy;

    private int createdBy;
    private boolean status = true;

}

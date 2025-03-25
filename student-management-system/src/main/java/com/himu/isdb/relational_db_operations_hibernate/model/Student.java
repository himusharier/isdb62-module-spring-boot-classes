package com.himu.isdb.relational_db_operations_hibernate.model;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.himu.isdb.relational_db_operations_hibernate.config.InstantDeserializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sms_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(unique = true, length = 100)
    private String email;

    @OneToOne
    @JoinColumn(name = "clazz", referencedColumnName = "id", nullable = false)
    private Classroom clazz;

    @Column(nullable = false, unique = true)
    private Integer roll;

    @OneToMany(mappedBy = "student")
    private List<Book> book;

    @Column(nullable = false, length = 17)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(nullable = false, length = 10)
    private String gender;

    @JsonDeserialize(using = InstantDeserializer.class)
    @Column(updatable = false)
    private Instant dob;
}

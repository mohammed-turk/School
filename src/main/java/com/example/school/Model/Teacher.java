package com.example.school.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 2, message = "Name must be at least 2 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;


    @NotNull(message = "Age cannot be null")
    @Min(value = 22, message = "Age must be at least 22")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;
}

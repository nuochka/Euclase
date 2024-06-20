package com.euclase.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank
    @Field(name = "username")
    private String username;

    @Email
    @NotBlank
    @Field(name = "email")
    @Indexed(unique = true)
    private String email;

    @NotBlank
    @Field(name = "password")
    private String password;

}

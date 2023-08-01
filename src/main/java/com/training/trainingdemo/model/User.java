package com.training.trainingdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNo;
}

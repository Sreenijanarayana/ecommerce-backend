package com.sree.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    //to transfer the data  between client and the server
    private Long id;
    private String firstName;
    private String  lastName;
    private String email;

}

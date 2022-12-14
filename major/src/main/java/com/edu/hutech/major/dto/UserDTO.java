package com.edu.hutech.major.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;

    private String email;

    private String password;

    private String phone;

    private List<Integer> roleIds;

    private String firstName;

    private String lastName;

}

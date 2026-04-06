package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "userdb")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String Id;
    private String userName;
    private String userEmail;
    private int userAge;

 /*   public UserEntity(String id, String userName, String userEmail, int userAge) {
        Id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAge = userAge;
    }

    public UserEntity() {
    }*/
}

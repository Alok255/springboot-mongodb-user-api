package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "userdb")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
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

package com.tailor.track.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private Long mobileNumber;
    private String gender;
}

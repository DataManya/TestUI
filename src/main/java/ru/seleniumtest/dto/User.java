package ru.seleniumtest.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Getter
@Setter
@NoArgsConstructor
public class User {
    private String login;
    private String password;
    private String name;

    public User(String l, String p, String n) {
        this.login = l;
        this.password = p;
        this.name = n;
    }
}
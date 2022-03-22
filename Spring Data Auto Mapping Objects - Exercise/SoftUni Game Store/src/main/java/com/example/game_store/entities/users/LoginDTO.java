package com.example.game_store.entities.users;

import com.example.game_store.exceptions.WrongCredentialsException;
import com.example.game_store.utils.CredentialValidation;

public class LoginDTO {

    private String email;
    private String password;

    public LoginDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        validate();
    }

    private  void validate() {
        if (!CredentialValidation.isValid(this.email, this.password)) {
            throw new WrongCredentialsException("Invalid email / pass");
        }
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

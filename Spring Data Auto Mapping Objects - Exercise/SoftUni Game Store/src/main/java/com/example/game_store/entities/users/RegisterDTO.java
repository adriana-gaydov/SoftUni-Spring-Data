package com.example.game_store.entities.users;

import com.example.game_store.utils.CredentialValidation;
import com.example.game_store.exceptions.WrongCredentialsException;

public class RegisterDTO {

    private String email;
    private String password;

    private String confirmPassword;
    private String fullName;

    public RegisterDTO(String[] commandPart) {
        this.email = commandPart[1];
        this.password = commandPart[2];
        this.confirmPassword = commandPart[3];
        this.fullName = commandPart[4];

        validate();
    }

    private void validate() {
        if (!this.password.equals(this.confirmPassword)) {
            throw new WrongCredentialsException("Passwords don't match!");
        }

        if (!CredentialValidation.isValidEmail(this.email)) {
            throw new WrongCredentialsException("Invalid email!");
        }

        if (!CredentialValidation.isValidPassword(this.password)) {
            throw new WrongCredentialsException("Invalid password!");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}

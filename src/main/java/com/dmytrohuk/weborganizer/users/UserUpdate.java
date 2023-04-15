package com.dmytrohuk.weborganizer.users;

public class UserUpdate {
    private String username;

    private String password;

    private String firstName;

    private String surname;

    private String email;

    private String address;
    public UserUpdate(String username, String password, String firstName, String surname,
                      String email, String address) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
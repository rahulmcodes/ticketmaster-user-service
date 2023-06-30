package com.ticketmaster.userservice.records;

import javax.persistence.*;

public record User(
        String userName,
        String password,
        String email,
        String firstName,
        String lastName) {


}

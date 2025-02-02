package com.hajokns.domain.entity;

import com.hajokns.domain.vo.Address;
import com.hajokns.domain.vo.Email;
import com.hajokns.domain.vo.FullName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private final FullName fullName;
    private final Email email;
    private Address address;

    public Customer(FullName fullName, Email email, Address address) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.address = address;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Email getEmail() {
        return email;
    }
}

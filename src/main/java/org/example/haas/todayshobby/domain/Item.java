package org.example.haas.todayshobby.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;
}

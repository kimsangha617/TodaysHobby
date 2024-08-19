package org.example.haas.todayshobby.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;
}

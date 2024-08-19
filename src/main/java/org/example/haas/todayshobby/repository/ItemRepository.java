package org.example.haas.todayshobby.repository;

import org.example.haas.todayshobby.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

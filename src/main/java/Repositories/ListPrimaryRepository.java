package Repositories;

import models.ListPrimary;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListPrimaryRepository extends JpaRepository<ListPrimary, UUID> {
    boolean existsByName(String name);
}

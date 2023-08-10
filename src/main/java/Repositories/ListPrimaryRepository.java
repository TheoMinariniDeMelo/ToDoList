package Repositories;

import models.ListPrimary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListPrimaryRepository extends JpaRepository<ListPrimary, UUID> {
}

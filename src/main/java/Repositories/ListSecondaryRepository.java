package Repositories;

import models.ListSecondary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListSecondaryRepository extends JpaRepository<ListSecondary, UUID> {

}

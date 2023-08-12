package repositories;

import models.ListPrimary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListPrimaryRepository extends JpaRepository<ListPrimary, UUID> {
}

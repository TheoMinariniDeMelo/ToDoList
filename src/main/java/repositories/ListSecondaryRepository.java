package repositories;

import models.ListSecondary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ListSecondaryRepository extends JpaRepository<ListSecondary, UUID> {

}

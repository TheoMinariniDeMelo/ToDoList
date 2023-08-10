package Repositorys;

import models.ListSecondary;
import models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListSecondaryRepository extends JpaRepository<ListSecondary, UUID> {

}

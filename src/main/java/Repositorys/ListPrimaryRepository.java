package Repositorys;

import models.ListPrimary;
import models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListPrimaryRepository extends JpaRepository<ListPrimary, UUID> {
}

package Repositories;

import models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
}

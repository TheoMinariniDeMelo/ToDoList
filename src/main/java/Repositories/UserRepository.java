package Repositories;

import models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    public String[] get(@RequestBody UserModel user);
    public UserModel create(@RequestBody UserModel user);
    public UserModel update(@RequestBody UserModel user);
    public UserModel patch(@RequestBody UserModel user);
    public void delete(@RequestBody String id);

}

package application.services.controller.repositoriesByAspects;

import application.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Delete {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public void deleteUser(UserModel userModel) {
        jpaRepositoriesService.getUserRepository().delete(userModel);
    }
}

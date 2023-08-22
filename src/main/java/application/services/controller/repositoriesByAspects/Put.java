package application.services.controller.repositoriesByAspects;

import application.models.UserModel;
import application.services.controller.repositoriesByAspects.JpaRepositoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Put {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public UserModel putUser(UserModel userModel) {
        return jpaRepositoriesService.getUserRepository().save(userModel);
    }

}

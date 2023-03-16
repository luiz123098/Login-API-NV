package ModelDAO.ModelInterface;

import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModel extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);

}
package ViewMB;

import ControllerBO.ControllerInterface.UserController;
import Entity.DTO.UserDTO;
import Entity.User;
import Exceptions.BusinessRules;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/api/users")
public class UserView {
    private UserController userController;
    public UserView(UserController userController) {
        this.userController = userController;
    }
    @PostMapping
    public ResponseEntity save(@RequestBody UserDTO dto) {

        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setCpf(dto.getCpf());
        try {
            User userSave = userController.save(user);
            return new ResponseEntity(userSave, HttpStatus.CREATED);
        } catch (BusinessRules e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

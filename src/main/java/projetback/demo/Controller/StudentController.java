package projetback.demo.Controller;

import projetback.demo.dto.CheckAdmin;
import projetback.demo.dto.UserDto;



import projetback.demo.repository.VerificationTokenRepository;



import projetback.demo.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
@Slf4j
public class StudentController {

    private final AuthService authService;
    private final VerificationTokenRepository verificationTokenRepository;
    @PostMapping("/isAdmin")
    public ResponseEntity<String> verifyAdmin(@RequestBody CheckAdmin checkAdmin){
        boolean is_admin=authService.is_admin(checkAdmin.getEmail());
        if (is_admin){
            return new ResponseEntity<>("User is an admin", HttpStatus.OK);
        }
        return new ResponseEntity<>("User is not admin",HttpStatus.NOT_FOUND);
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(authService.getAllusers());
    }

    @GetMapping("remove/{id}")
    public ResponseEntity removeUsers(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(authService.UserChange(id));
    }
}
package projetback.demo.Controller;

import projetback.demo.dto.AuthenticationResponse;
import projetback.demo.dto.CheckAdmin;
import projetback.demo.dto.LoginRequest;
import projetback.demo.dto.RegisterRequest;
import projetback.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerrequest){
        authService.signup(registerrequest);
        return new ResponseEntity<>("User registration Successful", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable  String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated Successfully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);

    }




}


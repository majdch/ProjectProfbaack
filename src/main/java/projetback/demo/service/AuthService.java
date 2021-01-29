package projetback.demo.service;


import projetback.demo.dto.AuthenticationResponse;
import projetback.demo.dto.LoginRequest;
import projetback.demo.dto.RegisterRequest;
import projetback.demo.dto.UserDto;
import projetback.demo.exceptions.SpringRedditException;
import projetback.demo.model.NotificationEmail;
import projetback.demo.model.Student;
import projetback.demo.model.Subjects;
import projetback.demo.model.VerificationToken;
import projetback.demo.repository.StudentRepository;
import projetback.demo.repository.SubjectsRepository;
import projetback.demo.repository.VerificationTokenRepository;
import projetback.demo.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final StudentRepository studentRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final SubjectsRepository subjectsRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        Student student = new Student();
        student.setStudentName(registerRequest.getStudentName());

        student.setEmail(registerRequest.getEmail());

        Subjects subject = subjectsRepository.findById(registerRequest.getSubjects()) .orElseThrow(() -> new SpringRedditException("Subject not found"));;
        student.setSubjects(subject);
        String password=this.getAlphaNumericString(8);
        System.out.println(password);
        student.setPassword(passwordEncoder.encode(password));
        student.setCreated(Instant.now());
        student.setEnabled(false);
        student.set_admin(false);
        student.setUnexpired(true);
        studentRepository.save(student);
        String token = generateVerificationToken(student);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                student.getEmail(),
                "Thank you for signing up to Prof, "+
                        "Your username is : "+registerRequest.getEmail()+
                        " and your password is : "+password+
                        " please click on the below url to activate your account : " +
                        "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(Student student) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setStudent(student);
        verificationTokenRepository.save(verificationToken);
        return token;

    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(()->new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }
    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String email=  verificationToken.getStudent().getEmail();
        Student student = studentRepository.findByEmail(email).orElseThrow(()->new SpringRedditException("User not found - " + email));
        student.setEnabled(true);
        studentRepository.save(student);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        Student student=studentRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new SpringRedditException("Subject not found"));
        return new AuthenticationResponse(token,loginRequest.getEmail(),student.is_admin(),student.getSubjects().getSubjectId());
    }

    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }


    public boolean is_admin(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(()->new SpringRedditException("No user has been found"));
        return student.is_admin();
    }
    public boolean UserChange(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(()->new SpringRedditException("User not found"));
        student.setUnexpired(!student.isUnexpired());




        return studentRepository.save(student).isUnexpired();

    }
    @Transactional(readOnly = true)
    public List<UserDto> getAllusers() {
        return studentRepository.findAll().stream().map(this::mapUserDto).collect(Collectors.toList());
    }

    private UserDto mapUserDto(Student student) {
        Subjects subjects =subjectsRepository.findById(student.getSubjects().getSubjectId()).orElseThrow(()->new SpringRedditException("Not found"));

        return UserDto.builder().studentId(student.getStudentId()).is_admin(student.is_admin()).unexpired(student.isUnexpired()).subjects_id(subjects.getSubjectId()).subjects_name(subjects.getSubjectName()).studentName(student.getStudentName()).email(student.getEmail()).enabled(student.isEnabled()).created(student.getCreated()).build();
    }



}



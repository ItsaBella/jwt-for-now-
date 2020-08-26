package com.lubanzi.qlitygigs.Controllers;

import com.lubanzi.qlitygigs.Config.JwtUtil;
import com.lubanzi.qlitygigs.Model.JwtRequest;
import com.lubanzi.qlitygigs.Model.JwtResponse;
import com.lubanzi.qlitygigs.Model.User;
import com.lubanzi.qlitygigs.Model.UserRegistrationRequest;
import com.lubanzi.qlitygigs.Repos.UsersRepo;
import com.lubanzi.qlitygigs.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller class for handling user objects. Contains implementation for Repo
 * interface.
 */
@RestController
@RequestMapping(value = "users/")
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    PasswordEncoder encoder;

    /**
     *
     * @param User object
     * @return String representation of created User Object.
     */
    @PostMapping("/createUser")
    public String createUser(@RequestBody User u) {
        User nU = userService.save(u);
        return nU.toString();
    }

    /**
     * GetUserByEmail Function
     * 
     * @param email address string
     * @return User object related to the email address
     */
    @GetMapping("/getUserByEmail")
    public User getU(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    /**
     * Get All Users Function
     * 
     * @return An Array List of all User objects
     */
    @RequestMapping("/getAllUsers")
    public List<User> getAll() {
        return userService.getAll();
    }

    // /**
    //  * @param User object to be updated
    //  * @return Updated User object
    //  */
    @RequestMapping("/updateUser")
    public User update(@RequestBody User u) {
        return userService.update(u);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getuEmail(), authenticationRequest.getuPassword());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getuEmail());

        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody UserRegistrationRequest request) throws Exception {
//        return ResponseEntity.ok(userService.save(user));
        User user  =  new User();

        user.setuName(request.getuName());
        user.setuSurname(request.getuSurname());
        user.setuPassword(encoder.encode(request.getuPassword()));
        user.setId(request.getId());
        user.setuEmail(request.getuEmail());
//        user.setuCompany(request.getuCompany());
        user.setuContactNumber(request.getuContactNumber());
//        user.setuDOB(request.getuDOB());
        user.setuType(request.getuType());

        return ResponseEntity.ok(userService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

    // /**
    //  * @param User object to be deleted
    //  * @return String confirming user has been deleted
    //  */
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestBody User u)
    {
        userService.deleteUser(u);
        return "User deleted!";
    }

    @GetMapping(value = "/users")
    ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }

}

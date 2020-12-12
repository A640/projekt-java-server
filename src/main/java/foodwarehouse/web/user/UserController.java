package foodwarehouse.web.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import foodwarehouse.core.user.UserService;
import foodwarehouse.core.user.Permission;
import foodwarehouse.web.common.SuccessResponse;
import foodwarehouse.web.error.RestException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping
    public SuccessResponse<List<UserResponse>> getUsers() {
        final var users = userService
                .getUsers()
                .stream()
                .map(user -> new UserResponse(user.permission().value(), user.userId(), user.username()))
                .collect(Collectors.toList());
        return new SuccessResponse<>(users);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/employee")
    public SuccessResponse<UserResponse> createEmploee(@RequestBody CreateUserRequest request) {
        System.out.println(request);
        return userService
                .createUser(request.username(), request.password(), request.email(), Permission.EMPLOYEE)
                .map(user -> new SuccessResponse<>(new UserResponse(user.permission().value(), user.userId(), user.username())))
                .orElseThrow(() -> new RestException("Unable to create a new user."));
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/user")
    public SuccessResponse<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        Permission permission = Permission.from(request.permission()).get();
        System.out.println(permission);
        if(permission == null) {
            new RestException("Unable to create a new user.");
        }
        System.out.println(request);
        return userService
                .createUser(request.username(), request.password(), request.email(), Permission.from(request.permission()).get())
                .map(user -> new SuccessResponse<>(new UserResponse(user.permission().value(), user.userId(), user.username())))
                .orElseThrow(() -> new RestException("Unable to create a new user."));
                //do sprawdzenia czy niepoprawny permission wyrzuca blad
    }
}
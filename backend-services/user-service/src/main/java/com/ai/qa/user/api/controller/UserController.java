package com.ai.qa.user.api.controller;

import com.ai.qa.user.api.dto.*;
import com.ai.qa.user.api.exception.ApiResponse;
import com.ai.qa.user.application.dto.UserLogin;
import com.ai.qa.user.application.dto.UserRegister;
import com.ai.qa.user.application.dto.UserUpdateNick;
import com.ai.qa.user.application.service.UserService;
import com.ai.qa.user.infrastructure.persisitence.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/***
 * 为什么user-service必须也要自己做安全限制？
 * 1。零信任网络 (Zero Trust Network)：在微服务架构中，你必须假设内部网络是不安全的。不能因为一个请求来自API Gateway就完全信任它。万一有其他内部服务被攻破，它可能会伪造请求直接调用user-service，绕过Gateway。如果user-service没有自己的安全防线，它就会被完全暴露。
 * 2。职责分离 (Separation of Concerns)：Gateway的核心职责是路由、限流、熔断和边缘认证。而user-service的核心职责是处理用户相关的业务逻辑，业务逻辑与谁能执行它是密不可分的。授权逻辑是业务逻辑的一部分，必须放在离业务最近的地方。
 * 3。细粒度授权 (Fine-Grained Authorization)：Gateway通常只做粗粒度的授权，比如“USER角色的用户可以访问/api/users/**这个路径”。但它无法知道更精细的业务规则，例如：
 * GET /api/users/{userId}: 用户123是否有权查看用户456的资料？
 * PUT /api/users/{userId}: 只有用户自己或者管理员才能修改用户信息。
 * 这些判断必须由user-service结合自身的业务逻辑和数据来完成。
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${datasource.username}")
    private String username;

    private final UserService userService;
    private UserMapper mapper;

    @GetMapping("/config")
    public String login() {
        System.out.println("测试config");
        return "测试JWT："+username;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest) {

        String token = "";
        try {
            UserLogin userLogin = mapper.toUserLogin(authRequest);
            token = userService.login(userLogin);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
        return ResponseEntity.ok(ApiResponse.success(new AuthResponse(token)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody RegisterRequest registerRequest) {

        try {
            UserRegister userRegister = mapper.toUserRegister(registerRequest);
            userService.register(userRegister);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
        return ResponseEntity.ok(ApiResponse.success(new RegisterResponse(true)));
    }

    @PostMapping("/nick")
    public ResponseEntity<ApiResponse<UpdateNickResponse>> updateNick(@RequestBody UpdateNickRequest updateNickRequest) {

        try {
            UserUpdateNick userUpdateNick = mapper.toUserUpdateNick(updateNickRequest);
            userService.updateNick(userUpdateNick);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
        return ResponseEntity.ok(ApiResponse.success(new UpdateNickResponse(true)));
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}

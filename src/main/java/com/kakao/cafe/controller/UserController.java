package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.UserDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.controller.dto.SignUpRequestDto;
import com.kakao.cafe.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String signUp(SignUpRequestDto form) {
        User user = new User(form.getEmail(), form.getNickname(), form.getPassword());
        userService.signUp(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String list(Model model) {
        List<UserDto> users = userService.findUsers().stream()
            .map(UserDto::from)
            .collect(Collectors.toList());
        model.addAttribute("users", users);
        model.addAttribute("totalUserCount", users.size());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String profile(@PathVariable Long userId, Model model) {
        User user = userService.findUser(userId);
        UserDto userDto = UserDto.from(user);
        model.addAttribute("user", userDto);
        return "user/profile";
    }
}

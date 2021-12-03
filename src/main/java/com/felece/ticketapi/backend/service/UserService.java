package com.felece.ticketapi.backend.service;

import com.felece.ticketapi.backend.entity.User;
import com.felece.ticketapi.backend.entity.UserRole;
import com.felece.ticketapi.backend.entity.vm.UpdateUserVM;
import com.felece.ticketapi.backend.entity.vm.UserListVM;
import com.felece.ticketapi.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(UserRole.USER);
        }
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserListVM> getAllUsers() {
        List<User> inDB = userRepository.findAll();
        final Stream<UserListVM> listVMStream = inDB.stream().map(UserListVM::new);
        return listVMStream.collect(java.util.stream.Collectors.toList());
    }

    public User getUser(final Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public String updateUser(final UpdateUserVM updateUserVM) {
        User inDB = userRepository.findByEmail(updateUserVM.getEmail());
        if (inDB == null) {
            return "User not found";
        }
        inDB.setFirstName(updateUserVM.getFirstName());
        inDB.setLastName(updateUserVM.getLastName());
        inDB.setPhoneNumber(updateUserVM.getPhoneNumber());
        inDB.setRole(updateUserVM.getRole());
        inDB.setRole(updateUserVM.getRole());
        userRepository.save(inDB);
        return "User updated";
    }

    public String deleteUser(final String email) {
        User inDB = userRepository.findByEmail(email);
        if (inDB == null) {
            return "User not found";
        }
        userRepository.delete(inDB);
        return "User deleted";
    }
}
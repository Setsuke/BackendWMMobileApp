package pe.edu.upc.wheelmanagerserversite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.upc.wheelmanagerserversite.domain.model.User;
import pe.edu.upc.wheelmanagerserversite.domain.repository.CorporationRepository;
import pe.edu.upc.wheelmanagerserversite.domain.repository.UserProfileRepository;
import pe.edu.upc.wheelmanagerserversite.domain.repository.UserRepository;
import pe.edu.upc.wheelmanagerserversite.domain.service.UserService;
import pe.edu.upc.wheelmanagerserversite.exception.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CorporationRepository corporationRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;


    @Override
    public User createUser(Long userProfileId,User user) {
            return userProfileRepository.findById(userProfileId).map(userProfile -> {
                user.setUserProfile(userProfile);
                return userRepository.save(user);
            }).orElseThrow(()->new ResourceNotFoundException("UserProfile","Id",userProfileId));
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        return userRepository.findById(userId).map(user-> {
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setBusinessman(userRequest.getIsbusinessman());
            return userRepository.save(user);
        }).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
    }



    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepository.findById(userId).map(comment-> {
            userRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }
}

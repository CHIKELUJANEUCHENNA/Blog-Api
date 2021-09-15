package com.example.mytaskweek9.service.implementation;

import com.example.mytaskweek9.controller.PostController;
import com.example.mytaskweek9.dto.LoginDto;
import com.example.mytaskweek9.dto.SignUpDto;
import com.example.mytaskweek9.exceptions.AppException;
import com.example.mytaskweek9.model.*;
import com.example.mytaskweek9.repository.*;
import com.example.mytaskweek9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserService {
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final FavouriteRepository favouriteRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, LikesRepository likesRepository,
                            CommentRepository commentRepository, PostRepository postRepository,
    FavouriteRepository favouriteRepository) {
        this.userRepository = userRepository;
        this.likesRepository = likesRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.favouriteRepository = favouriteRepository;
    }


    @Override
    public ResponseEntity<?> loginUser(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        throw new AppException("Incorrect password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> signUp(SignUpDto signUpDto) {
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setFullName(signUpDto.getFullName());
        user.setPassword(signUpDto.getPassword());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


//    @Override
//    public User saveUser(long id, User user) {
//        User user1 = userRepository.getById(id);
//
//        user1.setEmail(user.getEmail());
//        user1.setFullName(user.getFullName());
//        user1.setPassword(user.getPassword());
//        user1.setPhoneNumber(user.getPhoneNumber());
////        user1.setLoggedIn(user.getLoggedIn())
//        return userRepository.save(user);
//    }

//    @Override
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }


    @Override
        public User updateUser(long id, User user) {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            throw new AppException("User not found", HttpStatus.BAD_REQUEST);
        }
        else
            user1.get().setEmail(user.getEmail());
        user1.get().setFullName(user.getFullName());
        user1.get().setPassword(user.getPassword());
        user1.get().setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(user1.get());
//        return user1.get();
    }

//    @Scheduled(fixedDelay = 5000)
    @Override
    public void deleteUser(long id) {
        User user = userRepository.getById(id);
//        List<Likes> likes = likesRepository.findLikesByUser(user);
//        List<Comments> comments = commentRepository.findCommentsByUser(user);
//        List<Post> post = user.getPostList();
//        while(likes.size() > 0) {
//            Likes likes1 = likes.get(0);
//            likesRepository.delete(likes1);
//            likes.remove(likes1);
//        }
//        while(comments.size() > 0) {
//            Comments comments1 = comments.get(0);
//            commentRepository.delete(comments1);
//            comments.remove(comments1);
//        }
//        while(post.size() > 0) {
//            Post post1 = post.get(0);
//            List<Favourite> favourite = favouriteRepository.findAll();
//            for (Favourite favourite1 : favourite) {
//                if (favourite1.getPosts().contains(post1)) {
//                    List<Post> posts = favourite1.getPosts();
//                    posts.remove(post1);
//                    favourite1.setPosts(posts);
//                    favouriteRepository.save(favourite1);
//                }
//            }
//            postRepository.delete(post1);
//            post.remove(post1);
//        }
        userRepository.deleteById(id);
    }
}

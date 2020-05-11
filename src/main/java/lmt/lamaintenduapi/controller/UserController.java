package lmt.lamaintenduapi.controller;

import lmt.lamaintenduapi.Repository.MaraudeUsersRepository;
import lmt.lamaintenduapi.Repository.UserRepository;
import lmt.lamaintenduapi.model.MaraudeUsers;
import lmt.lamaintenduapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping(value="/users")
public class UserController implements ResourceAssembler<User, Resource<User>> {


    @Autowired
    UserRepository userRepository;

    @Autowired
    MaraudeUsersRepository maraudeUsersRepository;

    @Autowired
    private EntityLinks entityLinks;

    @GetMapping(path = "/search")
    public ResponseEntity<Resource<User>> FindUserByEmail(@RequestParam(value = "email") String email){

        Resource<User> userResource;

        User user = userRepository.findByEmail(email);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            userResource = toResource(user);
            return new ResponseEntity<>(userResource, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/addUserIdParticipateInMaraudeId")
    public ResponseEntity<Resource<MaraudeUsers>> addUserAndMaraudeForParticipation(@RequestParam(value = "userId") int idUser, @RequestParam(value = "maraudeId") int idMaraude, @RequestParam(value = "participate") boolean participate){

       MaraudeUsers maraudeUsers = new MaraudeUsers(idUser, idMaraude, participate, true);

        maraudeUsers = maraudeUsersRepository.save(maraudeUsers);

        if(maraudeUsers == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

//    @GetMapping(path = "/search")
//    public UserRole findUserRoleByUserId(@RequestParam(value = "userId") int userId){
//
//        UserRole userRole;
//
//        userRole = userRoleRepository.findByUserId(userId);
//
//        if(userRole == null){
//            return null;
//        }
//        else{
//            return userRole;
//        }
//    }

    @Override
    public Resource<User> toResource(User User) {
        Link self = entityLinks.linkFor(User.class).slash(User.getId()).withSelfRel();
        Link rel = entityLinks.linkFor(User.class).slash(User.getId()).withRel("User");
        return new Resource<>(User, self, rel);
    }

}

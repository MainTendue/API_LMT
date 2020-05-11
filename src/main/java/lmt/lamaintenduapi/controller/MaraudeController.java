package lmt.lamaintenduapi.controller;

import lmt.lamaintenduapi.Repository.LieuRepository;
import lmt.lamaintenduapi.Repository.MaraudeRepository;
import lmt.lamaintenduapi.Repository.MaraudeUsersRepository;
import lmt.lamaintenduapi.model.Lieu;
import lmt.lamaintenduapi.model.Maraude;
import lmt.lamaintenduapi.model.MaraudeUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RepositoryRestController
@RequestMapping(value="/maraudeUserses")
public class MaraudeController implements ResourceAssembler<MaraudeUsers, Resource<MaraudeUsers>> {
    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    LieuRepository lieuRepository;

    @Autowired
    MaraudeUsersRepository maraudeUsersRepository;

    @Autowired
    MaraudeRepository maraudeRepository;

//    MaraudeUsers[] maraudeUsersListe;


    @GetMapping(path = "/search/findAllByMaraudeId/{maraudeId}")
    public ResponseEntity<List<MaraudeUsers>> findAllMaraudeUsersByMaraude(@PathVariable int maraudeId) {
        Optional<Maraude> optionalMaraude = this.maraudeRepository.findById(maraudeId);
        List<MaraudeUsers> maraudesUsersListe = new ArrayList<>();

        if (!optionalMaraude.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            maraudesUsersListe = this.maraudeUsersRepository.findAllMaraudeUsersByMaraudeId(optionalMaraude.get().id);
        }
        if (maraudesUsersListe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(maraudesUsersListe, HttpStatus.OK);
        }
    }


    @GetMapping(path = "/search/findAllByLieuId")
    public ResponseEntity<List<MaraudeUsers>> findAllMaraudeUsersByLieu(@RequestParam int lieuId) {
        Optional<Lieu> optionalLieu = this.lieuRepository.findById(lieuId);
        List<MaraudeUsers> maraudeUsersListe = new ArrayList<>();

        if (!optionalLieu.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            maraudeUsersListe = this.maraudeUsersRepository.findAllMaraudeUsersByLieu(optionalLieu.get().id);
        }
        if (maraudeUsersListe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(maraudeUsersListe, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/search/findAllByLieuAndDate")
    public ResponseEntity<List<MaraudeUsers>> findAllMaraudeUsersByLieuAndDate(@RequestParam int lieuId, @RequestParam String date) {

        Optional<Lieu> optionalLieu = this.lieuRepository.findById(lieuId);

        List<MaraudeUsers> maraudeUsersListe = new ArrayList<>();

        if (!optionalLieu.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            maraudeUsersListe = this.maraudeUsersRepository.findAllMaraudeUsersByLieuAndDate(lieuId, date);


        }
        if (maraudeUsersListe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(maraudeUsersListe, HttpStatus.OK);
        }
    }

    @Override
    public Resource<MaraudeUsers> toResource(MaraudeUsers MaraudeUsers) {
        Link self = entityLinks.linkFor(MaraudeUsers.class).slash(MaraudeUsers.getId()).withSelfRel();
        Link rel = entityLinks.linkFor(MaraudeUsers.class).slash(MaraudeUsers.getId()).withRel("MaraudeUsers");
        return new Resource<>(MaraudeUsers, self, rel);
    }
}

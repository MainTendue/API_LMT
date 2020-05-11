package lmt.lamaintenduapi.model;


import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Maraude extends Evenement implements Serializable{
    public String date;
    String duree;
    String commentaire;
    int participantMax;

    @ManyToOne(fetch= FetchType.EAGER)
    @RestResource(exported = false)
    Lieu lieu;
}

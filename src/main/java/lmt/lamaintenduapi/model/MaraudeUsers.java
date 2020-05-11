package lmt.lamaintenduapi.model;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class MaraudeUsers extends BaseEntity implements Serializable {

    @ManyToOne(fetch= FetchType.EAGER)
    @RestResource(exported = false)
    User user;

    @ManyToOne(fetch= FetchType.EAGER)
    @RestResource(exported = false)
    Maraude maraude;

    boolean participate;

    boolean vehicule;

   public MaraudeUsers(int idUser, int idMaraude, boolean participate, boolean vehicule){
        this.user.id = idUser;
        this.maraude.id = idMaraude;
        this.participate = participate;
        this.vehicule = vehicule;
    }

    public MaraudeUsers(User user, Maraude maraude, boolean participate, boolean vehicule){
       this.user = user;
        this.maraude = maraude;
        this.participate = participate;
        this.vehicule = vehicule;
    }
    public MaraudeUsers(){}
}

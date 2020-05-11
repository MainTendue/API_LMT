package lmt.lamaintenduapi.model;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class BesoinUsers extends BaseEntity implements Serializable {
    int quantite;

    String dateReponse;

    @ManyToOne(fetch= FetchType.EAGER)
    @RestResource(exported = false)
    BesoinsRemontes besoinsRemontes;

    @ManyToOne(fetch= FetchType.EAGER)
    @RestResource(exported = false)
    User user;

}

package lmt.lamaintenduapi.model;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BesoinEvenement extends BaseEntity implements Serializable {
    String description;

    @ManyToOne(fetch= FetchType.EAGER)
    @RestResource(exported = false)
    protected Maraude maraude;

    @ManyToOne(fetch= FetchType.EAGER)
    @RestResource(exported = false)
    protected Destinateur destinateur;

}

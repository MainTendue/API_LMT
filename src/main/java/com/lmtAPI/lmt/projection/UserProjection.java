package com.lmtAPI.lmt.projection;

import com.lmtAPI.lmt.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userProjection", types = {User.class})
public interface UserProjection {
    int getId();
    String getEmail();
    String getName();
    String getPrenom();
    String getPassword();
    String getUserIdFirebase();
    String getUrlPhoto();
}

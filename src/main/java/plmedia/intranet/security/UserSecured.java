package plmedia.intranet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import plmedia.intranet.model.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jonas Holm
 */

/**
 *  BEWARE!! VIRKER IKKE ENDNU
 *  Denne klasse extender User (dvs vi kan hente alle vores egenskaber fra user)
 *  og implementer UserDetails (Skaber kompatibilitet med spring security)
 *
 *
* */

public class UserSecured extends User implements UserDetails {

    // TODO: forsæt arbejde herfra på video
    // https://youtu.be/H94Wbd8ARKM?t=33m8s
    public UserSecured(int userId, String password, String userEmail, String firstName,
                String lastName, ArrayList<String> permissions) {

        super(userId, password, userEmail, firstName, lastName, permissions);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        super.getUserId();

        return AuthorityUtils.createAuthorityList();
    }

    @Override
    public String getUsername() {
        return "peter";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

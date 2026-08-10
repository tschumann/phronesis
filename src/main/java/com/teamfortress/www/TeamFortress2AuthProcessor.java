package com.teamfortress.www;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class TeamFortress2AuthProcessor {

    public static final String TF_ROLE = "tf";

    @Nullable
    public Authentication authenticate(HttpServletRequest request) {
            final List<GrantedAuthority> grantedAuthorities = new ArrayList<>(1);
            grantedAuthorities.add(new SimpleGrantedAuthority(TF_ROLE));

            final User user = new User("tf", "", grantedAuthorities);

            return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
    }
}

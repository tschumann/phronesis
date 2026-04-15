package org.natural_selection.www;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class NaturalSelectionAuthProcessor {

    public static final String NS_ROLE = "ns";

    public Authentication authenticate(HttpServletRequest request) {
        final String authorisation = request.getHeader("Authorization");

        if (authorisation != null) {
            // this is the Base 64 encoding of auth:mnbv5tgb (see https://github.com/unknownworlds/NS/blob/master/main/source/mod/AvHCurl.cpp)
            // using CURLOPT_USERPWD is what does the conversion to Base 64 in Natural Selection
            if (authorisation.equals("Basic YXV0aDptbmJ2NXRnYg==")) {
                final List<GrantedAuthority> grantedAuthorities = new ArrayList<>(1);
                grantedAuthorities.add(new SimpleGrantedAuthority(NS_ROLE));

                final User user = new User("ns", "", grantedAuthorities);

                return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
            }
        }

        return null;
    }
}

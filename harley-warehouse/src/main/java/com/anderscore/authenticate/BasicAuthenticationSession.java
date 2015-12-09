package com.anderscore.authenticate;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class BasicAuthenticationSession extends AuthenticatedWebSession {

    public BasicAuthenticationSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return username.equals(password) && username.equals("wicketer");
    }

    @Override
    public Roles getRoles() {
        return null;
    }

}

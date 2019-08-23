package org.annoml.servant.SpringAnnoMLServant.service;


import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {


    public boolean checkAuthorAccessToken(String authorId, String token) {
        // Verify access token with API of authorization server
        return true;
    }
}

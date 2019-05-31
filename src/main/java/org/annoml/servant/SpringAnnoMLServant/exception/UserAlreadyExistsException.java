package org.annoml.servant.SpringAnnoMLServant.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("user already existing:" + username);
    }
}

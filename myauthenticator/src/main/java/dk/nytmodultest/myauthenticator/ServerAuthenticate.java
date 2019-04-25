package dk.nytmodultest.myauthenticator;

public interface ServerAuthenticate {
    public String userSignIn(final String user, final String pass, String authType) throws Exception;
}

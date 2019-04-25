package dk.nytmodultest.myauthenticator;

import android.util.Log;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Handles the communication with nytmodultest.dk
 */

public class NmtServerAuthenticate implements ServerAuthenticate {

    @Override
    public String userSignIn(String email, String pass, String authType) throws Exception {
        Log.d("Lene", "userSignIn");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://nytmodultest.dk/modultest-api/api/login-student";
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Content-Type","application/json");
        String user = "{\"userName\":\"" + email + "\",\"password\":\"" + pass + "\"}";
        HttpEntity entity = new StringEntity(user);
        httpPost.setEntity(entity);

        String authToken = null;
        try{
            HttpResponse response = httpClient.execute(httpPost);
            String responseString = EntityUtils.toString(response.getEntity());

            if(response.getStatusLine().getStatusCode() != 200){
                NmtError error = new Gson().fromJson(responseString, NmtError.class);
                throw new Exception("Error signing in[" + error.code+"] - " + error.error);
            }

            NmtTokenResponse nmtResponse = new Gson().fromJson(responseString, NmtTokenResponse.class);
            authToken = nmtResponse.token;

        } catch (IOException e){
            e.printStackTrace();
        }

        return authToken;
//
//        String query = null;
//        try {
//            query = String.format("%s=%s&%s=%s", "username", URLEncoder.encode(user, "UTF-8"), "password", pass);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        url += "?" + query;
//
//        HttpGet httpGet = new HttpGet(url);
//
////        httpGet.addHeader("X-Parse-Application-Id", "XUafJTkPikD5XN5HxciweVuSe12gDgk2tzMltOhr");
////        httpGet.addHeader("X-Parse-REST-API-Key", "8L9yTQ3M86O4iiucwWb4JS7HkxoSKo7ssJqGChWx");
//
//        HttpParams params = new BasicHttpParams();
//        params.setParameter("username", user);
//        params.setParameter("password", pass);
//        httpGet.setParams(params);
////        httpGet.getParams().setParameter("username", user).setParameter("password", pass);
//
//        String authtoken = null;
//        try {
//            HttpResponse response = httpClient.execute(httpGet);
//
//            String responseString = EntityUtils.toString(response.getEntity());
//            if (response.getStatusLine().getStatusCode() != 200) {
//                NmtError error = new Gson().fromJson(responseString, NmtError.class);
//                throw new Exception("Error signing-in ["+error.code+"] - " + error.error);
//            }
//
//            User loggedUser = new Gson().fromJson(responseString, User.class);
//            authtoken = loggedUser.sessionToken;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return authtoken;
    }

    private class NmtError implements Serializable {
        int code;
        String error;
    }

    private class NmtTokenResponse implements Serializable {
        private boolean success;
        private String token;
        private String message;
    }

//    private class User implements Serializable {
//
//        private String firstName;
//        private String lastName;
//        private String username;
//        private String phone;
//        private String objectId;
//        public String sessionToken;
//        private String gravatarId;
//        private String avatarUrl;
//
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//
//        public String getObjectId() {
//            return objectId;
//        }
//
//        public void setObjectId(String objectId) {
//            this.objectId = objectId;
//        }
//
//        public String getSessionToken() {
//            return sessionToken;
//        }
//
//        public void setSessionToken(String sessionToken) {
//            this.sessionToken = sessionToken;
//        }
//
//        public String getGravatarId() {
//            return gravatarId;
//        }
//
//        public void setGravatarId(String gravatarId) {
//            this.gravatarId = gravatarId;
//        }
//
//        public String getAvatarUrl() {
//            return avatarUrl;
//        }
//
//        public void setAvatarUrl(String avatarUrl) {
//            this.avatarUrl = avatarUrl;
//        }
//    }
}

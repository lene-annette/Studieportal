package dk.nytmodultest.myauthenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NmtAuthenticatorService extends Service{
    @Override
    public IBinder onBind(Intent intent){
        NmtAuthenticator authenticator = new NmtAuthenticator(this);
        return authenticator.getIBinder();
    }
}

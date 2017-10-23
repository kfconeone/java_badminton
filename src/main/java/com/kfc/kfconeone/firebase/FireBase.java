package com.kfc.kfconeone.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import java.io.FileInputStream;
import java.net.URL;

public class FireBase {
    public static void Init()
    {
        try
        {
            URL resource = FireBase.class.getClassLoader().getResource("firebasekey.json");
            String path = resource.getPath();
            FileInputStream serviceAccount = new FileInputStream(path);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://kfctesting-43746.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            UserRecord userRecord = FirebaseAuth.getInstance().getUserAsync("fBqYHVEt4uajHjwkROsy1robeub2").get();

            System.out.println("Successfully fetched user data: " + userRecord.getUid());
            System.out.println("Successfully fetched user data: " + userRecord.getEmail());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

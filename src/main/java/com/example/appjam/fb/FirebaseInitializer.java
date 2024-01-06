package com.example.appjam.fb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {
    private static boolean initialized = false;

    public static void initialize() throws IOException {
        if (!initialized) {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/firebase_service_key.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://appjam-13ff4-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            initialized = true;
        }
    }
}
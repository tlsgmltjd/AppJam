package com.example.appjam.fb;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseInitializer {
    private static boolean initialized = false;

    public static void initialize() throws IOException {
        if (!initialized) {
            // 리소스를 입력 스트림으로 로드합니다.
            Resource resource = new ClassPathResource("firebase/firebase_service_key.json");
            InputStream serviceAccountStream = resource.getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .setDatabaseUrl("https://appjam-13ff4-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            initialized = true;
        }
    }
}

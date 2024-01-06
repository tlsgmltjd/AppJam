package com.example.appjam.fb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {
    private static boolean initialized = false;

    public static void initialize() throws IOException {
        if (!initialized) {
            // 클래스 패스에서 리소스 파일을 로드
            Resource resource = new ClassPathResource("firebase/firebase_service_key.json");
            FileInputStream serviceAccount = new FileInputStream(resource.getFile());

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://appjam-13ff4-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);

            initialized = true;
        }
    }
}
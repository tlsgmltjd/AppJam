package com.example.appjam.fb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.*;

import java.io.IOException;

public class FirebaseReader {

    // Function to read and render data
    public static void renderData() throws IOException {
        FirebaseInitializer.initialize();

        // Firebase Realtime Database의 루트 경로에 접근
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // ValueEventListener를 사용하여 데이터 읽기
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 데이터가 변경될 때 호출되는 메서드
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    FbUser fbUser = objectMapper.convertValue(snapshot.getValue(), FbUser.class);
                    System.out.println("Read data: " + fbUser.getToken());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 에러 발생 시 호출되는 메서드
                System.err.println("Failed to read data: " + databaseError.toException());
            }
        });
    }
}
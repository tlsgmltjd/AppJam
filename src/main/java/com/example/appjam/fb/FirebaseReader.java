package com.example.appjam.fb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FirebaseReader {

    // Function to read and render data asynchronously
    public static CompletableFuture<List<FbUser>> renderData() throws IOException {
        CompletableFuture<List<FbUser>> future = new CompletableFuture<>();

        FirebaseInitializer.initialize();

        // Firebase Realtime Database의 루트 경로에 접근
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        List<FbUser> fbUsers = new ArrayList<>();

        // ValueEventListener를 사용하여 데이터 읽기
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 데이터가 변경될 때 호출되는 메서드
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    FbUser fbUser = objectMapper.convertValue(snapshot.getValue(), FbUser.class);
                    System.out.println(fbUser);
                    fbUsers.add(fbUser);
                }

                // 데이터 읽기가 완료되면 CompletableFuture에 결과를 완료
                future.complete(fbUsers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 에러 발생 시 호출되는 메서드
                System.err.println("Failed to read data: " + databaseError.toException());

                // 에러가 발생하면 CompletableFuture에 예외를 완료
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future;
    }
}

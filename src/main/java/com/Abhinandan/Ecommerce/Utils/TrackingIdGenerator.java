//package com.Abhinandan.Ecommerce.Utils;
//
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//import java.util.UUID;
//
//@Service
//public class TrackingIdGenerator {
//
//    public UUID trackingId(String email) {
//
//        UUID random = UUID.randomUUID();
//
//        String input = email + random.toString();
//
//        UUID trackingID = UUID.nameUUIDFromBytes(input.getBytes(StandardCharsets.UTF_8));
//
//        return trackingID;
//    }
//}

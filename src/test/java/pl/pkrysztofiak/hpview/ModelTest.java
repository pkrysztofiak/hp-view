package pl.pkrysztofiak.hpview;

import java.util.UUID;
import java.util.stream.IntStream;

public class ModelTest {

    public static void main(String[] args) {
        
        IntStream.range(0, 4).forEach(next -> {
            System.out.println(UUID.randomUUID());
        });
    }
}

package com.project.States;

import com.badlogic.gdx.math.Vector2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.project.States.Birds.*;

public class BirdsSerializationTest {
    public static void main(String[] args) {
        try {
            // Step 1: Create a bird instance
            Birds bird = new Red(new Vector2(100, 200));
            System.out.println("Created bird:");
            System.out.println("Position: " + bird.getPosition());
            System.out.println("Impact Damage: " + bird.getImpactDamage());

            // Step 2: Serialize the bird
            String filePath = "bird.ser";
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
                out.writeObject(bird);
            }
            System.out.println("Bird serialized successfully to " + filePath);

            // Step 3: Deserialize the bird
            Birds deserializedBird;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
                deserializedBird = (Birds) in.readObject();
            }

            // Step 4: Verify the deserialized bird
            System.out.println("Deserialized bird:");
            System.out.println("Position: " + deserializedBird.getPosition());
            System.out.println("Impact Damage: " + deserializedBird.getImpactDamage());

        } catch (Exception e) {
            System.err.println("Error during serialization/deserialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

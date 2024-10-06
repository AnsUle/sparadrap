package Tests;

import Models.Client;
import Models.Medecin;
import Models.Mutuelle;
import Models.Specialiste; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        // Initialisez un client avec des valeurs valides
        List<Specialiste> specialistes = new ArrayList<>();
        // Ajoutez éventuellement des instances de Specialiste à la liste
        client = new Client(
                "Dupont",
                "Jean",
                "10 rue de Paris",
                "75001",
                "Paris",
                "0123456789",
                "jean.dupont@example.com",
                "1234567890123", // Numéro de sécurité sociale valide
                new Date(), // Date de naissance (par exemple, aujourd'hui)
                new Mutuelle("Mutuelle A", "123 Rue de Paris", "75001", "Paris", "0142003004", "contact@mutuellea.fr", 75.0f), // Remplacez par une instance de Mutuelle valide
                new Medecin("Dupont", "Marc", "123 Rue de Médecine", "75001", "Paris", "0142003007", "dr.dupont@medecin.fr", "A12345"), // Remplacez par une instance de Medecin valide
                specialistes // Liste de spécialistes
        );
    }

    @Test
    void testGetNumSecuriteSocial() {
        assertEquals("1234567890123", client.getNumSecuriteSocial());
    }

    @Test
    void testSetNumSecuriteSocial_Valid() {
        client.setNumSecuriteSocial("9876543210123");
        assertEquals("9876543210123", client.getNumSecuriteSocial());
    }

    @Test
    void testSetNumSecuriteSocial_Invalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.setNumSecuriteSocial("invalid"); // Numéro invalide
        });
        assertEquals("Numéro de sécurité sociale invalide", exception.getMessage());
    }

    @Test
    void testGetDateNaissance() {
        assertNotNull(client.getDateNaissance());
    }

    @Test
    void testSetDateNaissance() {
        Date newDate = new Date(); // Exemple : nouvelle date
        client.setDateNaissance(newDate);
        assertEquals(newDate, client.getDateNaissance());
    }

    @Test
    void testToString() {
        assertTrue(client.toString().contains("1234567890123"));
    }

    @Test
    void testMutuelle() {
        Mutuelle mutuelle = new Mutuelle("Mutuelle A", "123 Rue de Paris", "75001", "Paris", "0142003004", "contact@mutuellea.fr", 75.0f); // Créez une instance valide de Mutuelle
        client.setMutuelle(mutuelle);
        assertEquals(mutuelle, client.getMutuelle());
    }

    @Test
    void testMedecinTraitant() {
        Medecin medecin = new Medecin("Dupont", "Marc", "123 Rue de Médecine", "75001", "Paris", "0142003007", "dr.dupont@medecin.fr", "A12345"); // Créez une instance valide de Medecin
        client.setMedecinTraitant(medecin);
        assertEquals(medecin, client.getMedecinTraitant());
    }

    @Test
    void testSpecialistes() {
        List<Specialiste> specialistes = new ArrayList<>();
        // Ajoutez des spécialistes à la liste
        client.setSpecialistes(specialistes);
        assertEquals(specialistes, client.getSpecialistes());
    }
}


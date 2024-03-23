import org.example.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;
    @Test
    public void testCreerUtilisateur() throws ServiceException {
//TODO Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont",
                "jeandupont@email.com");

// TODO : Configuration du comportement du mock, utiliser la
//directive « when » avec sa méthode « thenReturn »
// ...
        doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);
// TODO : Création du service avec le mock
// ...
        UserService userService = new UserService(utilisateurApiMock);
// TODO : Appel de la méthode à tester
        // ...
        userService.creerUtilisateur(utilisateur);
// TODO : Vérification de l'appel à l'API
// ...
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }
    @Test
    public void testCreerUtilisateurAvecException() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Configuration du comportement du mock pour lancer une ServiceException avec le message spécifié
        ServiceException serviceException = new ServiceException("Echec de la création de l'utilisateur");
        doThrow(serviceException).when(utilisateurApiMock).creerUtilisateur(utilisateur);


        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester et gestion de l'exception levée
        ServiceException exception = assertThrows(ServiceException.class, () -> {
            userService.creerUtilisateur(utilisateur);
        });


        // Vérification de l'appel à l'API
        // On vérifie que la méthode creerUtilisateur du mock a bien été appelée avec l'utilisateur donné en argument
        verify(utilisateurApiMock).creerUtilisateur(utilisateur);
    }
@Test
    public void testCreerUtilisateur_ErreurValidation() throws ServiceException {
        // Création d'un nouvel utilisateur invalide
        Utilisateur utilisateur = new Utilisateur(null, null, null);


    doNothing().when(utilisateurApiMock).creerUtilisateur(utilisateur);

    UserService userService = new UserService(utilisateurApiMock);
    userService.creerUtilisateur(utilisateur);

    // Verify that UtilisateurApi method is never called
    verify(utilisateurApiMock, never()).creerUtilisateur(utilisateur);
    }



    @Test
    public void testCreerUtilisateur_withReturn() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Configuration du comportement du mock pour renvoyer true lors de l'appel de creerUtilisateur
        when(utilisateurApiMock.creerUtilisateurReturnBoolean(utilisateur)).thenReturn(true);

        // Définition d'un ID fictif
        int idUtilisateur = 123;

        // Configuration du mock pour renvoyer l'ID
        when(utilisateurApiMock.creerUtilisateurReturnId(utilisateur)).thenReturn(idUtilisateur);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        assertTrue(userService.creerUtilisateurReturnBoolean(utilisateur));
        int utilisateurId = userService.creerUtilisateurReturnId(utilisateur);

        // Vérification de l'appel à la méthode creerUtilisateur du mock
        verify(utilisateurApiMock).creerUtilisateurReturnBoolean(utilisateur);
        verify(utilisateurApiMock).creerUtilisateurReturnId(utilisateur);

        // Vérification de l'ID de l'utilisateur
        assertEquals(idUtilisateur, utilisateurId);

    }


    @Captor
    private ArgumentCaptor<Utilisateur> argumentCaptor;

    @Test
    public void testCreerUtilisateur_withCapture() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // Configuration du comportement du mock pour renvoyer true lors de l'appel de creerUtilisateur
        when(utilisateurApiMock.creerUtilisateurReturnBoolean(any(Utilisateur.class))).thenReturn(true);

        // Création du service avec le mock
        UserService userService = new UserService(utilisateurApiMock);

        // Appel de la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Capturer les arguments passés à la méthode creerUtilisateur
        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());
        Utilisateur utilisateurCapture = argumentCaptor.getValue();
        System.out.println(utilisateurCapture);

        // Vérification des arguments capturés
        assertEquals("Jean", utilisateurCapture.getFirstName());
        assertEquals("Dupont", utilisateurCapture.getLastName());
        assertEquals("jeandupont@email.com", utilisateurCapture.getEmail());
    }
}
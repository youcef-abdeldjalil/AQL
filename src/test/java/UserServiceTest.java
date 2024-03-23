import org.example.ServiceException;
import org.example.UserService;
import org.example.Utilisateur;
import org.example.UtilisateurApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

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
}
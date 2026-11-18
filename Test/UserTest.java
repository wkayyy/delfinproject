import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void testGetUserId(){
        //Arrange
        String gittePassword = "556234%";
        User user = new User(7, "Gitte", gittePassword, Role.KASSERER);

        //Act
        int result = user.getUserId();

        //Assert
        assertEquals(7, result);
    }

    @Test
    void testGetUsername(){
        //Arrange
        String gittePassword = "556234%";
        User user = new User(7, "Gitte", gittePassword, Role.KASSERER);

        //Act
        String result = user.getUsername();

        //Assert
        assertEquals("Gitte", result);
    }

    @Test
    void testGetPassword(){
        //Arrange
        String gittePassword = "556234%";

        User user = new User(7, "Gitte", gittePassword, Role.KASSERER);

        //Act
        String result = user.getPassword();

        //Assert
        assertEquals(gittePassword, result);
    }

    @Test
    void testGetRole(){
        //Arrange
        String gittePassword = "556234%";
        User user = new User(7, "Gitte", gittePassword, Role.KASSERER);

        //Act
       Role result = user.getRole();

        //Assert
        assertEquals(Role.KASSERER, result);
    }

}

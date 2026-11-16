import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class MemberTest {

    @Test
    void testGetMemberNumberAktiv() {
        //Arrange
        LocalDate birthDate = LocalDate.of(2003, 1, 6);
        //Act
        Member member = new Member(7,"Matthew", birthDate, "Aktiv", "Motionist");

        //Assert
        assertEquals(7, member.getMemberNumber());
    }

    @Test
    void testGetBirthDate() {
        //Arrange
        LocalDate birthDate = LocalDate.of(2003, 1, 6);
        Member member = new Member("Matthew", birthDate, "Aktiv", "Motionist");
        //Act
        LocalDate result = member.getBirthDate();
        //Assert
        assertEquals(birthDate, result);
    }

    @Test
    void testGetAge() {
        //Arrange
        LocalDate birthDate = LocalDate.now().minusYears(20);
        Member member = new Member("Matthew", birthDate, "Aktiv", "Motionist");
        //Act
        int result = member.getAge();
        //Assert
        assertNotEquals(21, result);
    }

    @Test
    void getAgeGroup() {

    }

    @Test
    void getName() {
    }

    @Test
    void getAktivitet() {
    }

    @Test
    void getSvømmetype() {
    }
}
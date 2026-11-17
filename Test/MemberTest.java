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
    void testGetAgeGroupJunior() {
        //Arrange
        LocalDate juniorYear = LocalDate.now().minusYears(17);
//        LocalDate seniorYear = LocalDate.now().minusYears(18);

        Member member = new Member("Mathias", juniorYear, "Aktiv", "Motionist");

        //Act
        String result = member.getAgeGroup();

        //Assert
        assertEquals("Junior", result);
    }

    @Test
    void testGetAgeGroupSenior(){
        //Arrange
        LocalDate seniorYear = LocalDate.now().minusYears(18);
//        LocalDate juniorYear = LocalDate.now().minusYears(17);

        Member member = new Member("Sarah", seniorYear, "Aktiv","Konkurrencesvømmer");

        //Act
        String result = member.getAgeGroup();

        //Assert
        assertEquals("Senior", result);
    }

    @Test
    void testGetName() {
        //Arrange
        Member member = new Member("Sezer", LocalDate.of(2004, 6, 15), "Aktiv", "Motionist");

        //Act
        String result = member.getName();

        //Assert
        assertNotEquals("Sarah", result);
    }

    @Test
    void testGetAktivitet() {
        //Arrange
        LocalDate birthDate = LocalDate.of(2003, 1, 6);
        Member member = new Member("Matthew", birthDate, "Aktiv", "Motionist");

        //Act
        String result = member.getAktivitet();

        //Assert
        assertEquals("Aktiv",result);
    }

    @Test
    void testGetSvømmetype() {
        //Arrange
        LocalDate birthDate = LocalDate.of(2003, 1, 6);
        Member member = new Member("Matthew", birthDate, "Aktiv", "Motionist");

        //Act
        String result = member.getSvømmetype();

        //Assert
        assertNotEquals("Konkurrencesvømmer", result);
    }
}
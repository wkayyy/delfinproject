import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembershipFeeTest {
    @Test
    void testPassivMemberBetaler500(){
        //Arrange
        MembershipFee membershipFee = new MembershipFee();
        Member member = new Member("Matthew", LocalDate.now().minusYears(23), "Passiv", "Motionist");
        member.setAntalfamiliemedlemmer(3);

        //Act
        int result = membershipFee.calculateFee(member);

        //Assert
        assertEquals(500, result);
    }

    @Test
    void testJuniorMemberBetaler1000(){
        //Arrange
        MembershipFee membershipFee = new MembershipFee();
        Member member = new Member("Mathias", LocalDate.now().minusYears(17), "Aktiv", "Motionist"); //17 fordi junior

        //Act
        int result = membershipFee.calculateFee(member);

        //Assert
        assertEquals(1000, result);
    }

    @Test
    void testSenior18YearMemberBetaler1600(){
        //Arrange
        LocalDate præcis18 = LocalDate.now().minusYears(18);
        MembershipFee membershipFee = new MembershipFee();
        Member member = new Member("Sarah", præcis18, "Aktiv", "Konkurrencesvømmer");

        //Act
        int result = membershipFee.calculateFee(member);

        //Assert
        assertEquals(1600, result);
    }

    @Test
    void testSeniorOver18Betaler1600(){
        //Arrange
        LocalDate over18 = LocalDate.now().minusYears(60);
        MembershipFee membershipFee = new MembershipFee();
        Member lars = new Member("Lars", over18, "Aktiv", "Motionist");

        //Act
        int result = membershipFee.calculateFee(lars);

        //Assert
        assertEquals(1600, result);
    }

    @Test
    void testSeniorPensionistBetaler1200(){
        //Arrange
        LocalDate pensionistOver60 = LocalDate.now().minusYears(75);
        MembershipFee membershipFee = new MembershipFee();
        Member sjanne = new Member("Sjanne", pensionistOver60, "Aktiv", "Motionist");

        //Act
        int result = membershipFee.calculateFee(sjanne);

        //Assert
        assertEquals(1200, result);
    }
}

import java.time.LocalDate;
import java.time.Period;

public class Member {

    int memberNumber;
    String name;
    LocalDate birthDate;
    String aktivitet;
    String svømmetype;

    static int nextMemberNumber = 1;

    public Member(String name, LocalDate birthDate, String aktivitet) {
        this.memberNumber = nextMemberNumber;
        nextMemberNumber++;
        this.name = name;
        this.birthDate = birthDate;
        this.aktivitet = aktivitet;
}

public int getMemberNumber() {
        return memberNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getAgeGroup() {
        if (getAge() < 18) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    public String getName() {
        return name;
    }

    public String getAktivitet() {
        return aktivitet;
    }
}

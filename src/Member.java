
import java.time.LocalDate;
import java.time.Period;

public class Member {

    int memberNumber;
    String name;
    LocalDate birthDate;
    String aktivitet;
    boolean kontingentBetalt = false;
    String svømmetype;
    LocalDate indmeldelsesdato;

    static int nextMemberNumber = 1;

    // Opretter et nyt medlem
    public Member(String name, LocalDate birthDate, String aktivitet, String svømmetype) {
        this.memberNumber = nextMemberNumber;
        nextMemberNumber++;

        this.name = name;
        this.birthDate = birthDate;
        this.aktivitet = aktivitet;
        this.svømmetype = svømmetype;
        this.indmeldelsesdato = LocalDate.now();
    }

    // Indlæser et eksisterende medlem
    public Member(int memberNumber, String name, LocalDate birthDate, String aktivitet, String svømmetype) {
        this.memberNumber = memberNumber;

        if (memberNumber >= nextMemberNumber) {
            nextMemberNumber = memberNumber + 1;
        }

        this.name = name;
        this.birthDate = birthDate;
        this.aktivitet = aktivitet;
        this.svømmetype = svømmetype;
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

    public String getSvømmetype() {
        return svømmetype;
    }
    public boolean isKontingentBetalt() {
        return kontingentBetalt;
    }

    public void setKontingentBetalt(boolean kontingentBetalt) {
        this.kontingentBetalt = kontingentBetalt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAktivitet(String aktivitet) {
        this.aktivitet = aktivitet;
    }

    public void setSvømmetype(String svømmetype) {
        this.svømmetype = svømmetype;
    }

    public LocalDate getIndmeldelsesdato() {
        return indmeldelsesdato;
    }

    public void setIndmeldelsesdato(LocalDate indmeldelsesdato) {
        this.indmeldelsesdato = indmeldelsesdato;
    }

    public int getMedlemsAar() {
        return Period.between(indmeldelsesdato, LocalDate.now()).getYears();
    }
}

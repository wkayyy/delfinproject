import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

void main() {
  IO.println("Hej og velkommen til svømmehallen");

  ArrayList<Member> medlemmer = new ArrayList<>();

  String svar = "ja";

  while (svar.equals("ja")) {

    IO.print("Indtast navn: ");
    String name = IO.readln();

    LocalDate birthDate = null;

    try {
      IO.print("Indtast fødselsdato (ÅÅÅÅ-MM-DD): ");
      birthDate = LocalDate.parse(IO.readln());
    } catch (DateTimeParseException e) {
      IO.println("Ugyldig fødselsdato!");
      continue;
    }

    IO.println("Vælg aktivitetsform:");
    IO.println("1. Aktiv");
    IO.println("2. Passiv");

    IO.print("Vælg: ");
    String aktivitet = IO.readln();

    if (aktivitet.equals("1")) {
      aktivitet = "Aktiv";
    } else if (aktivitet.equals("2")) {
      aktivitet = "Passiv";
    }

    Member medlem1 = new Member(name, birthDate, aktivitet);
    medlemmer.add(medlem1);

  IO.print("Vil du oprette et nyt medlem? (ja/nej): ");
  svar = IO.readln().toLowerCase();
  }
}

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

void main() {
  IO.println("Hej og velkommen til svømmehallen");

  MemberFile memberFile = new MemberFile();
  ArrayList<Member> medlemmer = memberFile.loadMembers();

  IO.println("");
  IO.println("===== DELFINEN =====");
  IO.println("Medlemsregister indlæst.");

  String valg = "";

  while (!valg.equals("0")) {

    IO.println("");
    IO.println("----- DELFINEN =====");
    IO.println("1. Opret medlem");
    IO.println("2. Vis medlemmer");
    IO.println("0. Afslut");
    IO.print("Vælg: ");

    valg = IO.readln();

    if (valg.equals("1")) {

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

      if (birthDate.isAfter(LocalDate.now())) {
        IO.println("Fødselsdatoen kan ikke være i fremtiden!");
        continue;
      }

      IO.println("Vælg aktivitetsform:");
      IO.println("1. Aktiv");
      IO.println("2. Passiv");
      IO.print("Vælg: ");

      String aktivitet = IO.readln();

      if (!aktivitet.equals("1") && !aktivitet.equals("2")) {
        IO.println("Ugyldigt! Prøv igen.");
        continue;
      }

      String svømmetype = "Ikke relevant";

      if (aktivitet.equals("1")) {
        aktivitet = "Aktiv";

        IO.println("Vælg svømmetype:");
        IO.println("1. Motionist");
        IO.println("2. Konkurrencesvømmer");
        IO.print("Vælg: ");

        String svømmeValg = IO.readln();

        if (svømmeValg.equals("1")) {
          svømmetype = "Motionist";
        } else if (svømmeValg.equals("2")) {
          svømmetype = "Konkurrencesvømmer";
        } else {
          IO.println("Ugyldigt! Prøv igen.");
          continue;
        }

      } else if (aktivitet.equals("2")) {
        aktivitet = "Passiv";
      }

      Member medlem1 = new Member(name, birthDate, aktivitet, svømmetype);
      medlemmer.add(medlem1);

      memberFile.saveMembers(medlemmer);

      IO.println("Medlem oprettet! Medlemsnummer: " + medlem1.getMemberNumber());

    } else if (valg.equals("2")) {

      IO.println("");
      IO.println("===== ALLE MEDLEMMER =====");

      if (medlemmer.isEmpty()) {
        IO.println("Der er ingen medlemmer oprettet.");
      } else {

        for (Member medlem : medlemmer) {
          IO.println("-------------------------");
          IO.println("Medlemsnummer: " + medlem.getMemberNumber());
          IO.println("Navn: " + medlem.getName());
          IO.println("Fødselsdato: " + medlem.getBirthDate());
          IO.println("Alder: " + medlem.getAge());
          IO.println("Aldersgruppe: " + medlem.getAgeGroup());
          IO.println("Aktivitet: " + medlem.getAktivitet());
          IO.println("Svømmetype: " + medlem.getSvømmetype());

          MembershipFee membershipFee = new MembershipFee();
          int fee = membershipFee.calculateFee(medlem);

          IO.println("Årligt kontingent: " + fee + " kr.");
        }

        IO.println("-------------------------");

      }

    } else if (valg.equals("0")) {

      IO.println("Programmet afsluttes.");

    } else {
      IO.println("Ugyldigt valg!");
    }
  }
}


import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConsoleUI {

    LoginSystem loginSystem = new LoginSystem();
    MemberFile memberFile = new MemberFile();
    MembershipFee membershipFee = new MembershipFee();

    ArrayList<Member> medlemmer = memberFile.loadMembers();

    public void start() {

        IO.println("Hej og velkommen til svømmehallen");

        // Testbrugere
        loginSystem.addUser(new User(1, "formand", "1234", Role.FORMAND));
        loginSystem.addUser(new User(2, "kasserer", "1234", Role.KASSERER));
        loginSystem.addUser(new User(3, "træner", "1234", Role.TRÆNER));

        login();

    }

    public void login() {

        User loggedIn = null;

        while (loggedIn == null) {

            IO.println("");
            IO.println("===== DELFINEN LOGIN =====");

            IO.print("Brugernavn: ");
            String username = IO.readln();

            IO.print("Adgangskode: ");
            String password = IO.readln();

            loggedIn = loginSystem.login(username, password);

            if (loggedIn == null) {
                IO.println("Forkert brugernavn eller adgangskode!");
                IO.println("Prøv igen.");
            }
        }

        IO.println("");
        IO.println("Velkommen, " + loggedIn.getUsername());
        IO.println("Din rolle: " + loggedIn.getRole());

        menu();

    }

    public void menu() {

        String valg = "";

        while (!valg.equals("0")) {

            IO.println("");
            IO.println("===== DELFINEN =====");
            IO.println("1. Opret medlem");
            IO.println("2. Vis medlemmer");
            IO.println("0. Afslut");
            IO.print("Vælg: ");

            valg = IO.readln();

            if (valg.equals("1")) {

                opretMedlem();

            } else if (valg.equals("2")) {

                visMedlemmer();

            } else if (valg.equals("0")) {

                IO.println("Programmet afsluttes.");

            } else {

                IO.println("Ugyldigt valg!");

            }
        }
    }

    public void opretMedlem() {

        IO.print("Indtast navn: ");
        String name = IO.readln();

        LocalDate birthDate;

        try {
            IO.print("Indtast fødselsdato (ÅÅÅÅ-MM-DD): ");
            birthDate = LocalDate.parse(IO.readln());

        } catch (DateTimeParseException e) {

            IO.println("Ugyldig fødselsdato!");
            return;
        }

        if (birthDate.isAfter(LocalDate.now())) {
            IO.println("Fødselsdatoen kan ikke være i fremtiden!");
            return;
        }

        IO.println("Vælg aktivitetsform:");
        IO.println("1. Aktiv");
        IO.println("2. Passiv");
        IO.print("Vælg: ");

        String aktivitet = IO.readln();

        if (!aktivitet.equals("1") && !aktivitet.equals("2")) {
            IO.println("Ugyldigt valg! Prøv igen.");
            return;
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
                IO.println("Ugyldigt valg! Prøv igen.");
                return;
            }

        } else {
            aktivitet = "Passiv";
        }

        Member medlem1 = new Member(name, birthDate, aktivitet, svømmetype);

        medlemmer.add(medlem1);

        memberFile.saveMembers(medlemmer);

        IO.println("Medlem oprettet! Medlemsnummer: " + medlem1.getMemberNumber());

    }

    public void visMedlemmer() {

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

                int fee = membershipFee.calculateFee(medlem);

                IO.println("Årligt kontingent: " + fee + " kr.");

            }

            IO.println("-------------------------");
        }
    }
}


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

        visRolleMenu(loggedIn);

    }

    public void visRolleMenu(User user) {

        if (user.getRole() == Role.FORMAND) {
            formandMenu();

        } else if (user.getRole() == Role.KASSERER) {
            kassererMenu();

        } else if (user.getRole() == Role.TRÆNER) {
            traenerMenu();
        }
    }

    public void formandMenu() {

        String valg = "";

        while (!valg.equals("0")) {

            IO.println("");
            IO.println("===== FORMANDENS MENU =====");
            IO.println("1. Opret medlem");
            IO.println("2. Vis alle medlemmer");
            IO.println("3. Se forventet kontingentindtægt");
            IO.println("0. Afslut");
            IO.print("Vælg: ");

            valg = IO.readln();

            if (valg.equals("1")) {
                opretMedlem();

            } else if (valg.equals("2")) {
                visMedlemmer();

            } else if (valg.equals("3")) {
                visForventetIndtaegt();

            } else if (!valg.equals("0")) {
                IO.println("Ugyldigt valg!");
            }
        }
    }

    public void kassererMenu() {

        String valg = "";

        while (!valg.equals("0")) {

            IO.println("");
            IO.println("===== KASSERERENS MENU =====");
            IO.println("1. Vis alle medlemmer");
            IO.println("2. Se forventet kontingentindtægt");
            IO.println("3. Se medlemmer med gæld");
            IO.println("4. Registrer betaling");
            IO.println("0. Afslut");
            IO.print("Vælg: ");

            valg = IO.readln();

            if (valg.equals("1")) {
                visMedlemmer();

            } else if (valg.equals("2")) {
                visForventetIndtaegt();

            } else if (valg.equals("3")) {
                visRestancer();

            } else if (valg.equals("4")) {
                registrerBetaling();

            } else if (!valg.equals("0")) {
                IO.println("Ugyldigt valg!");
            }
        }
    }
    public void traenerMenu() {

        String valg = "";

        while (!valg.equals("0")) {

            IO.println("");
            IO.println("===== TRÆNERENS MENU =====");
            IO.println("1. Se mine hold");
            IO.println("0. Afslut");
            IO.print("Vælg: ");

            valg = IO.readln();

            if (valg.equals("1")) {
                IO.println("Holdoversigten bliver snart tilføjet.");

            } else if (!valg.equals("0")) {
                IO.println("Ugyldigt valg!");
            }
        }
    }

    public void visRestancer() {

        IO.println("");
        IO.println("===== MEDLEMMER MED RESTANCER =====");

        boolean fundet = false;

        for (Member medlem : medlemmer) {

            if (!medlem.isKontingentBetalt()) {

                int fee = membershipFee.calculateFee(medlem);

                IO.println("-------------------------");
                IO.println("Medlemsnummer: " + medlem.getMemberNumber());
                IO.println("Navn: " + medlem.getName());
                IO.println("Skylder: " + fee + " kr.");

                fundet = true;
            }
        }

        if (!fundet) {
            IO.println("Ingen medlemmer har udestående kontingent.");
        }
    }

    public void registrerBetaling() {

        IO.println("");
        IO.println("===== REGISTRER BETALING =====");

        IO.print("Indtast medlemsnummer: ");
        int medlemsnummer = Integer.parseInt(IO.readln());

        boolean fundet = false;

        for (Member medlem : medlemmer) {

            if (medlem.getMemberNumber() == medlemsnummer) {

                fundet = true;

                if (medlem.isKontingentBetalt()) {
                    IO.println("Medlemmet har allerede betalt.");
                } else {
                    medlem.setKontingentBetalt(true);
                    memberFile.saveMembers(medlemmer);

                    IO.println("Betaling registreret!");
                    IO.println(medlem.getName() + " er nu registreret som betalt.");
                }
            }
        }

        if (!fundet) {
            IO.println("Medlemsnummeret findes ikke.");
        }
    }

    public void visForventetIndtaegt() {

        int samletIndtaegt = 0;

        for (Member medlem : medlemmer) {

            int fee = membershipFee.calculateFee(medlem);

            samletIndtaegt = samletIndtaegt + fee;
        }

        IO.println("");
        IO.println("===== FORVENTET KONTINGENT =====");
        IO.println("Antal medlemmer: " + medlemmer.size());
        IO.println("Forventet indtægt: " + samletIndtaegt + " kr.");
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

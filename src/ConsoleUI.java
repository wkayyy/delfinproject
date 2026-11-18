
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ConsoleUI {

    LoginSystem loginSystem = new LoginSystem();
    MemberFile memberFile = new MemberFile();
    MembershipFee membershipFee = new MembershipFee();
    ArrayList<Member> medlemmer = memberFile.loadMembers();
    TrainingResultFile resultFile = new TrainingResultFile();

    public void start() {

        resultFile.loadResults(medlemmer);

        IO.println("Hej og velkommen til svømmehallen");

        // login koder til de forskellige ting
        loginSystem.addUser(new User(1, "formand", "1234", Role.FORMAND));
        loginSystem.addUser(new User(2, "kasserer", "1234", Role.KASSERER));
        loginSystem.addUser(new User(3, "traener", "1234", Role.TRÆNER));

        login();

    }

    public void login() {

        User loggedIn = null;

        while (loggedIn == null) {


            IO.println("");
            IO.println("╔══════════════════════════════════════════════╗");
            IO.println("║                                              ║");
            IO.println("║                 DELFINEN                     ║");
            IO.println("║              ADMINISTRATION                  ║");
            IO.println("║                                              ║");
            IO.println("╠══════════════════════════════════════════════╣");
            IO.println("║                                              ║");

            IO.print("║  Brugernavn: ");
            String username = IO.readln();

            IO.print("║  Adgangskode: ");
            String password = IO.readln();

            loggedIn = loginSystem.login(username, password);

            IO.println("║                                              ║");
            IO.println("╚══════════════════════════════════════════════╝");

            if (loggedIn == null) {
                IO.println("");
                IO.println("  [!] FORKERT BRUGERNAVN ELLER ADGANGSKODE");
                IO.println("  Prøv venligst igen.");
            }
        }

        IO.println("");
        IO.println("╔══════════════════════════════════════════════╗");
        IO.println("║               LOGIN GODKENDT                 ║");
        IO.println("╠══════════════════════════════════════════════╣");
        IO.println("║  Velkommen," + loggedIn.getUsername() + "                           ║");
        IO.println("║  Rolle:" + loggedIn.getRole() + "                               ║");
        IO.println("╚══════════════════════════════════════════════╝");

        visRolleMenu(loggedIn);


    }

    public void visRolleMenu(User user) {

        if (user.getRole() == Role.FORMAND) {
            formandMenu();

        } else if (user.getRole() == Role.KASSERER) {
            kassererMenu();

        } else if (user.getRole() == Role.TRÆNER) {
            trænerMenu(user);
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
            IO.println("4. Ret medlemsoplysninger");
            IO.println("0. Afslut");
            IO.print("Vælg: ");

            valg = IO.readln();

            if (valg.equals("1")) {
                opretMedlem();

            } else if (valg.equals("2")) {
                visMedlemmer();

            } else if (valg.equals("3")) {
                visForventetIndtaegt();

            } else if (valg.equals("4")) {
                retmedlemsoplysninger();

            } else if (!valg.equals("0")) {
                IO.println("Ugyldigt valg!");
            }
        }
    }

    public void retmedlemsoplysninger() {

        IO.println("");
        IO.println("===== RET MEDLEMSOPLYSNINGER =====");

        IO.print("Indtast medlemsnummer: ");
        int medlemsnummer = Integer.parseInt(IO.readln());

        Member valgtMedlem = null;

        for (Member medlem : medlemmer) {
            if (medlem.getMemberNumber() == medlemsnummer) {
                valgtMedlem = medlem;
                break;
            }
        }

        if (valgtMedlem == null) {
            IO.println("Medlemsnummeret findes ikke.");
            return;
        }

        String valg = "";

        while (!valg.equals("0")) {

            IO.println("");
            IO.println("Hvad vil du rette?");
            IO.println("1. Navn");
            IO.println("2. Fødselsdato");
            IO.println("3. Aktivitet");
            IO.println("4. Svømmetype");
            IO.println("5. Familie");
            IO.println("0. Tilbage");

            IO.print("Vælg: ");
            valg = IO.readln();

            if (valg.equals("1")) {

                IO.print("Indtast nyt navn: ");
                valgtMedlem.setName(IO.readln());

            } else if (valg.equals("2")) {

                IO.print("Indtast ny fødselsdato (ÅÅÅÅ-MM-DD): ");

                try {
                    LocalDate nyDato = LocalDate.parse(IO.readln());

                    if (nyDato.isAfter(LocalDate.now())) {
                        IO.println("Datoen må ikke være i fremtiden.");
                    } else {
                        valgtMedlem.setBirthDate(nyDato);
                    }

                } catch (DateTimeParseException e) {
                    IO.println("Ugyldigt datoformat.");
                }

            } else if (valg.equals("3")) {

                IO.println("1. Aktiv");
                IO.println("2. Passiv");
                IO.print("Vælg aktivitet: ");

                String aktivitetValg = IO.readln();

                if (aktivitetValg.equals("1")) {
                    valgtMedlem.setAktivitet("Aktiv");

                } else if (aktivitetValg.equals("2")) {
                    valgtMedlem.setAktivitet("Passiv");
                    valgtMedlem.setSvømmetype("Ikke relevant");

                } else {
                    IO.println("Ugyldigt valg.");
                }

            } else if (valg.equals("4")) {

                IO.println("1. Motionist");
                IO.println("2. Konkurrencesvømmer");
                IO.print("Vælg svømmetype: ");

                String typeValg = IO.readln();

                if (typeValg.equals("1")) {
                    valgtMedlem.setSvømmetype("Motionist");

                } else if (typeValg.equals("2")) {
                    valgtMedlem.setSvømmetype("Konkurrencesvømmer");

                } else {
                    IO.println("Ugyldigt valg.");
                }

            } else if (valg.equals("5")) {

                IO.println("");
                IO.println("===== FAMILIE =====");
                IO.println("1. Gør medlem til hovedmedlem");
                IO.println("2. Tilføj familiemedlem");
                IO.println("3. Fjern familiemedlem");
                IO.println("0. Tilbage");
                IO.print("Vælg: ");

                String familieValg = IO.readln();

                if (familieValg.equals("1")) {

                    valgtMedlem.setFamiliehovedmedlem(valgtMedlem.getMemberNumber());

                    IO.println("Medlemmet er nu hovedmedlem.");

                } else if (familieValg.equals("2")) {

                    IO.print("Indtast medlemsnummer på familiemedlemmet: ");
                    int familieNummer = Integer.parseInt(IO.readln());

                    Member familieMedlem = null;

                    for (Member medlem : medlemmer) {
                        if (medlem.getMemberNumber() == familieNummer) {
                            familieMedlem = medlem;
                            break;
                        }
                    }

                    if (familieMedlem == null) {
                        IO.println("Medlemsnummeret findes ikke.");

                    } else if (familieMedlem.getMemberNumber() == valgtMedlem.getMemberNumber()) {
                        IO.println("Et medlem kan ikke tilføje sig selv.");

                    } else {

                        int hovedmedlemNummer = valgtMedlem.getFamiliehovedmedlem();

                        if (hovedmedlemNummer == 0) {
                            hovedmedlemNummer = valgtMedlem.getMemberNumber();
                        }

                        familieMedlem.setFamiliehovedmedlem(hovedmedlemNummer);

                        IO.println("Familiemedlemmet er tilføjet.");
                    }

                } else if (familieValg.equals("3")) {

                    IO.print("Indtast medlemsnummer på familiemedlemmet, der skal fjernes: ");
                    int familieNummer = Integer.parseInt(IO.readln());

                    Member familieMedlem = null;

                    for (Member medlem : medlemmer) {
                        if (medlem.getMemberNumber() == familieNummer) {
                            familieMedlem = medlem;
                            break;
                        }
                    }

                    if (familieMedlem == null) {
                        IO.println("Medlemsnummeret findes ikke.");

                    } else if (familieMedlem.getMemberNumber() == familieMedlem.getFamiliehovedmedlem()) {
                        IO.println("Hovedmedlemmet kan ikke fjernes på denne måde.");

                    } else if (familieMedlem.getFamiliehovedmedlem() == 0) {
                        IO.println("Medlemmet er ikke en del af en familie.");

                    } else {
                        familieMedlem.setFamiliehovedmedlem(0);
                        IO.println("Familiemedlemmet er fjernet.");
                    }

                } else if (!familieValg.equals("0")) {
                    IO.println("Ugyldigt valg.");
                }

            } else if (!valg.equals("0")) {
                IO.println("Ugyldigt valg!");
            }

            memberFile.saveMembers(medlemmer);
        }

        IO.println("Medlemsoplysninger gemt.");
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
    public void trænerMenu(User træner) {

        String valg = "";

        while (!valg.equals("0")) {

            IO.println("");
            IO.println("===== TRÆNERENS MENU =====");
            IO.println("1. Tilknyt konkurrencesvømmer");
            IO.println("2. Se mine svømmere");
            IO.println("3. Registrer træningsresultat");
            IO.println("4. Se bedste træningstider");
            IO.println("0. Afslut");
            IO.print("Vælg: ");

            valg = IO.readln();

            if (valg.equals("1")) {
                tilknytSvømmer(træner);

            } else if (valg.equals("2")) {
                visMineSvømmere(træner);

            } else if (valg.equals("3")) {
                registrerTræningsresultat();

            } else if (valg.equals("4")) {
                visBedsteTider();

            } else if (!valg.equals("0")) {
                IO.println("Ugyldigt valg!");
            }
        }
    }

    public void tilknytSvømmer(User træner) {

        IO.println("");
        IO.println("===== TILKNYT KONKURRENCESVØMMER =====");
        IO.print("Indtast medlemsnummer: ");

        int medlemsnummer;

        try {
            medlemsnummer = Integer.parseInt(IO.readln());
        } catch (NumberFormatException e) {
            IO.println("Medlemsnummer skal være et tal.");
            return;
        }

        Member valgt = null;

        for (Member medlem : medlemmer) {
            if (medlem.getMemberNumber() == medlemsnummer) {
                valgt = medlem;
                break;
            }
        }

        if (valgt == null) {
            IO.println("Medlemsnummeret findes ikke.");
            return;
        }

        if (!valgt.erKonkurrencesvømmer()) {
            IO.println("Kun konkurrencesvømmere kan tilknyttes en træner.");
            return;
        }

        if (valgt.getTrainerId() == træner.getUserId()) {
            IO.println("Svømmeren er allerede tilknyttet dig.");
            return;
        }

        if (valgt.getTrainerId() != 0) {
            IO.println("Svømmeren har allerede en anden træner.");
            return;
        }

        valgt.setTrainerId(træner.getUserId());
        memberFile.saveMembers(medlemmer);

        IO.println(valgt.getName() + " er nu tilknyttet dig.");
    }

    public void visMineSvømmere(User træner) {

        IO.println("");
        IO.println("===== MINE SVØMMERE =====");

        visSvømmereIGruppe(træner, "Junior", "under 18 år");
        visSvømmereIGruppe(træner, "Senior", "fra 18 år");
    }

    private void visSvømmereIGruppe(User træner, String aldersgruppe, String beskrivelse) {

        IO.println("");
        IO.println("--- " + aldersgruppe + " (" + beskrivelse + ") ---");

        boolean fundet = false;

        for (Member medlem : medlemmer) {
            if (medlem.getTrainerId() == træner.getUserId()
                    && medlem.getAgeGroup().equals(aldersgruppe)) {

                IO.println(medlem.getMemberNumber() + " - " + medlem.getName());
                fundet = true;
            }
        }

        if (!fundet) {
            IO.println("Ingen svømmere.");
        }
    }

    private Member vælgKonkurrencesvømmer() {

        IO.print("Indtast medlemsnummer: ");

        int medlemsnummer;

        try {
            medlemsnummer = Integer.parseInt(IO.readln());
        } catch (NumberFormatException e) {
            IO.println("Medlemsnummer skal være et tal.");
            return null;
        }

        for (Member medlem : medlemmer) {
            if (medlem.getMemberNumber() == medlemsnummer) {

                if (!medlem.erKonkurrencesvømmer()) {
                    IO.println("Medlemmet er ikke konkurrencesvømmer.");
                    return null;
                }

                return medlem;
            }
        }

        IO.println("Medlemsnummeret findes ikke.");
        return null;
    }

    public void registrerTræningsresultat() {

        IO.println("");
        IO.println("===== REGISTRER TRÆNINGSRESULTAT =====");

        Member svømmer = vælgKonkurrencesvømmer();

        if (svømmer == null) {
            return;
        }

        Discipliner[] discipliner = Discipliner.values();

        IO.println("Vælg disciplin:");
        for (int i = 0; i < discipliner.length; i++) {
            IO.println((i + 1) + ". " + discipliner[i]);
        }
        IO.print("Vælg: ");

        int disciplinValg;

        try {
            disciplinValg = Integer.parseInt(IO.readln());
        } catch (NumberFormatException e) {
            IO.println("Ugyldigt valg.");
            return;
        }

        if (disciplinValg < 1 || disciplinValg > discipliner.length) {
            IO.println("Ugyldigt valg.");
            return;
        }

        Discipliner disciplin = discipliner[disciplinValg - 1];

        IO.print("Indtast tid i sekunder (fx 58.4): ");

        double tid;

        try {
            tid = Double.parseDouble(IO.readln().replace(",", "."));
        } catch (NumberFormatException e) {
            IO.println("Ugyldig tid.");
            return;
        }

        if (Double.isNaN(tid) || Double.isInfinite(tid) || tid <= 0) {
            IO.println("Tiden skal være større end 0.");
            return;
        }

        IO.print("Indtast dato (ÅÅÅÅ-MM-DD): ");

        LocalDate dato;

        try {
            dato = LocalDate.parse(IO.readln());
        } catch (DateTimeParseException e) {
            IO.println("Ugyldig dato.");
            return;
        }

        if (dato.isAfter(LocalDate.now())) {
            IO.println("Datoen må ikke være i fremtiden.");
            return;
        }

        TrainingResult tidligereBedste = svømmer.getBestResult(disciplin);

        svømmer.addTrainingResult(new TrainingResult(disciplin, tid, dato));
        resultFile.saveResults(medlemmer);

        IO.println("Resultat registreret for " + svømmer.getName() + ".");

        if (tidligereBedste == null || tid < tidligereBedste.getTidISekunder()) {
            IO.println("Ny bedste tid i " + disciplin + "!");
        }
    }

    public void visBedsteTider() {

        IO.println("");
        IO.println("===== BEDSTE TRÆNINGSTIDER =====");

        Member svømmer = vælgKonkurrencesvømmer();

        if (svømmer == null) {
            return;
        }

        IO.println("Svømmer: " + svømmer.getName());

        for (Discipliner disciplin : Discipliner.values()) {

            TrainingResult bedste = svømmer.getBestResult(disciplin);

            if (bedste == null) {
                IO.println(disciplin + ": ingen resultater endnu");
            } else {
                IO.println(disciplin + ": " + bedste.getTidISekunder()
                        + " sek. (" + bedste.getDato() + ")");
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
                IO.println("Medlem siden: " + medlem.getIndmeldelsesdato());
                IO.println("Medlemsanciennitet: " + medlem.getMedlemsAar() + " år");
                if (medlem.getFamiliehovedmedlem() != 0) {
                    IO.println("Familie: Ja");
                } else {
                    IO.println("Familie: Nej");
                }
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

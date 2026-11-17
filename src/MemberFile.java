
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.time.LocalDate;

public class MemberFile {

    public void saveMembers(ArrayList<Member> medlemmer) {

        try {
            FileWriter writer = new FileWriter("medlemmer.csv");

            for (Member medlem : medlemmer) {
                writer.write(
                        medlem.getMemberNumber() + ";" +
                                medlem.getName() + ";" +
                                medlem.getBirthDate() + ";" +
                                medlem.getAktivitet() + ";" +
                                medlem.getSvømmetype() + ";" +
                                medlem.isKontingentBetalt() + ";" +
                                medlem.getIndmeldelsesdato() + ";"+
                                medlem.getFamiliehovedmedlem()+"\n"
                );
            }

            writer.close();

            System.out.println("MEDLEMMER ER GEMT!");
            System.out.println("Filens placering: " +
                    new java.io.File("medlemmer.csv").getAbsolutePath());

        } catch (IOException e) {
            System.out.println("FEJL VED GEMNING: " + e.getMessage());
        }
    }


    public ArrayList<Member> loadMembers() {

        ArrayList<Member> medlemmer = new ArrayList<>();

        try {
            File file = new File("medlemmer.csv");

            if (!file.exists()) {
                return medlemmer;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine();

                String[] oplysninger = linje.split(";");

                int memberNumber = Integer.parseInt(oplysninger[0]);
                String name = oplysninger[1];
                LocalDate birthDate = LocalDate.parse(oplysninger[2]);
                String aktivitet = oplysninger[3];
                String svømmetype = oplysninger[4];
                LocalDate indmeldelsesdato = LocalDate.now();
                int familieHovedmedlem = 0;

                if (oplysninger.length > 6) {
                    indmeldelsesdato = LocalDate.parse(oplysninger[6]);
                }

                if (oplysninger.length > 7) {
                    familieHovedmedlem = Integer.parseInt(oplysninger[7]);
                }

                Member medlem = new Member(memberNumber, name, birthDate, aktivitet, svømmetype);

                medlem.setKontingentBetalt(medlem.kontingentBetalt);
                medlem.setIndmeldelsesdato(indmeldelsesdato);
                medlem.setFamiliehovedmedlem(familieHovedmedlem);

                medlemmer.add(medlem);
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af medlemmer!");
        }

        return medlemmer;
    }

}

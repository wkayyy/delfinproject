import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainingResultFile {

    private static final String FILNAVN = "traeningsresultater.csv";

    public void saveResults(ArrayList<Member> medlemmer) {

        try {
            FileWriter writer = new FileWriter(FILNAVN);

            for (Member medlem : medlemmer) {
                for (TrainingResult resultat : medlem.getTrainingResults()) {
                    writer.write(
                            medlem.getMemberNumber() + ";" +
                                    resultat.getDiscipline() + ";" +
                                    resultat.getTidISekunder() + ";" +
                                    resultat.getDato() + "\n"
                    );
                }
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("FEJL VED GEMNING AF RESULTATER: " + e.getMessage());
        }
    }

    public void loadResults(ArrayList<Member> medlemmer) {

        File file = new File(FILNAVN);

        if (!file.exists()) {
            return;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine();

                if (linje.isBlank()) {
                    continue;
                }

                String[] oplysninger = linje.split(";");

                int medlemsnummer = Integer.parseInt(oplysninger[0]);
                Discipliner disciplin = Discipliner.valueOf(oplysninger[1]);
                double tid = Double.parseDouble(oplysninger[2]);
                LocalDate dato = LocalDate.parse(oplysninger[3]);

                for (Member medlem : medlemmer) {
                    if (medlem.getMemberNumber() == medlemsnummer) {
                        medlem.addTrainingResult(new TrainingResult(disciplin, tid, dato));
                        break;
                    }
                }
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af resultater!");
        }
    }
}
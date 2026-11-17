import java.time.LocalDate;
import java.util.List;

public class TrainingResult {
   private final Discipliner discipline;
   private final double tidISekunder;
   private final LocalDate dato;

   public TrainingResult(Discipliner discipline, double tidISekunder, LocalDate dato) {
       this.discipline = discipline;
       this.tidISekunder = tidISekunder;
       this.dato = dato;
   }
    public Discipliner getDiscipline() {
        return discipline;
    }

    public double getTidISekunder() {
        return tidISekunder;
    }

    public LocalDate getDato() {
        return dato;
    }
}

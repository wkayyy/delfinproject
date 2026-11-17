public class MembershipFee {
    public int calculateFee(Member member){
        if("Passiv".equals(member.getAktivitet())){ //Udmelding
                return 500;
        }

        int age = member.getAge(); //Henter alder fra member


        int familiepris = member.getFamiliehovedmedlem();

        if (familiepris >= 2) {
            return 800;
        }

        if (age < 18){
            return 1000;
        } else if (age > 60) {
            return 1200;
        } else {
            return 1600;
        }

    }


}

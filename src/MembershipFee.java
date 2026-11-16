public class MembershipFee {
    public int calculateFee(Member member){
        if("Passiv".equals(member.getAktivitet())){
                return 500;
        }

        int age = member.getAge(); //Henter alder fra member

        if (age < 18){
            return 1000;
        } else if (age > 60) {
            return 1200;
        } else {
            return 1600;
        }
    }
}

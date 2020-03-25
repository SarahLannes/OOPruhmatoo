public class Mäng {
    Integer[][] tabel;

    public static boolean loos(){
        return Math.random() < 0.5;
    }
    public static void valialustaja(Mängija essamängija, Mängija teinemängija){
        if (loos()==false){
            System.out.println("jou");
            Mängija hoia = essamängija;
            essamängija=teinemängija;
            teinemängija=hoia;
        }
    }
}

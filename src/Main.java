public class Main {

    public static void main(String[] args) {
        Mängija essamängija= new Mängija("Scanner", 55);
        Mängija teinemängija = new Mängija("Java", 10);
        System.out.println(essamängija.getNimi());
        System.out.println(teinemängija.getNimi());
        if (Mäng.valiAlustaja()==false){
            System.out.println("jou");
            Mängija hoia = essamängija;
            essamängija=teinemängija;
            teinemängija=hoia;
        }
        Mäng.täidaTabel();
        Mäng.valjasta_tabel();

    }
}

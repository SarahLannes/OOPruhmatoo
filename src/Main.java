import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Mäng Ühenda4");
        System.out.println("Mängu eesmärgiks on saada 4 märki järjest kas ritta, veergu või diagonaali.");
        System.out.println("Head mängimist!");
        System.out.println();
        Scanner in = new Scanner(System.in);
        System.out.printf("Sisesta esimese mängija nimi: ");
        String esimesenimi = in.next();
        System.out.printf("Sisesta teise mängija nimi: ");
        String teisenimi = in.next();
        Mängija essamängija= new Mängija(esimesenimi);
        Mängija teinemängija = new Mängija(teisenimi);
        Mäng.pärisMäng(essamängija, teinemängija);


    }
}

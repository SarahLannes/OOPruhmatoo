import java.util.Arrays;
import java.util.Scanner;

public class Mang {
    //mängutabel
    static String[][] tabel = new String[6][7];

    /**
     * Meetodi abil valitakse loosiga kumb mängija alustab.
     *
     * Parameetrid puuduvad.
     * Meetod tagastab tõeväärtuse true, kui alustab esimene mängija ja false, kui alustab teine mängija.
     */
    private static boolean valiAlustaja() {
        return Math.random() < 0.5;
    }

    /**
     * Meetodi abil täidetakse tühi tabel "-"-ga.
     *
     * Parameetrid puuduvad.
     * Meetod ei tagasta midagi.
     */
    private static void taidaTabel() {
        for (int i = 0; i < tabel.length; i++) {
            String[] rida = tabel[i];
            Arrays.fill(rida, "-");
        }
    }

    /**
     * Meetodi abil tehakse käik.
     *
     * @param mark Esimese või teise mängija märk mida hakatakse tabelisse lisama.
     * Meetod ei tagasta midagi.
     */
    private static void kaik(String mark) {
        valjastaTabel();
        boolean kaikTehtud = false;
        while (!kaikTehtud) {
            System.out.print("Millisesse veergu tahad käiku teha? ");
            Scanner in = new Scanner(System.in);
            int mitmesRida = in.nextInt() - 1;
            if (mitmesRida < tabel[0].length && mitmesRida >= 0) {
                for (int i = tabel.length - 1; i >= 0; i--) {
                    String[] rida = tabel[i];
                    if (rida[mitmesRida].equals("-")) {
                        rida[mitmesRida] = mark;
                        kaikTehtud = true;
                        break;
                    } else if (i == 0) {
                        //kui rida on juba täis.
                        System.out.println("See rida on juba täis!!!");
                        valjastaTabel();
                        break;
                    }
                }
            } else {
                //kui sellise indeksiga rida ei leidu.
                System.out.println("See rida ei sobi!!!");
                valjastaTabel();
            }
        }
    }


    /**
     * Meetod kontrollib, kas mõnes veerus, reas või diagonaalis on neli samasugust sümbolit reas.
     *
     * Parameetrid puuduvad.
     * Meetod tagastab 1, kui esimene mängija on võitnud, 2 kui teine mängija on võitnud ja 0 kui kumbki mängija pole veel võitnud.
     */
    private static int kasNeliReas() {
        //ridade kontroll
        for (int i = tabel.length - 1; i > 0; i--) {
            String[] rida = tabel[i];
            String sone = String.join("", rida);
            if (sone.contains("XXXX")) {
                return 1;
            } else if (sone.contains("OOOO")) {
                return 2;
            }
        }
        //veergude kontroll
        for (int i = 0; i < tabel[0].length; i++) {
            String[] veerg = new String[tabel.length];
            for (int j = 0; j < tabel.length; j++) {
                veerg[j] = tabel[j][i];
            }
            String sone = String.join("", veerg);
            if (sone.contains("XXXX")) {
                return 1;
            } else if (sone.contains("OOOO")) {
                return 2;
            }
        }
        //diagonaalide kontroll
        return kontrolliDiagonaalid();
    }
    /**
     * Meetod kontrollib, kas mõnes diagonaalis on neli samasugust sümbolit reas.
     *
     * Parameetrid puuduvad.
     * Meetod tagastab 1, kui esimene mängija on võitnud, 2 kui teine mängija on võitnud ja 0 kui kumbki mängija pole veel võitnud.
     */
    private static int kontrolliDiagonaalid() {
        // ühtepidi diagonaalide kontroll
        for (int i = 3; i < tabel.length; i++) {
            for (int j = 0; j < tabel[0].length - 3; j++) {
                if ((tabel[i][j] + tabel[i - 1][j + 1] + tabel[i - 2][j + 2] + tabel[i - 3][j + 3]).equals("XXXX")) {
                    return 1;
                } else if ((tabel[i][j] + tabel[i - 1][j + 1] + tabel[i - 2][j + 2] + tabel[i - 3][j + 3]).equals("OOOO")) {
                    return 2;
                }
            }
        }
        // teistpidi diagonaalide kontroll
        for (int i = 3; i < tabel.length; i++) {
            for (int j = 3; j < tabel[0].length; j++) {
                if ((tabel[i][j] + tabel[i - 1][j - 1] + tabel[i - 2][j - 2] + tabel[i - 3][j - 3]).equals("XXXX")) {
                    return 1;
                } else if ((tabel[i][j] + tabel[i - 1][j - 1] + tabel[i - 2][j - 2] + tabel[i - 3][j - 3]).equals("OOOO")) {
                    return 2;
                }
            }
        }
        return 0;
    }
    /**
     * Meetod väljastab mängutabeli hetkeseisu.
     *
     * Parameetrid puuduvad.
     * Meetod ei tagasta midagi.
     */
    private static void valjastaTabel() {
        for (int i = 0; i < tabel.length; i++) {
            for (int j = 0; j < tabel[i].length; j++)
                System.out.print(tabel[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Meetodis rakendatakse ülaltoodud meetodeid, et "käivitada" mäng.
     *
     * @param essamangija Mängija number 1.
     * @param teinemangija Mängija number 2.
     * Meetod ei tagasta midagi.
     */
    public static void parisMang(Mangija essamangija, Mangija teinemangija) {
        if (!Mang.valiAlustaja()) {
            Mangija hoia = essamangija;
            essamangija = teinemangija;
            teinemangija = hoia;
        }

        //See kes alustab, lisab X-e.
        //Teine mängija mängib O-dega.
        System.out.println(essamangija.getNimi() + " märgiks on X.");
        System.out.println(teinemangija.getNimi() + " märgiks on O.");

        Mang.taidaTabel();
        int kellekaik = 0;
        //Kui muutuja kellekäik väärtus on paarisarv, siis käib esimene mängija, kui paaritu arv, siis käib teine mängija.
        while (kasNeliReas() == 0) {
            System.out.println();
            System.out.println();
            if (kellekaik % 2 == 0) {
                System.out.println(essamangija.nimi + " kord!");
                kaik("X");

            } else {
                System.out.println(teinemangija.nimi + " kord!");
                kaik("O");

            }
            kellekaik++;
            System.out.println("Käik tehtud!");
        }
        valjastaTabel();
        if (kasNeliReas() == 1) {
            System.out.println(essamangija.getNimi() + " võitis!");
        } else {
            System.out.println(teinemangija.getNimi() + " võitis!");
        }

    }
}



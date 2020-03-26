import java.util.Arrays;
import java.util.Scanner;

public class Mäng {
    //mängutabel
    static String[][] tabel = new String[6][7];

    /**
     * Meetodi abil valitakse loosiga kumb mängija alustab.
     *
     * Parameetrid puuduvad.
     * Meetod ei tagasta midagi.
     */
    public static boolean valiAlustaja() {
        return Math.random() < 0.5;
    }

    /**
     * Meetodi abil täidetakse tühi tabel "-"-ga.
     *
     * Parameetrid puuduvad.
     * Meetod ei tagasta midagi.
     */
    public static void täidaTabel() {
        for (int i = 0; i < tabel.length; i++) {
            String[] rida = tabel[i];
            Arrays.fill(rida, "-");
        }
    }

    /**
     * Meetodi abil tehakse käik.
     *
     * @param märk Esimese või teise mängija märk mida hakatakse tabelisse lisama.
     * Meetod ei tagasta midagi.
     */
    public static void käik(String märk) {
        valjasta_tabel();
        boolean käikTehtud = false;
        while (!käikTehtud) {
            System.out.printf("Millisesse veergu tahad käiku teha? ");
            Scanner in = new Scanner(System.in);
            int mitmesRida = in.nextInt() - 1;
            if (mitmesRida < tabel[0].length && mitmesRida >= 0) {
                for (int i = tabel.length - 1; i >= 0; i--) {
                    String[] rida = tabel[i];
                    if (rida[mitmesRida].equals("-")) {
                        rida[mitmesRida] = märk;
                        käikTehtud = true;
                        break;
                    } else if (i == 0) {
                        //kui rida on juba täis.
                        System.out.println("See rida on juba täis!!!");
                        valjasta_tabel();
                        break;
                    }
                }
            } else if (käikTehtud == false) {
                //kui sellise indeksiga rida ei leidu.
                System.out.println("See rida ei sobi!!!");
                valjasta_tabel();
            }
        }
    }


    /**
     * Meetod kontrollib, kas mõnes veerus, reas või diagonaalis on neli samasugust sümbolit reas.
     *
     * Parameetrid puuduvad.
     * Meetod tagastab 1, kui esimene mängija on võitnud, 2 kui teine mängija on võitnud ja 0 kui kumbki mängija pole veel võitnud.
     */
    public static int kasNeliReas() {
        //ridade kontroll
        for (int i = tabel.length - 1; i > 0; i--) {
            String[] rida = tabel[i];
            String sõne = String.join("", rida);
            if (sõne.contains("XXXX")) {
                return 1;
            } else if (sõne.contains("OOOO")) {
                return 2;
            }
        }
        //veergude kontroll
        for (int i = 0; i < tabel[0].length; i++) {
            String[] veerg = new String[tabel.length];
            for (int j = 0; j < tabel.length; j++) {
                veerg[j] = tabel[j][i];
            }
            String sõne = String.join("", veerg);
            if (sõne.contains("XXXX")) {
                return 1;
            } else if (sõne.contains("OOOO")) {
                return 2;
            }
        }
        //diagonaalide kontroll
        return kontrollidiagonaalid();
    }
    /**
     * Meetod kontrollib, kas mõnes diagonaalis on neli samasugust sümbolit reas.
     *
     * Parameetrid puuduvad.
     * Meetod tagastab 1, kui esimene mängija on võitnud, 2 kui teine mängija on võitnud ja 0 kui kumbki mängija pole veel võitnud.
     */
    public static int kontrollidiagonaalid() {
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
    public static void valjasta_tabel() {
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
     * @param essamängija Mängija number 1.
     * @param teinemängija Mängija number 2.
     * Meetod ei tagasta midagi.
     */
    public static void pärisMäng(Mängija essamängija, Mängija teinemängija) {
        if (!Mäng.valiAlustaja()) {
            Mängija hoia = essamängija;
            essamängija = teinemängija;
            teinemängija = hoia;
        }

        //See kes alustab, lisab X-e.
        //Teine mängija mängib O-dega.
        System.out.println(essamängija.getNimi() + " märgiks on X.");
        System.out.println(teinemängija.getNimi() + " märgiks on O.");

        Mäng.täidaTabel();
        int kellekäik = 0;
        while (kasNeliReas() == 0) {
            System.out.println();
            System.out.println();
            if (kellekäik % 2 == 0) {
                System.out.println(essamängija.nimi + " kord!");
                käik("X");

            } else {
                System.out.println(teinemängija.nimi + " kord!");
                käik("O");

            }
            kellekäik++;
            System.out.println("Käik tehtud!");
        }
        valjasta_tabel();
        if (kasNeliReas() == 1) {
            System.out.println(essamängija.getNimi() + " võitis!");
        } else {
            System.out.println(teinemängija.getNimi() + " võitis!");
        }

    }
}



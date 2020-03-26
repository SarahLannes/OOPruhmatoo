import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Mäng {
    static String[][] tabel = new String[6][7];

    public static boolean valiAlustaja() {
        return Math.random() < 0.5;
    }


    public static void täidaTabel() {
        for (int i = 0; i < tabel.length; i++) {
            String[] rida = tabel[i];
            for (int j = 0; j < rida.length; j++) {
                rida[j] = "-";
            }
        }
    }

    public static void käik(String märk) {
        valjasta_tabel();
        boolean käikTehtud = false;
        while (käikTehtud == false) {
            System.out.printf("Millisesse veergu tahad käiku teha? ");
            Scanner in = new Scanner(System.in);
            int mitmesRida = in.nextInt() - 1;
            if (mitmesRida < tabel.length - 1 && mitmesRida >= 0) {
                for (int i = tabel.length - 1; i >= 0; i--) {
                    String[] rida = tabel[i];
                    if (rida[mitmesRida].equals("-")) {
                        rida[mitmesRida] = märk;
                        käikTehtud = true;
                        break;
                    } else if (i == 0) {
                        System.out.println("See rida on juba täis!!!");
                        valjasta_tabel();
                        break;
                    }
                }
            } else if (käikTehtud == false) {
                System.out.println("See rida ei sobi!!!");
                valjasta_tabel();
            }
        }
    }

    //See kes alustab, lisab X-e.
    //Teine mängija mängib O-dega.
    public static int kasNeliReas() {
        for (int i = tabel.length - 1; i > 0; i--) {
            String[] rida = tabel[i];
            String sõne = String.join("", rida);
            if (sõne.contains("XXXX")) {
                return 1;
            } else if (sõne.contains("OOOO")) {
                return 2;
            }
        }
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
        int diagonaalivõit= kontrollidiagonaalid();

        if (diagonaalivõit>0){
            return diagonaalivõit;
        }

        return 0;
    }

    public static int kontrollidiagonaalid() {
        for (int rowStart = 0; rowStart < tabel[0].length - 4; rowStart++) {
            List<String> diagonaal = new ArrayList();
            int row, col;
            for (row = rowStart, col = 0; row < tabel[0].length-1 && col < tabel.length; row++, col++) {
                diagonaal.add(tabel[row][col]);
            }
            String kokku = diagonaal.toString().replace("[", "").replace("]", "")
                    .replace(", ", "");
            ;
            System.out.println(kokku);
            if (kokku.contains("XXXX")) {
                return 1;
            } else if (kokku.contains("OOOO")) {
                return 2;
            }
        }

// top-left to bottom-right - red diagonals
        for (int colStart = 1; colStart < tabel.length - 4; colStart++) {
            StringBuilder diagonaal1 = new StringBuilder();
            int row, col;
            for (row = 0, col = colStart; row < tabel[0].length && col < tabel.length; row++, col++) {
                diagonaal1.append(tabel[row][col]);
            }
            String kokku1=diagonaal1.toString().replace("[", "").replace("]", "")
                    .replace(", ", "");
            if (kokku1.contains("XXXX")) {
                return 1;
            } else if (kokku1.contains("OOOO")) {
                return 2;
            }
        }
        return 0;
    }

    public static void valjasta_tabel() {
        for (int i = 0; i < tabel.length; i++) {
            for (int j = 0; j < tabel[i].length; j++)
                System.out.print(tabel[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void pärisMäng(Mängija essamängija, Mängija teinemängija) {
        if (Mäng.valiAlustaja() == false) {
            Mängija hoia = essamängija;
            essamängija = teinemängija;
            teinemängija = hoia;
        }
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
            System.out.println(essamängija.nimi + " võitis!");
        } else {
            System.out.println(teinemängija.nimi + " võitis!");
        }


    }
}



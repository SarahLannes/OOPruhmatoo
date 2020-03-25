public class Mäng {
    static String[][] tabel = new String[7][6];

    public static boolean valiAlustaja(){
        return Math.random() < 0.5;
    }


    //See kes alustab, lisab X-e.
    //Teine mängija mängib O-dega.
    public static int kasNeliReas(){
        for (int i = tabel.length-1; i < 0; i--) {
            String[] rida = tabel[i];
            String sõne = String.join("", rida);
            if (sõne.contains("XXXX")){
                return 1;
            }
            else if (sõne.contains("OOOO")){
                return 2;
            }
        }
        for (int i = 0; i < tabel[0].length; i++) {
            String[] veerg = new String[tabel.length];
            for (int j = 0; j <tabel.length ; j++) {
                veerg[j]=tabel[j][i];
            }
            String sõne = String.join("", veerg);
            if (sõne.contains("XXXX")){
                return 1;
            }
            else if (sõne.contains("OOOO")){
                return 2;
            }
        }
        return 0;
    }

    public static void täidaTabel(){
        for (int i = 0; i <tabel.length ; i++) {
            String [] rida = tabel[i];
            for (int j = 0; j <rida.length ; j++) {
                rida[j] ="-";
            }
        }
    }

    public static void valjasta_tabel() {
        for (int i = 0; i < tabel.length; i++) {
            for (int j = 0; j < tabel[i].length; j++)
                System.out.print(tabel[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

}

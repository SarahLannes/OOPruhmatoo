public class Mäng {
    static String[][] tabel = new String[7][6];

    public static boolean valiAlustaja(){
        return Math.random() < 0.5;
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

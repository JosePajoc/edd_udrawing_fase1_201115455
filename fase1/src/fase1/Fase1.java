package fase1;

import java.util.Scanner;

public class Fase1 {

    public static void main(String[] args) {
        int op = 0;
        do{
            try {
                System.out.println("------------------------------------------------------");
                System.out.println("1. Parámetros iniciales");
                System.out.println("2. Ejecutar paso");
                System.out.println("3. Estado en memoria de las estructuras");
                System.out.println("4. Reportes");
                System.out.println("5. Acerca de");
                System.out.println("6. Salir\n");
                System.out.println("Ingrese el número de opción: ");
                Scanner entrada1 = new Scanner(System.in);
                op = entrada1.nextInt();
                if(op == 1){
                   
                    System.out.println("\ta. Carga masiva clientes");
                    System.out.println("\tb. Cantidad de ventanillas\n");
                    System.out.println("Ingrese la literal de la opción: ");
                    Scanner entrada2 = new Scanner(System.in);
                    char op2 = entrada2.nextLine().charAt(0);  
                }
            }catch(Exception error){
                System.out.println("---> El valor ingresado es incorrecto <---");
            }

        }while (op != 6);
    }

}

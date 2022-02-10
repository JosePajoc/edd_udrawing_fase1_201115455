package fase1;

import java.util.Scanner;

public class Fase1 {

    public static void main(String[] args) {
        int op = 0;
        String rutaJson;
        boolean jsonCarga = false, ventanillaCarga = false;
        int ventanillasNum;
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
                    System.out.println("\tIngrese la literal de la opción: ");
                    Scanner entrada2 = new Scanner(System.in);
                    char op2 = entrada2.nextLine().charAt(0); 
                    switch (op2) {
                        case 'a':
                            System.out.println("\t\tIngrese la ruta del archivo JSON que posee los datos: ");
                            rutaJson = entrada2.nextLine();
                            System.out.println("\t\tLa ruta es-> " + rutaJson);
                            jsonCarga = true;
                            break;
                        case 'b':
                            System.out.println("\t\tIngrese el número de ventanillas: ");
                            ventanillasNum = entrada1.nextInt();
                            System.out.println("\t\tEl número de ventanillas registradas es-> " + ventanillasNum);
                            ventanillaCarga = true;
                            break;
                        default:
                            System.out.println("Opción no valida");
                            break;
                    }
                }else if(op == 2){
                    if(jsonCarga && ventanillaCarga){
                        System.out.println("-------------------------> EJECUTANDO PASO <-------------------------");
                    }else{
                        System.out.println("Verificar si se cargaron datos de los clientes y de las ventanillas");
                    }
                }
            }catch(Exception error){
                System.out.println("---> El valor ingresado es incorrecto <---");
            }

        }while (op != 6);
    }

}

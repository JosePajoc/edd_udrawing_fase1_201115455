package fase1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Fase1 {

    static int op = 0;
    static boolean jsonCarga = false, ventanillaCarga = false;
    static int ventanillasNum;
    static listaSimpleClientes listaClientes = new listaSimpleClientes();

    public static void main(String[] args) {

        do {
            //try {
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
                if (op == 1) {
                    System.out.println("\ta. Carga masiva clientes");
                    System.out.println("\tb. Cantidad de ventanillas\n");
                    System.out.println("\tIngrese la literal de la opción: ");
                    Scanner entrada2 = new Scanner(System.in);
                    char op2 = entrada2.nextLine().charAt(0);
                    switch (op2) {
                        case 'a':
                            System.out.println("\t\tIngrese la ruta del archivo JSON que posee los datos: ");
                            String rutaJson = entrada2.nextLine();
                            leerJson(rutaJson);     //Llamar función de lectura
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
                } else if (op == 2) {
                    if (jsonCarga && ventanillaCarga) {
                        System.out.println("-------------------------> EJECUTANDO PASO <-------------------------");
                    } else {
                        System.out.println("Verificar si se cargaron datos de los clientes y de las ventanillas");
                    }
                }
            //} catch (Exception error) {
                //System.out.println("---> El valor ingresado es incorrecto <---");
            //}

        } while (op != 6);
    }

    public static void leerJson(String rutaJson) {
        try {
            //Crear objeto JsonParser, se usa para almacenar el json externo
            JSONParser convertidor = new JSONParser();
            //Objeto generico para manipular el json externo
            Object objeto = convertidor.parse(new FileReader(rutaJson));
            //Hacer casteo al objeto generico y convertirlo a Objeto JSON
            JSONObject entradaJson = (JSONObject) objeto;
            for (int i = 1; i <= entradaJson.size(); i++) {
                //Iterando para cada cliente del Json
                Map datos = ((Map) entradaJson.get("Cliente" + i));
                //System.out.println("--------------------------");
                //Se crea objeto para acceder a los datos del mapa en iteraciones
                Iterator<Map.Entry> info = datos.entrySet().iterator();
                //Variables temporales para usar en el nodo cliente
                int id = 0, img_color = 0, img_bw = 0;
                String nombre = "";
                //Mientras que datos posea valor
                while (info.hasNext()) {
                    //A cada valor lo asignamos a una variable que poseerá Clave y valor
                    Map.Entry par = info.next();
                    //Mostrar cada par
                    //System.out.println(par.getKey() + ":" + par.getValue());
                    if (par.getKey().equals("id_cliente")) {
                        id =  Integer.valueOf(String.valueOf(par.getValue()));
                    } else if (par.getKey().equals("nombre_cliente")) {
                        nombre = String.valueOf(par.getValue()); 
                    } else if (par.getKey().equals("img_color")) {
                        img_color = Integer.valueOf(String.valueOf(par.getValue()));
                    } else if (par.getKey().equals("img_bw")) {
                        img_bw = Integer.valueOf(String.valueOf(par.getValue()));
                    }
                }
                listaClientes.insertarNodoCliente(id, nombre, img_color, img_bw);
            }
            System.out.println("Total de clientes cargados -> " + entradaJson.size());
            listaClientes.verNodosClientes();
            jsonCarga = true;
        } catch (IOException e) {

        } catch (ParseException e) {

        }
    }

}

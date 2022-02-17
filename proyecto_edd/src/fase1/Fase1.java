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
    static int cantidadJsonCargados;
    static String[] nombres = {"Luis", "Pedro", "Maria", "Melisa", "Marcos", "Hugo", "Karen", "Karina", "Maribel", "Cristal", "Silvestre", "Juan", "Rocky"};
    static String[] apellidos = {"Santos", "Pineda", "Asunción", "Pelico", "Estrada", "Hernandez", "Us", "Po", "Sax", "Sosa", "Estalon", "VanDame", "Balboa"};
    //CR -> Cola de recepción
    static listaSimpleClientesCR listaClientes = new listaSimpleClientesCR();
    static listaSimpleVentanillas listaVentanillas = new listaSimpleVentanillas();
    static colaImpBW colaImpresionBW = new colaImpBW();
    static colaImpColor colaImpresionColor = new colaImpColor();
    static listaCircularDobleEspera salaDeEspera = new listaCircularDobleEspera();

    public static void main(String[] args) {

        do {
            //try {
            System.out.println("-------------------------------------------------- MENÚ PRINCIPAL --------------------------------------------------");
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
                        if (jsonCarga == false) {
                            System.out.println("\t\tIngrese la ruta del archivo JSON que posee los datos: ");
                            String rutaJson = entrada2.nextLine();
                            leerJson(rutaJson);     //Llamar función de lectura
                        } else {
                            System.out.println("#################### Ya se han cargado los clientes ####################\n");
                        }
                        break;
                    case 'b':
                        if (ventanillaCarga == false) {
                            System.out.println("\t\tIngrese el número de ventanillas: ");
                            ventanillasNum = entrada1.nextInt();
                            System.out.println("\t\t#################### El número de ventanillas registradas es -> " + ventanillasNum + "  ####################");
                            if (ventanillasNum > 1) {
                                //Creación de ventanillas
                                for (int i = 0; i < ventanillasNum; i++) {
                                    listaVentanillas.insertarNodoVentanilla(i);
                                }
                                ventanillaCarga = true;
                                //listaVentanillas.verNodosVentanillas();
                            }
                        } else {
                            System.out.println("#################### Ya se ha cargado el número de ventanillas ####################\n");
                        }
                        break;
                    default:
                        System.out.println("#################### Opción no valida ####################");
                        break;
                }
            } else if (op == 2) {
                if (jsonCarga && ventanillaCarga) {
                    System.out.println("\n-----------------------------------> EJECUTANDO PASO <-----------------------------------\n");
                    
                    //-------------------> área de clientes que se crean para entrar a la cola de recepción
                    int valor = (int) (Math.random() * 3); //aleatorio entre 0 y 3
                    for (int i = 0; i < valor; i++) {
                        cantidadJsonCargados++;     //se usa para continuar numeración del Json para los nuevos clientes
                        generarClientes(cantidadJsonCargados);
                    }
                    System.out.println("#################### Cantidad clientes creados -> " + valor);
                    System.out.println("#################### Total de clientes en cola de recepción -> " + listaClientes.verCantidadClientes());

                    //--------------------> área para que cada cliente pase a una ventanilla disponible
                    while (listaVentanillas.verVentanillaDisponible()) {
                        listaVentanillas.atenderCliente(listaClientes.sacarClienteCR());
                        System.out.println("--> Cliente atendido <--");
                    }

                    System.out.println("##################### Clientes en cola de recepción #####################");
                    listaClientes.verNodosClientes();
                    listaVentanillas.ver();

                    //--------------------> área de recepción de imágenes en cada ventanilla
                    listaVentanillas.recepcionImg();

                    //--------------------> área de envio de imágenes a impresoras
                    for (int i = 0; i < ventanillasNum; i++) {
                        //usando nodo ventanilla que finalizo recepción
                        if (listaVentanillas.enviarImpresion() != null) {
                            //Se extrae la pila para no perderla y así enviarla a la impresora respectiva
                            pilaImg pilaTemporal = listaVentanillas.enviarImpresion().pila_img;

                            TnodoClienteP aux = pilaTemporal.inicio;
                            while (aux != null) {
                                if(aux.tipoImg.equals("img_bw")){
                                    colaImpresionBW.insertarImagenCola(aux.id, aux.nombre, aux.tipoImg);
                                }else if(aux.tipoImg.equals("img_color")){
                                    colaImpresionColor.insertarImagenCola(aux.id, aux.nombre, aux.tipoImg);
                                }
                                aux = aux.siguiente;
                            }
                            //enviando cliente a sala de espera (lista circular doble enlazada)
                            int idTemp = listaVentanillas.enviarImpresion().cliente.id;
                            String nombreTemp = listaVentanillas.enviarImpresion().cliente.nombre;
                            salaDeEspera.insertarClienteEspera(idTemp, nombreTemp);
                            
                            //Restaurando la ventanilla con valores iniciales
                            listaVentanillas.enviarImpresion().habilitado = true;
                            listaVentanillas.enviarImpresion().cliente = null;
                            listaVentanillas.enviarImpresion().pila_img = new pilaImg();
                            listaVentanillas.enviarImpresion().recepcionFin = false;
                        }
                    }
                    //área para ver la sala de espera
                    System.out.println("\n#################### Clientes en la sala de espera ####################");
                    System.out.println(salaDeEspera.verListaCircularDobleEspera());
                    
                    //--------------------> área para ver las colas de impresión
                    System.out.println("\n.............................. Cola impresora BW ..............................");
                    System.out.println(colaImpresionBW.verColaImpBW());
                    System.out.println("............................. Cola impresora Color ..............................");
                    System.out.println(colaImpresionColor.verColaImpColor());
                                        
                    
                    System.out.println("\n-----------------------------------> PASO FINALIZADO <-----------------------------------\n");
                } else {
                    System.out.println("################## Verificar si se cargaron datos de los clientes y de las ventanillas ###################");
                }
            } else if (op == 3) {

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
                //Se crea objeto para acceder a los datos del mapa en iteraciones
                Iterator<Map.Entry> info = datos.entrySet().iterator();
                //Variables temporales para usar en el nodo cliente
                int id = 0, img_color = 0, img_bw = 0;
                String nombre = "";
                //Mientras que datos posea valor
                while (info.hasNext()) {
                    //A cada valor lo asignamos a una variable que poseerá Clave y valor
                    Map.Entry par = info.next();
                    //Mostrar cada par  //System.out.println(par.getKey() + ":" + par.getValue());
                    if (par.getKey().equals("id_cliente")) {
                        id = Integer.valueOf(String.valueOf(par.getValue()));
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
            System.out.println("#################### Total de clientes en cola de recepción -> " + entradaJson.size());
            cantidadJsonCargados = entradaJson.size(); //Asignación para uso posterior
            jsonCarga = true;
        } catch (IOException e) {

        } catch (ParseException e) {

        }
    }

    public static void generarClientes(int numeracion) {
        int fin = nombres.length - 1;
        int valor = (int) (Math.random() * fin);  //Valor aleatorio entre 0 y el tamaño de la lista de nombres
        String temp_cliente = nombres[valor] + " " + apellidos[valor] + "-" + String.valueOf(numeracion);
        int valor_img = (int) (Math.random() * 4); //Valor aleatorio entre 0 y 4

        if (valor_img == 4) {
            listaClientes.insertarNodoCliente(numeracion, temp_cliente, valor_img, 0);
        } else {
            listaClientes.insertarNodoCliente(numeracion, temp_cliente, valor_img, (4 - valor_img));
        }
    }
}

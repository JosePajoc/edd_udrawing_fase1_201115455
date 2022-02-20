package fase1;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Fase1 {

    static int op = 0;
    static boolean jsonCarga = false, ventanillaCarga = false, pasoEjecutado = false;
    static int ventanillasNum;
    static int cantidadJsonCargados;
    static String[] nombres = {"Luis", "Pedro", "Maria", "Melisa", "Marcos", "Hugo", "Karen", "Karina", "Maribel", "Cristal", "Silvestre", "Juan", "Rocky"};
    static String[] apellidos = {"Santos", "Pineda", "Asunción", "Pelico", "Estrada", "Hernandez", "Us", "Po", "Sax", "Sosa", "Estalon", "VanDame", "Balboa"};
    static int pasosColor = 0;
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
                    int valor = (int) (Math.random() * 3); //aleatorio para crear clientes entre 0 y 3
                    for (int i = 0; i < valor; i++) {
                        cantidadJsonCargados++;     //se usa para continuar numeración del Json para los nuevos clientes
                        generarClientes(cantidadJsonCargados);
                    }
                    System.out.println("#################### Cantidad clientes creados -> " + valor);
                    System.out.println("#################### Total de clientes en cola de recepción antes de abrir ventanillas -> " + listaClientes.verCantidadClientes());

                    //--------------------> área para que cada cliente pase a una ventanilla disponible
                    //No pueden haber más ventanillas disponibles que clientes en cola de recepción
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
                                if (aux.tipoImg.equals("img_bw")) {
                                    colaImpresionBW.insertarImagenCola(aux.id, aux.nombre, aux.tipoImg);
                                } else if (aux.tipoImg.equals("img_color")) {
                                    colaImpresionColor.insertarImagenCola(aux.id, aux.nombre, aux.tipoImg);
                                }
                                aux = aux.siguiente;
                            }
                            //enviando cliente a sala de espera (lista circular doble enlazada)
                            int idTemp = listaVentanillas.enviarImpresion().cliente.id;
                            String nombreTemp = listaVentanillas.enviarImpresion().cliente.nombre;
                            int totImgTemp = listaVentanillas.enviarImpresion().cliente.totImg;
                            salaDeEspera.insertarClienteEspera(idTemp, nombreTemp, totImgTemp);
                            
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
                    System.out.println("\n............................. Cola impresora Color ..............................");
                    System.out.println(colaImpresionColor.verColaImpColor());

                    //área impresión de imagenes que se encuentran en sus respectivas colas
                    if (colaImpresionBW.verTamanioCola() > 0) {
                        System.out.println("\n>>>>>>>>> Imprimiendo imagen en Blanco y negro");
                        TnodoClienteP temporal = colaImpresionBW.sacarImpresionCola();

                        System.out.println(temporal.nombre + " >> " + temporal.tipoImg);
                        //entrega de imagen a cliente por medio de id
                        salaDeEspera.entregarImpCliente(temporal.id, temporal.tipoImg);
                    }

                    if (colaImpresionColor.verTamanioCola() > 0) {
                        pasosColor++;
                        if (pasosColor > 1) {
                            System.out.println("\n>>>>>>>>> Imprimiendo imagen a Color");
                            TnodoClienteP temporal2 = colaImpresionColor.sacarImpresionCola();

                            System.out.println(temporal2.nombre + " >> " + temporal2.tipoImg);
                            //entrega de imagen a cliente por medio de id
                            salaDeEspera.entregarImpCliente(temporal2.id, temporal2.tipoImg);
                            //System.out.println("Pasos color " + pasosColor);
                            pasosColor = 0;
                        }
                    }
                    pasoEjecutado = true;
                    System.out.println("\n-----------------------------------> PASO FINALIZADO <-----------------------------------\n");
                } else {
                    System.out.println("################## Verificar si se cargaron datos de los clientes y de las ventanillas ###################");
                }
            } else if (op == 3) {
                //Creación de archivos dot
                if (listaClientes.verCantidadClientes() > 0) {
                    //generar dot y png
                    generarDot("Cola de recepción", listaClientes.datosClientesGrafo());
                }
                
                if (colaImpresionBW.verTamanioCola() > 0) {
                    generarDot("Cola de impresora BW", colaImpresionBW.datosImpresionesGrafo());
                }else{
                    System.out.println("\n################## No hay imagenes en la cola de la impresora BW por lo tanto no se creo imagen de la estructura");
                }
                if (colaImpresionColor.verTamanioCola() > 0) {
                    generarDot("Cola de impresora Color", colaImpresionColor.datosImpresionesGrafo());
                }else{
                    System.out.println("\n################## No hay imagenes en la cola de la impresora COLOR por lo tanto no se creo imagen de la estructura");
                }
                if(salaDeEspera.verTamListaCircularDobleEspera() > 0){
                    generarDot("Sala de espera", salaDeEspera.datosDotEspera());
                }else{
                    System.out.println("\n################## No hay clientes en la sala de espera");
                }

            } else if (op == 5) {
                System.out.println("-----------------------------------------------------\n\tEstudiante: José Ernesto Pajoc Raymundo");
                System.out.println("-----------------------------------------------------\n\tCarné: 201115455");
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

    public static void generarDot(String edd, String contenidoDot) {
        try {
            PrintWriter escribir = new PrintWriter("grafos/" + edd + ".dot", "UTF-8");
            escribir.println("digraph G { \n node[shape=box]; \n");
            escribir.println(contenidoDot);
            escribir.println("rankdir=LR; \n }");
            escribir.close();
            System.out.println("Creado el archivo DOT para " + edd);
            generarGrafos(edd);
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo DOT de " + edd);
        }
    }

    public static void generarGrafos(String edd) {
        try {
            //construcción del comando
            ProcessBuilder procesoSistema = new ProcessBuilder("dot", "-Tpng", "-o", "grafos/" + edd + ".png", "grafos/" + edd + ".dot");
            procesoSistema.redirectErrorStream(true);   //retorna error del proceso construido
            //Ejecutar comando
            procesoSistema.start();
            System.out.println("Grafo creado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

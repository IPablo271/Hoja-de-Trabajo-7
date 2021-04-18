//Universidad del valle de Guatemala
//Nombre: Pablo Daniel Gonzalez Ramos
//Carnet: 20362
//Clase main para la implementación de la hoja de trabajo 7.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
public class main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> primeradivision=new ArrayList<String>();
        ArrayList<String[]> segundadivision=new ArrayList<>();
        Scanner scan2 = new Scanner(System.in);
        ArrayList<HashMap> espanol=new ArrayList<>();
        ArrayList<HashMap> ingles=new ArrayList<>();
        ArrayList<HashMap> frances=new ArrayList<>();
        ArrayList<String> palabras1=new ArrayList<>();
        ArrayList<String> palabras2=new ArrayList<>();
        ArrayList<String> palabras3=new ArrayList<>();
        ArrayList<ArrayList> listaspalabras=new ArrayList<>();
        ArrayList<ArrayList<HashMap>> listafinal=new ArrayList<>();
        ArrayList<ArrayList<HashMap>> listafinal2=new ArrayList<>();
        ArrayList<ArrayList<HashMap>> listafinal3=new ArrayList<>();
        ArrayList<Association> ListaFinalset=new ArrayList<>();
        ArrayList<Association> ListaFinalespanol=new ArrayList<>();
        ArrayList<Association> ListaFinalfrances=new ArrayList<>();
        ArrayList<Association> EspanolIngles=new ArrayList<>();
        ArrayList<Association> EspanolFrances=new ArrayList<>();
        ArrayList<Association> InglesEspanol=new ArrayList<>();
        ArrayList<Association> Inglesfrances=new ArrayList<>();
        ArrayList<Association> FrancesEspanol=new ArrayList<>();
        ArrayList<Association> FrancesIngles=new ArrayList<>();
        //Listas que ayudan a la manipulacion de los datos que entran a lo largo del programa
        File file=new File("palabras.txt");
        File file2=new File("oracion.txt");
        Scanner scan3=new Scanner(file2);//Se recibe el primer archivo que contiene las palabras
        Scanner scan=new Scanner(file);//Se recibe el segundo archivo que contiene las
        String filecontent="";
        while(scan.hasNext()){
            filecontent=filecontent.concat(scan.nextLine()+"\n");
        }

        String[] arr=filecontent.split("\n");//Dividir el primer archivo en una lista donde el parametro de division es cada linea
        for(int i=0;i<arr.length;i++){
            String a=arr[i];
            primeradivision.add(a);
        }
        for(int c=0;c<primeradivision.size();c++){//Dividir cada en linea en las 3 palabras que contiene el archivo.
            String a=primeradivision.get(c);
            String[] arr2=a.split(",");
            segundadivision.add(arr2);
        }
        for(int i=0;i<segundadivision.size();i++){
            String sublista[]=segundadivision.get(i);//Crear una sublista
            String uno=sublista[0];//Agregar la primera palabra a
            String dos=sublista[1];
            String tres=sublista[2];
            palabras1.add(uno);
            palabras2.add(dos);
            palabras3.add(tres);
            //Crear las difrentes combinaciones de palabras que pueden existir
            HashMap<String,String> mapa1=new HashMap<>();
            HashMap<String,String> mapa12=new HashMap<>();

            Association b=new Association(uno,dos);
            Association bc=new Association(uno,tres);
            Association espanoli=new Association(dos,uno);
            Association espanolf=new Association(dos,tres);
            Association franceespanol=new Association(tres,uno);
            Association franceesingles=new Association(tres,dos);


            mapa1.put(uno,dos);
            mapa12.put(uno,tres);
            ingles.add(mapa1);
            ingles.add(mapa12);
            HashMap<String,String> mapa2=new HashMap<>();
            HashMap<String,String> mapa22=new HashMap<>();
            mapa2.put(dos,uno);
            mapa22.put(dos,tres);
            espanol.add(mapa2);
            espanol.add(mapa22);
            HashMap<String,String> mapa3=new HashMap<>();
            HashMap<String,String> mapa32=new HashMap<>();
            mapa3.put(tres,uno);
            mapa32.put(tres,uno);
            frances.add(mapa3);
            frances.add(mapa32);
            InglesEspanol.add(b);
            Inglesfrances.add(bc);
            EspanolIngles.add(espanoli);
            EspanolFrances.add(espanolf);
            FrancesEspanol.add(franceespanol);
            FrancesIngles.add(franceesingles);

        }
        for(int i=0;i<ingles.size();i=i+2){
           HashMap c =ingles.get(i);
           HashMap d=ingles.get(i+1);
           ArrayList<HashMap> listainterna=new ArrayList<>();
           listainterna.add(c);
           listainterna.add(d);
           listafinal.add(listainterna);

        }
        for(int i=0;i<espanol.size();i=i+2){
            HashMap c =espanol.get(i);
            HashMap d=espanol.get(i+1);
            ArrayList<HashMap> listainterna=new ArrayList<>();
            listainterna.add(c);
            listainterna.add(d);
            listafinal2.add(listainterna);

        }
        for(int i=0;i<frances.size();i=i+2){
            HashMap c =frances.get(i);
            HashMap d=frances.get(i+1);
            ArrayList<HashMap> listainterna=new ArrayList<>();
            listainterna.add(c);
            listainterna.add(d);
            listafinal3.add(listainterna);
        }
        for(int i=0;i<palabras1.size();i++){
            String a=palabras1.get(i);
            ArrayList<HashMap> maj=listafinal.get(i);
            Association b=new Association(a,maj);
            ListaFinalset.add(b);
        }
        for(int i=0;i<palabras2.size();i++){
            String a=palabras2.get(i);
            ArrayList<HashMap> maj=listafinal2.get(i);
            Association b=new Association(a,maj);
            ListaFinalespanol.add(b);
        }
        for(int i=0;i<palabras3.size();i++){
            String a=palabras3.get(i);
            ArrayList<HashMap> maj=listafinal3.get(i);
            Association b=new Association(a,maj);
            ListaFinalfrances.add(b);
        }
        
        //Creae los arboles binarios para su uso
        BinaryTree<Association> arbolIngles=new BinaryTree<Association>(ListaFinalset.get(0));
        BinaryTree<Association> arbolespanol=new BinaryTree<Association>(ListaFinalespanol.get(0));
        BinaryTree<Association> arbolfrances=new BinaryTree<Association>(ListaFinalfrances.get(0));
        //Agregar las difrentes palabras a los arboles binarios
        for (int n = 0; n < ListaFinalset.size(); n++) {
            if ((n+1) < ListaFinalset.size()) {
                BinaryTree<Association> btSiguiente = new BinaryTree<>(ListaFinalset.get(n+1));
                BinaryTree.agregar(arbolIngles, btSiguiente);
            }
        }
        System.out.print("\n Este es el arbol binario in order: "+arbolIngles.inOrder(arbolIngles));//Print del arbol binario in order
        String contenido="";
        while(scan3.hasNext()){
            contenido=contenido.concat(scan3.nextLine()+"\n");
        }
        System.out.print(contenido);
        ArrayList<String> subdivision=new ArrayList<>();
        String[] arr2=contenido.split(" ");//Dividir el archivo 2 por cada espacio que exista
        for(int i=0;i<arr2.length;i++){
            String a=arr2[i];
            String b=a.toLowerCase();
            subdivision.add(b);
        }
        boolean rungeneral=true;

        while(rungeneral){//While para la traduccion
            //Menu de ingreso al programa
            System.out.print("\nBienvenido al programa");
            System.out.print("\nIngrese la opcion que desee");
            System.out.print("\nEn que idioma esta el archivo que desea traducir");
            System.out.print("\n1.Espanol");
            System.out.print("\n2.Ingles");
            System.out.print("\n3.Frances");
            System.out.print("\n4.Terminar el programa");
            System.out.print("\nSeleccione su opcion: ");
            int a=scan2.nextInt();
            if(a==1){//OPCION 1
                boolean subrun=true;
                while(subrun){
                    //Submenu para verificar a que idioma quiere traducir
                    System.out.print("\nA que idioma quiere traducir su archivo");
                    System.out.print("\n1. Ingles");
                    System.out.print("\n2. Frances");
                    System.out.print("\nIngrese su opcion: ");
                    int h=scan2.nextInt();
                    if(h==1){
                        //Se crea el arbol binario y luego se busca las palabras individualmente para luego producir oracion traducida
                        System.out.print("Traduccion espanol ingles");
                        BinaryTree<Association> Arbol1=new BinaryTree<Association>(EspanolIngles.get(0));
                        for (int n = 0; n < EspanolIngles.size(); n++) {
                            if ((n+1) < EspanolIngles.size()) {
                                BinaryTree<Association> btSiguiente = new BinaryTree<>(EspanolIngles.get(n+1));
                                BinaryTree.agregar(Arbol1,btSiguiente);
                            }
                        }
                        String concatenado="";
                        for( int i=0;i<subdivision.size();i++){
                            String b=subdivision.get(i);
                            String palabra=Arbol1.buscar(b);
                            if(palabra==null){
                                concatenado=concatenado+"* "+b+" *";
                            }
                            else{
                                concatenado=concatenado+" * "+palabra+" * ";
                            }
                        }
                        System.out.print("\nLa oracion traducida es: "+concatenado);
                        subrun=false;
                    }
                    if(h==2){
                        //Se crea el arbol binario y luego se busca las palabras individualmente para luego producir oracion traducida
                        System.out.print("Traduccion espanol frances");
                        BinaryTree<Association> Arbol1=new BinaryTree<Association>(EspanolFrances.get(0));
                        for (int n = 0; n < EspanolFrances.size(); n++) {
                            if ((n+1) < EspanolFrances.size()) {
                                BinaryTree<Association> btSiguiente = new BinaryTree<>(EspanolFrances.get(n+1));
                                BinaryTree.agregar(Arbol1,btSiguiente);
                            }
                        }
                        String concatenado="";
                        for( int i=0;i<subdivision.size();i++){
                            String b=subdivision.get(i);
                            String palabra=Arbol1.buscar(b);
                            if(palabra==null){
                                concatenado=concatenado+"* "+b+" *";
                            }
                            else{
                                concatenado=concatenado+" * "+palabra+" * ";
                            }
                        }
                        System.out.print("\nLa oracion traducida es: "+concatenado);
                        subrun=false;
                    }
                    else{
                        System.out.print("Ingrese una opcion valida");
                    }
                }

            }
            if(a==2){
                boolean subrun=true;
                while(subrun){
                    //Sub menu para verificar a que idioma quiere traducir
                    System.out.print("\nA que idioma quiere traducir su archivo");
                    System.out.print("\n1. Espanol");
                    System.out.print("\n2. Frances");
                    System.out.print("\nIngrese su opcion: ");
                    int h=scan2.nextInt();
                    if(h==1){
                        //Se crea el arbol binario y luego se busca las palabras individualmente para luego producir oracion traducida
                        System.out.print("Traduccion ingles espanol");
                        BinaryTree<Association> Arbol1=new BinaryTree<Association>(InglesEspanol.get(0));
                        for (int n = 0; n < InglesEspanol.size(); n++) {
                            if ((n+1) < InglesEspanol.size()) {
                                BinaryTree<Association> btSiguiente = new BinaryTree<>(InglesEspanol.get(n+1));
                                BinaryTree.agregar(Arbol1,btSiguiente);
                            }
                        }
                        String concatenado="";
                        for( int i=0;i<subdivision.size();i++){
                            String b=subdivision.get(i);
                            String palabra=Arbol1.buscar(b);
                            if(palabra==null){
                                concatenado=concatenado+"* "+b+" *";
                            }
                            else{
                                concatenado=concatenado+" * "+palabra+" * ";
                            }
                        }
                        System.out.print("\nLa oracion traducida es: "+concatenado);
                        subrun=false;
                    }
                    if(h==2){
                        //Se crea el arbol binario y luego se busca las palabras individualmente para luego producir oracion traducida
                        BinaryTree<Association> Arbol1=new BinaryTree<Association>(Inglesfrances.get(0));
                        for (int n = 0; n < Inglesfrances.size(); n++) {
                            if ((n+1) < Inglesfrances.size()) {
                                BinaryTree<Association> btSiguiente = new BinaryTree<>(Inglesfrances.get(n+1));
                                BinaryTree.agregar(Arbol1,btSiguiente);
                            }
                        }
                        String concatenado="";
                        for( int i=0;i<subdivision.size();i++){
                            String b=subdivision.get(i);
                            String palabra=Arbol1.buscar(b);
                            if(palabra==null){
                                concatenado=concatenado+"* "+b+" *";
                            }
                            else{
                                concatenado=concatenado+" * "+palabra+" * ";
                            }
                        }
                        System.out.print("\nLa oracion traducida es: "+concatenado);
                        subrun=false;
                    }
                    else{
                        System.out.print("Ingrese una opcion valida");
                    }
                }

            }
            if(a==3){
                boolean subrun=true;
                while(subrun){
                    //Submenu para la verificar a que idioma quiere traudcir.
                    System.out.print("\nA que idioma quiere traducir su archivo");
                    System.out.print("\n1. Espanol");
                    System.out.print("\n2. Ingles");
                    System.out.print("\nIngrese su opcion: ");
                    int h=scan2.nextInt();
                    if(h==1){
                        //Se crea el arbol binario y luego se busca las palabras individualmente para luego producir oracion traducida
                        System.out.print("Traduccion frances espanol");
                        BinaryTree<Association> Arbol1=new BinaryTree<Association>(FrancesEspanol.get(0));
                        for (int n = 0; n < FrancesEspanol.size(); n++) {
                            if ((n+1) < FrancesEspanol.size()) {
                                BinaryTree<Association> btSiguiente = new BinaryTree<>(FrancesEspanol.get(n+1));
                                BinaryTree.agregar(Arbol1,btSiguiente);
                            }
                        }
                        String concatenado="";
                        for( int i=0;i<subdivision.size();i++){
                            String b=subdivision.get(i);
                            String palabra=Arbol1.buscar(b);
                            if(palabra==null){
                                concatenado=concatenado+"* "+b+" *";
                            }
                            else{
                                concatenado=concatenado+" * "+palabra+" * ";
                            }
                        }
                        System.out.print("\nLa oracion traducida es: "+concatenado);
                        subrun=false;
                    }
                    if(h==2){
                        //Se crea el arbol binario y luego se busca las palabras individualmente para luego producir oracion traducida
                        System.out.print("Traduccion frances ingles");
                        BinaryTree<Association> Arbol1=new BinaryTree<Association>(FrancesIngles.get(0));
                        for (int n = 0; n < FrancesIngles.size(); n++) {
                            if ((n+1) < FrancesIngles.size()) {
                                BinaryTree<Association> btSiguiente = new BinaryTree<>(FrancesIngles.get(n+1));
                                BinaryTree.agregar(Arbol1,btSiguiente);
                            }
                        }
                        String concatenado="";
                        for( int i=0;i<subdivision.size();i++){
                            String b=subdivision.get(i);
                            String palabra=Arbol1.buscar(b);
                            if(palabra==null){
                                concatenado=concatenado+"* "+b+" *";
                            }
                            else{
                                concatenado=concatenado+" * "+palabra+" * ";
                            }
                        }
                        System.out.print("\nLa oracion traducida es: "+concatenado);
                        subrun=false;
                    }
                    else{
                        System.out.print("Ingrese una opcion valida");
                    }
                }

            }
            if(a==4){
                //Opcion para salir del programa
                System.out.print("Saliendo del programa");
                break;
            }
            else{
                //Opcion para manejo de errores
                System.out.print("Ingrese una opcion valida");
            }
        }

    }
}

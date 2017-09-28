package opennlpdemo;


import java.util.Scanner;

public class Main {

    public static void main(String[]args){
        OpenNLPCategorizer categorizer=new OpenNLPCategorizer();
        categorizer.trainModel();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String str = scanner.nextLine();
            if ("quit".equals(str)) {
                break;
            }
            categorizer.classify(str);
        }
    }
}
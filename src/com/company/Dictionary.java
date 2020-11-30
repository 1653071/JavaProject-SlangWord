package com.company;
import java.lang.String;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
public class Dictionary {
    private HashMap<String , ArrayList<String>> data= new HashMap<String,ArrayList<String>>();
    public void readSlang() throws IOException {
        this.data = new HashMap<String,ArrayList<String>>();

        FileReader fr = new FileReader("D:\\1653071_SlangDictionary\\slang.txt");
        BufferedReader br = new BufferedReader(fr);
        String str;

            while (true) {

                str = br.readLine();
                if(str==null){
                    break;
                }
                String[] s1;
                ArrayList data1 = new ArrayList<>();
                if (str.indexOf("|") != -1) {
                    s1 = str.split("[`|]");
                    data1.add(s1[1]);
                    data1.add(s1[2]);
                    this.data.put(s1[0], data1);
                } else if (str.indexOf("`") != -1)  {

                    s1 = str.split("[`]");
                    data1.add(s1[1]);
                    data1.add("Dont have 2 define");
                    this.data.put(s1[0], data1);
                }
                else {

                    data1.add("dfdsf");
                    data1.add("Dont have 2 define");
                    this.data.put(str, data1);
                }

            }

        System.out.println("HashMap1: " + this.data);





    }


    public String FindByKeyword(String word){

        //Iterator<Map.Entry<String, ArrayList<String>>> iterator = this.data.entrySet().iterator();
        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            String str = entry.getKey();

            if(str.contentEquals(word)){
                return entry.getValue().get(0);

            }

        }
        return "Not have word in Dictionary";


    }
    public void FindByDefinition (String word){
        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            String str = entry.getValue().get(0);
            String str1 = entry.getValue().get(1);
            if(str.contains(word) || str1.contains(word)){
                String str3= entry.getKey();
                System.out.println(str3 +"-"+ str );

            }

        }
        System.out.println("Not have word in Dictionary");
    }



    public static void main(String args[]) throws IOException {
        Dictionary d = new Dictionary();
        d.readSlang();


        int choiceNumber;
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.println("1. Find definition n=by slang word");
            System.out.println("2. Xem");
            System.out.println("3. Thoát");

            do {
                System.out.println("Bấm số để chọn (1/2/3): ");
                choiceNumber = scanner.nextInt();
            } while ((choiceNumber < 1) || (choiceNumber > 3));

            switch (choiceNumber) {
                case 1:
                    System.out.println("Input word:");
                    Scanner sc= new Scanner(System.in);
                    String word = sc.nextLine();
                    String str;
                    str=d.FindByKeyword(word);
                    System.out.println("Define: " + str);
                    break;
                case 2:
                    System.out.println("Input word:");
                    Scanner sc1= new Scanner(System.in);
                    String word1 = sc1.nextLine();
                    String str1;
                    d.FindByDefinition(word1);

                    break;

                case 3:
                    System.out.println("Bạn chọn chức năng thoát! Tạm biệt!");
                    System.exit(0); // thoát chương trình
                    break;
            }
        }
    }



    }


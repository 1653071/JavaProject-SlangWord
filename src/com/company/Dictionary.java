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
    public void readSlang(String filename) throws IOException {
        this.data = new HashMap<String,ArrayList<String>>();

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String str;
            while (true) {

                str = br.readLine();
                if(str==null){
                    break;
                }
                String[] s1;
                //Integer a =0;

                ArrayList data1 = new ArrayList<>();
               // for(int i=0; i<=str.length()-1; i++) {
                //    char u= str.charAt(i);
                //    String s=Character.toString(u);
                //    if(s=="|"){
               //     break;
               // }

                if ( str.indexOf("`") != -1 ) {


                    s1 = str.split("[`]");

                    if (s1[1].indexOf("|") != -1 ) {
                        String[] s2 = s1[1].split("[|]");

                        for (int i = 0; i < s2.length; i++) {
                            data1.add(s2[i]);

                        }
                        this.data.put(s1[0], data1);
                    }
                    else{
                        data1.add(s1[1]);
                        data1.add("Dont have 2 define");
                        this.data.put(s1[0], data1);
                    }
                }
                else {

                    data1.add("ErrorValue");
                    data1.add("ErrorValue");
                    this.data.put(str, data1);
                }

            }

        System.out.println("HashMap1: " + this.data);





    }



    public String FindByKeyword(String word){
        String word1= word.toUpperCase();
        //Iterator<Map.Entry<String, ArrayList<String>>> iterator = this.data.entrySet().iterator();
        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            String str = entry.getKey();

            if(str.equals(word1)){
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
    public void History(String word) throws IOException {
        File file = new File("D:\\1653071_SlangDictionary\\history.txt");
        BufferedWriter filewrite = new BufferedWriter(new FileWriter(file));
        filewrite.write(word);
        filewrite.close();
    }
    public void DeleteWord(String word)  {
        try {

            File file = new File("D:\\1653071_SlangDictionary\\file.txt");
            file.createNewFile();
            BufferedWriter filewrite = new BufferedWriter(new FileWriter(file));
            String deleteword = "";
            for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
                String str = entry.getKey();
                if (str.contentEquals(word)) {
                    deleteword= entry.getKey();
                    break;
                }
            }
            if (deleteword.isEmpty()) {
                System.out.print("Not have word in dictionary");
            }
            else{
            this.data.remove(deleteword);

                    try {
                        for (Map.Entry<String, ArrayList<String>> entry1 : this.data.entrySet()) {
                            if (entry1.getValue().get(1).equals("Dont have 2 define")) {
                                filewrite.write(entry1.getKey() + "`" + entry1.getValue().get(0));
                                filewrite.newLine();
                            } else if (entry1.getValue().get(1).equals("ErrorValue")) {
                                filewrite.write(entry1.getKey());
                                filewrite.newLine();
                            }else{
                                filewrite.write(entry1.getKey() + "`" + entry1.getValue().get(0) + "|" + entry1.getValue().get(1));
                                filewrite.newLine();
                            }

                        }

                        System.out.print("Delete successful");
                        filewrite.close();
                        File fileslang= new File("D:\\1653071_SlangDictionary\\slang.txt");
                        if(fileslang.delete()){
                            System.out.println(file.getName() + " is deleted!");
                        }
                        file.renameTo(fileslang);


                       // file.renameTo(file);

                    }catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
        } catch (IOException e) {
            System.out.println("Error");

        }

    }
    public void RandomSlang(){

    }
public void AddNewWord(String slang,String definition,String definition2) throws IOException {
        Boolean haveword = false ;
        String slang1= slang.toUpperCase();
    for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
        String str1= entry.getKey();
        if (str1.equals(slang)){
            haveword=true;
            break;
        }
    }
    if (haveword==true){
        System.out.println("Word already in dictionary");
    }
    else{

        ArrayList data1 = new ArrayList<>();
        data1.add(definition);
        data1.add(definition2);
        this.data.put(slang1,data1);
        File file = new File("D:\\1653071_SlangDictionary\\slang.txt");
        BufferedWriter filewrite = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
        if (definition2 == "Dont have 2 define")  {
            filewrite.write(slang1 + "`" + definition);

        } else {
            filewrite.write(slang1 + "`" + definition + "|" + definition2);
            filewrite.newLine();
        }
        System.out.println("Sucessful");

    }
}
public void EditDefinition (String word, String newdefinition){
    boolean haveword = false ;
    String str1="";
    String str2="";
    String str3="";
    for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
        str1= entry.getKey();
        if (str1.equals(word)){
            str2= entry.getValue().get(0);
            str3= entry.getValue().get(1);

            haveword=true;
            break;
        }
    }
    if (haveword==true){
        ArrayList data1 = new ArrayList<>();
        data1.add(str2);
        data1.add(str3);
        ArrayList data2 = new ArrayList<>();
        data2.add(newdefinition);
        data2.add(str3);
        this.data.replace(word,data1,data2);

    }
    else {
        System.out.println("Word already in dictionary");
    }
}
public void EditSlangword(String word) throws IOException {
    boolean haveword = false;
    String str1 = "";
    String str2 = "";
    String str3 = "";
    for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
        str1 = entry.getKey();
        if (str1.equals(word)) {
            str2 = entry.getValue().get(0);
            str3 = entry.getValue().get(0);
            haveword = true;
            break;
        }
    }
    if (haveword == true) {
        this.data.remove(str1);
        ArrayList data1 = new ArrayList<>();
        data1.add(str2);
        data1.add(str3);
        this.data.put(word, data1);
        File file = new File("D:\\1653071_SlangDictionary\\file.txt");
        file.createNewFile();
        BufferedWriter filewrite = new BufferedWriter(new FileWriter(file));
        for (Map.Entry<String, ArrayList<String>> entry1 : this.data.entrySet()) {
            if (entry1.getValue().get(1).equals("Dont have 2 define")) {
                filewrite.write(entry1.getKey() + "`" + entry1.getValue().get(0));
                filewrite.newLine();
            } else if (entry1.getValue().get(1).equals("ErrorValue")) {
                filewrite.write(entry1.getKey());
                filewrite.newLine();
            } else {
                filewrite.write(entry1.getKey() + "`" + entry1.getValue().get(0) + "|" + entry1.getValue().get(1));
                filewrite.newLine();
            }
        }
        filewrite.close();
        File fileslang = new File("D:\\1653071_SlangDictionary\\slang.txt");
        file.renameTo(fileslang);


    }
}
public void resetDefaultSlangword() throws IOException {
    File filereset = new File("D:\\1653071_SlangDictionary\\filereset.txt");
    File slangDefault= new File("D:\\1653071_SlangDictionary\\slangdefault.txt");
    filereset.createNewFile();
    FileReader fr = new FileReader(slangDefault);
    BufferedReader rr = new BufferedReader(fr);
    FileWriter fw =new FileWriter(filereset);
    BufferedWriter ww= new BufferedWriter(fw);
    String str ;
        while ((str = rr.readLine()) != null) {
            ww.write(str);
            ww.newLine();

        }
        ww.close();
        rr.close();
    File fileslang= new File("D:\\1653071_SlangDictionary\\slang.txt");
    if(fileslang.delete()){
        System.out.println(fileslang.getName() + " is deleted!");
    }
    filereset.renameTo(fileslang);
}
public void randomWord(){
     Random generator= new Random();
     Object[] word = this.data.keySet().toArray();
     Object random= word[generator.nextInt(word.length)];
     System.out.print("Slang word random: " + random.toString());
}
public void QuizFindDefinition (){
    Random generator= new Random();
    Object[] word = this.data.keySet().toArray();
    Object random= word[generator.nextInt(word.length)];
    String slang =random.toString();
    String answer=this.data.get(slang).get(1);
    Object[] word1 = this.data.values().toArray();
    for (int i=0;i<=2;i++){
        Object random1= word[generator.nextInt(word.length)];
    }
    }

    public static void main(String args[]) throws IOException {
        Dictionary d = new Dictionary();
        d.readSlang("D:\\1653071_SlangDictionary\\slangDefault.txt");


        int choiceNumber;
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.println("1. Find definition n=by slang word");
            System.out.println("2. Xem");
            System.out.println("3. ");


            do {
                System.out.println("Bấm số để chọn (1/2/3/4/5/6/7/8/9): ");
                choiceNumber = scanner.nextInt();
            } while ((choiceNumber < 1) || (choiceNumber > 10));

            switch (choiceNumber) {
                case 1:
                    System.out.println("Input word:");
                    Scanner sc= new Scanner(System.in);
                    String word = sc.nextLine();
                    d.History(word);
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
                case 4:
                    String definition2;
                    System.out.println("---Add new word---");
                    Scanner sc2 = new Scanner(System.in);
                    System.out.print("Input slang word:");
                    String slangword=sc2.nextLine();
                    System.out.print("Input definition:");
                    String definition= sc2.nextLine();
                    String b;



                    System.out.println("Do you want add definition for definition ?? Please input yes to define or press enter to skip :");
                    Scanner sc3 = new Scanner(System.in);
                    b= sc3.nextLine();



                    if (b.equals("yes"))
                    {
                        System.out.print("Define a definition: ");
                        definition2 = sc2.nextLine();
                    }
                    else{
                        definition2="Dont have 2 define";
                    }
                    d.AddNewWord(slangword,definition,definition2);
                    break;
                case 5:
                    d.DeleteWord("SCO");
                    break;
                case 6:
                    d.resetDefaultSlangword();
                    break;
                case 8:
                    d.randomWord();
            }
        }
    }



    }


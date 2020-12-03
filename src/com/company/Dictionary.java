package com.company;
import java.awt.event.KeyEvent;
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
        //Iterator<Map.Entry<String, ArrayList<String>>> iterator = this.data.entrySet().iterator();
        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            String str = entry.getKey();

            if(str.equalsIgnoreCase(word )){
                return entry.getValue().get(0);

            }

        }
        return "Not have word in Dictionary";


    }
    public void FindByDefinition (String word){
        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            ArrayList<String> data= entry.getValue();

            for (int i=0;i< data.size();i++) {
                String def=entry.getValue().get(i);
                if(def.contains(word) ){
                    String str3= entry.getKey();
                    System.out.println(entry.getKey() +"-" +entry.getValue().get(i)+ "have definition contain" + word );
                    break;
                }
            }
        }

    }
    public void History(String word) throws IOException {
        File file = new File("D:\\1653071_SlangDictionary\\history.txt");
        BufferedWriter filewrite = new BufferedWriter(new FileWriter(file,true));
        filewrite.newLine();
        filewrite.write(word);
        filewrite.close();
    }
    public void ReadHistory() throws IOException {
        File file = new File("D:\\1653071_SlangDictionary\\history.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while (true) {

            str = br.readLine();
            System.out.print(str + "    " );
            if (str == null) {
                break;
            }
        }
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

                                filewrite.write(entry1.getKey() + "`");

                                for (int i=0;i< entry1.getValue().size();i++) {
                                    filewrite.write( entry1.getValue().get(i) + "| ");
                                }
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
public void AddNewWord(String slang,ArrayList<String> d) throws IOException {
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


        File file = new File("D:\\1653071_SlangDictionary\\slang.txt");
        BufferedWriter filewrite = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
        filewrite.newLine();
        filewrite.write(slang1 + "`" );
        for (int i=0; i<d.size();i++) {

            filewrite.write("|" + d.get(i));

        }


        System.out.println("Sucessful");

    }
}
public void EditDefinition (String word, String newdefinition) throws IOException {
    boolean haveword = false ;
    ArrayList data = new ArrayList<>();
    String str1="";
    for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
        str1= entry.getKey();
        if (str1.equals(word)){


            for(int i=0;i<entry.getValue().size();i++)
            {
                data.add(entry.getValue().get(i));
            }
            haveword=true;
            break;
        }
    }
    if (haveword==true) {
        ArrayList data2 = new ArrayList<>();
        ArrayList Edit = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Input your edition or enter when don't input anything to next");
            System.out.println("You want chance definition " + i + data.get(i) + "of" + word + ":");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();

            if (!str.isEmpty()) {
                Edit.add((str));

            } else {
                Edit.add(data.get(i));
            }
        }

        this.data.replace(word, data2, Edit);
        File file = new File("D:\\1653071_SlangDictionary\\file.txt");
        file.createNewFile();
        BufferedWriter filewrite = new BufferedWriter(new FileWriter(file));

        try {
            for (Map.Entry<String, ArrayList<String>> entry1 : this.data.entrySet()) {
                if (entry1.getValue().get(1).equals("Dont have 2 define")) {
                    filewrite.write(entry1.getKey() + "`" + entry1.getValue().get(0));
                    filewrite.newLine();
                } else if (entry1.getValue().get(1).equals("ErrorValue")) {
                    filewrite.write(entry1.getKey());
                    filewrite.newLine();
                } else {

                    filewrite.write(entry1.getKey() + "`");

                    for (int i = 0; i < entry1.getValue().size(); i++) {
                        filewrite.write(entry1.getValue().get(i) + "| ");
                    }
                    filewrite.newLine();
                }

            }
            filewrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    else {
        System.out.println("Word don't already in dictionary");
    }
}
public void EditSlangword(String word) throws IOException {
    boolean haveword = false;
    String str1 = "";

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
        System.out.println("----------------------***----------------------");
        System.out.println("-------------Welcome my Dictionary-------------");
        System.out.println("        --------Choose fuction-------");
        for (;;) {
            System.out.println(" ");
            System.out.println("1. Find Definition");
            System.out.println("2. Find Slang word relate to definition");
            System.out.println("3. History");
            System.out.println("4. Add new Slang word");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Reset");
            System.out.println("8. Random");



            do {
                System.out.println("Input number to choose (1/2/3/4/5/6/7/8): ");
                choiceNumber = scanner.nextInt();
            } while ((choiceNumber < 1) || (choiceNumber > 8));

            switch (choiceNumber) {
                case 1:
                    String word;
                    System.out.println("-------------Search Definition-------------");
                    do {
                        System.out.println("Input word or press enter to out:");
                        Scanner sc = new Scanner(System.in);
                        word = sc.nextLine();
                        d.History(word);
                        String str;
                        str = d.FindByKeyword(word);
                        System.out.println("Define of "+ word + " : " + str);
                    }while (!word.isEmpty());
                    System.out.println("Exit function search. ");
                    break;
                case 2:
                    String word1;

                    System.out.println("-------------Search Slang Word-------------");
                    do {

                    System.out.println("Input word or press enter to out:");
                    Scanner sc1= new Scanner(System.in);
                    word1 = sc1.nextLine();
                    String str1;
                    d.FindByDefinition(word1);
                    }while (!word1.isEmpty());
                    break;

                case 3:
                    System.out.println("-------------See history-------------");
                    d.ReadHistory();
                    break;
                case 4:
                    String definition2;
                    System.out.println("---Add new word---");
                    Scanner sc2 = new Scanner(System.in);
                    System.out.print("Input slang word:");
                    String slangword=sc2.nextLine();
                    ArrayList definition = new ArrayList<>();
                    System.out.println("Input definition:");
                    Scanner sc = new Scanner(System.in);
                    word = sc.nextLine();
                    definition.add(word);
                    do {
                        System.out.println("Have another definition?? if not enter to end : ");
                    }while (!word.isEmpty());
                    d.AddNewWord(slangword,definition);
                case 5:

                    break;
                case 6:
                    System.out.println("---Delete word---");
                    do {
                        System.out.println("Input word to delete or press enter to out:");
                        Scanner sc1= new Scanner(System.in);
                        word1 = sc1.nextLine();
                        System.out.println("Are you sure ? yes or no : ");
                        String choose=sc1.nextLine();
                        if(choose.equals("yes")) {
                            d.DeleteWord(word1);
                        }
                        else{
                            System.out.println("Cancel");
                        }
                    }while (!word1.isEmpty());

                    break;
                case 7:
                    System.out.println("---Reset to default---");
                    Scanner sc1= new Scanner(System.in);
                    System.out.println("Are you sure ? yes or no : ");
                    String choose=sc1.nextLine();
                    if(choose.equals("yes")) {
                        d.resetDefaultSlangword();
                    }
                    else{
                        System.out.println("Cancel");
                    }

                    break;
                case 8:
                    System.out.println("---Random slang word---");
                    d.randomWord();
                case 9:
                    System.exit(0); // thoát chương trình
                    break;
            }
        }
    }



    }


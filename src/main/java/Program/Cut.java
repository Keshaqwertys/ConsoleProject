package Program;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Cut {

    private boolean flagC;

    private boolean flagW;

    private String outputNameFile;

    private String inputNameFile;

    private String range;

    private boolean flagOutput;

    private int startRange;
    private int endRange;
    private String[] dividedRange;
    private File outputFile;

    private File inputFile;

    public Cut(boolean flagC, boolean flagW, String outputNameFile, String inputNameFile, String range, boolean flagOutput) throws IOException {
        this.flagC = flagC;
        this.flagW = flagW;
        this.outputNameFile = outputNameFile;
        this.inputNameFile = inputNameFile;
        this.range = range;
        this.flagOutput = flagOutput;


        dividedRange = range.split("-");

        if (dividedRange.length == 1){
            dividedRange = normalizeIt(dividedRange);
        }

        while (!isCorrect(dividedRange)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введеный вами диапозон неккоректен.");
            System.out.print("Введите дипозон: ");
            range = reader.readLine();

            dividedRange = range.split("-");

            if (dividedRange.length == 1){
                dividedRange = normalizeIt(dividedRange);
            }
        }

        if (!dividedRange[0].equals("")){
            startRange = Integer.parseInt(dividedRange[0]);
        } else startRange = 0;
        if (!dividedRange[1].equals("")){
            endRange = Integer.parseInt(dividedRange[1]);
        } else endRange = -1; // -1 = start with begin or finish at the end

        outputFile = searchFile(outputNameFile, false);
        inputFile = searchFile(inputNameFile, true);



        while (inputFile == null){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите /dir, чтобы увидеть содержание корневой директории");
            System.out.print("Введите название входного файла: ");
            inputNameFile = reader.readLine();
            if (inputNameFile.equals("/dir")) {
                contentsOfDirectory();
            } else {
                inputFile = searchFile(inputNameFile, true);
            }
            //if (inputFile == null) System.out.println("Файл не найден");

            //if (inputFile == null) throw new IllegalArgumentException("Входной файл не существует.");
        }
    }

    private File searchFile(String nameFile, boolean flag) throws IOException {
        File dyrectory = new File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources");
        File[] dyrectoryList = dyrectory.listFiles();
        File result = null;

        for (int i = 0; i < dyrectoryList.length; i++){
            if (dyrectoryList[i].getName().equals(nameFile)) result = dyrectoryList[i];
        }

        if (result == null){
            if (flag){
                System.out.println("Входной файл в директории не найден");
            }
            if (!flag){
                result = new File(dyrectory + "\\" + outputNameFile + ".txt");
                if (result.createNewFile()){
                    System.out.println("Новый выходной файл создан");
                } //else {
                //    System.out.print("Выходной файл с таким именем уже существует");
                //}
            }
        }

        return result;
    }

    public void getSlice() throws IOException {
        //boolean result = false;
        String[] text;
        int rangeLegth;
        int count = 0;

        //FileReader fr = new FileReader(inputFile);
            //InputStreamReader fr = new InputStreamReader(new FileInputStream(inputFile), "windows-1251");
        InputStreamReader fr = new InputStreamReader(new FileInputStream(inputFile));
        //
        Scanner scan = new Scanner(fr);
        FileWriter fw = new FileWriter(outputFile);

        int rangeFrom = startRange;
        int rangeBefore = endRange;


        while (scan.hasNextLine()) {
            StringBuilder resultText = new StringBuilder();

            if (flagW){
                text = scan.nextLine().split(" +");

                if (endRange > text.length - 1) rangeBefore = text.length - 1;
                if (endRange == -1) rangeBefore = text.length - 1;

                for (int i = rangeFrom; i <= rangeBefore; i++){
                    resultText.append(text[i] + " ");
                }

                if (flagOutput){
                    System.out.println(resultText.toString());
                }else {
                    String slice = resultText.toString().trim();
                    fw.write(slice);

                    fw.write(System.lineSeparator());
                }

            } else if (flagC) {
                text = scan.nextLine().split("");
                if (endRange == -1) rangeBefore = text.length;
                rangeLegth = rangeBefore - rangeFrom + 1; //  +1
                count = rangeFrom;
                String symbol;

                while (rangeLegth > 0){
                    if (count >= text.length) break;
                    symbol = text[count];

                    if (!Objects.equals(symbol, " ")){
                        resultText.append(symbol);
                        rangeLegth--;
                    } else {
                        resultText.append(" ");
                    }
                    count++;
                }

                if (flagOutput){
                    System.out.println(resultText.toString());
                } else {
                    String slice = resultText.toString();
                    fw.write(slice);
                    fw.write(System.lineSeparator());
                }
            }
        }

        fw.close();
        fr.close();

        //return result;
    }

    private void contentsOfDirectory(){
        File mainDyrectory = new File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources");
        File[] mainDyrectoryList = mainDyrectory.listFiles();

        System.out.println("...Содержание корневой директории...");
        for (int indexOfFiles = 0; indexOfFiles < mainDyrectoryList.length; indexOfFiles++){
            File file = mainDyrectoryList[indexOfFiles];
            System.out.println(file.getName());
        }
        System.out.println("....................................");
    }

    public boolean isCorrect(String[] range){
        boolean result = true;
        if (range.length != 2) return false;

        String rangeOne = range[0];
        String rangeTwo = range[1];



        if (rangeOne.matches("\\w*\\D+\\w*")) return false;
        if (rangeTwo.matches("\\w*\\D+\\w*")) return false;


        if ((!rangeOne.equals("")) && (!rangeTwo.equals(""))){
            if (Integer.parseInt(rangeTwo) < Integer.parseInt(rangeOne)) return false;
        }
        return result;
    }

    public String[] normalizeIt(String[] dividedRange){
        String[] newDividedRange = new String[dividedRange.length + 1];
        for(int i = 0; i < dividedRange.length; i++){
            newDividedRange[i] = dividedRange[i];
        }
        newDividedRange[newDividedRange.length - 1] = "";
        return  newDividedRange;
    }


    public boolean assertEqualsFile(File firsFile, File secondFile) throws IOException {
        boolean result = true;

        if (firsFile == secondFile) return true;

        InputStreamReader frFirst = new InputStreamReader(new FileInputStream(firsFile));
        Scanner scanFirst = new Scanner(frFirst);
        InputStreamReader frSecond = new InputStreamReader(new FileInputStream(secondFile));
        Scanner scanSecond = new Scanner(frSecond);

        while (scanFirst.hasNextLine()){
            if (!scanSecond.hasNextLine()) return false;

            String textFirst = scanFirst.nextLine();
            String textSecond = scanSecond.nextLine();

            if (!textFirst.equals(textSecond)) return false;
        }

        if (scanSecond.hasNextLine()) return false;

        frFirst.close();
        frSecond.close();

        return result;
    }

    public File getOutputFile(){
        return outputFile;
    }


}

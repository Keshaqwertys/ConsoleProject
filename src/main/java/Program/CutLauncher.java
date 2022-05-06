package Program;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CutLauncher {

    @Option(name = "-c")
    private boolean flagC;

    @Option(name = "-w")
    private boolean flagW;

    @Option(name = "-o")
    private String outputNameFile;

    @Option(name = "-r")
    private String range;

    @Argument(metaVar = "InputName")
    private String inputNameFile;

    public static void main(String[] args) throws IOException {
        new CutLauncher().launch(args);
    }

    public void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e){
            System.err.println(e.getMessage());
            System.err.println("cut [-c|-w] [-o ofile] [-r range] [file]");
            parser.printUsage(System.err);
        }

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader;
        boolean flagOutput = false;
        boolean checkFlags = (!flagC) && (!flagW);

        if (inputNameFile == null){
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Введите название входного файла: ");
            inputNameFile = reader.readLine();
        }

        if (outputNameFile == null){
            outputNameFile = "defaultOutputFile";
            flagOutput = true;
        }
        if (range == null){
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Диапозон не задан. Введите диапозон: ");
            range = reader.readLine();

        }

        int counter = 0;

        while (checkFlags){
            reader = new BufferedReader(new InputStreamReader(System.in));
            if (counter == 0) System.out.println("Вы не указали параметр диапазона");
            System.out.println("-w: диапозон указывает на слова");
            System.out.println("-с: диапозон указывает на буквы");
            System.out.print("Введите параметр: ");
            String result = reader.readLine().trim();

            if (result.equals("-w")) flagW = true;
            if (result.equals("-c")) flagC = true;

            checkFlags = (!flagC) && (!flagW);
            if (checkFlags) System.out.println("Введный вами параметр неккоректен");
            counter += 1;

        }

        Cut cutter = new Cut(flagC,flagW,outputNameFile,inputNameFile,range, flagOutput);

        cutter.getSlice();

        System.out.println("Флаг вывода: " + flagOutput);

    }
}

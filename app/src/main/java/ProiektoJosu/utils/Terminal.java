package ProiektoJosu.utils;

import ProiektoJosu.Main;
import ProiektoJosu.kudeatzaile.Aukerak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Terminal {
    private static final Terminal instance=new Terminal();
    private Main main;


    private Terminal(){
    }

    public static Terminal getInstance() {
        return instance;
    }

    public void setMain(Main main) {
        this.main = main;
    }


    public BufferedReader sudoTerminal(String komando) throws IOException {
        Process pb = new ProcessBuilder(new String[]{"/bin/bash", "-c", "sudo -S "+komando}).start();

        char[] pasahitza=main.getMainController().getPasahitza();


        OutputStreamWriter output = new OutputStreamWriter(pb.getOutputStream());

        output.write(pasahitza);
        Arrays.fill(pasahitza,'k');
        output.write('\n');
        output.flush();

        BufferedReader input2 =
                new BufferedReader(new InputStreamReader(pb.getInputStream()));

        return input2;

    }


    public BufferedReader terminalNormala(String komando) throws IOException {
        Process pb = new ProcessBuilder(new String[]{"/bin/bash", "-c", komando}).start();

        BufferedReader input2 =
                new BufferedReader(new InputStreamReader(pb.getInputStream()));

        return input2;

    }



    public BufferedReader fdiskExec(Aukerak aukerak, String charD, String diskIz, String partizioTam, String MB){

        Process pb = null;
        try {
            pb = new ProcessBuilder(new String[]{"/bin/bash", "-c", "sudo -S fdisk "+diskIz}).start();

            char[] pasahitza=main.getMainController().getPasahitza();


            OutputStreamWriter output = new OutputStreamWriter(pb.getOutputStream());
            InputStreamReader input = new InputStreamReader(pb.getInputStream());

            output.write(pasahitza);
            Arrays.fill(pasahitza,'k');
            output.write('\n');
            output.flush();

            //aukerak(n,w,q,d)
            switch (aukerak.getAukera()){
                //https://www.howtogeek.com/106873/how-to-use-fdisk-to-manage-partitions-on-linux/
                case 'n':
                    output.write(aukerak.getAukera());
                    output.flush();

                    output.write('\n');
                    output.flush();

                    output.write('\n');
                    output.flush();

                    output.write('\n');
                    output.flush();

                    output.write("+"+partizioTam+MB);
                    break;
                case 'w':
                    //TODO: w -k egingo duen funtzioa
                    output.write(aukerak.getAukera());
                    //erantzuna "The partition table has been altered"
                case 'q':
                    //TODO: q -k egingo duen funtzioa
                    output.write(aukerak.getAukera());
                    //erantzuna "\n"
                case 'd':
                    //TODO: d -k egingoo duen funtzioa
                    output.write(aukerak.getAukera());
                    output.write(charD);
                    // si hay mas de una particion 
                    break;
            }
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedReader input2 =
                new BufferedReader(new InputStreamReader(pb.getInputStream()));


        return input2;

    }

}

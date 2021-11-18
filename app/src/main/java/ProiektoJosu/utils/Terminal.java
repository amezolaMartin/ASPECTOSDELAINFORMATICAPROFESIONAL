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
        InputStreamReader input = new InputStreamReader(pb.getInputStream());

        output.write(pasahitza);
        Arrays.fill(pasahitza,'k');
        output.write('\n');
        output.flush();



        BufferedReader input2 =
                new BufferedReader(new InputStreamReader(pb.getInputStream()));

        return input2;

    }

    public BufferedReader fdiskExec(Aukerak aukerak, String diskIz, String partizioTam, String MB){

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
                case 'n':
                    output.write(aukerak.getAukera());
                    output.write('\n');
                    output.write('\n');
                    output.write('\n');
                    output.write("+"+partizioTam+MB);
                    break;
                case 'w':
                    //TODO: w -k egingo duen funtzioa
                case 'q':
                    //TODO: q -k egingo duen funtzioa
                case 'd':
                    //TODO: d -k egingoo duen funtzioa
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedReader input2 =
                new BufferedReader(new InputStreamReader(pb.getInputStream()));


        return input2;

    }

}

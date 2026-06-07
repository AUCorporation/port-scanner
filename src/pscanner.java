import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.net.InetSocketAddress;
import java.io.PrintWriter;


public class pscanner {
    int[] all_ports_range={1,65535};
    ArrayList<String> results=new ArrayList<>();
        //basic port scan 
     public void scan(String targetIP,int targetPort,int timeoutms){
    try(Socket s=new Socket()){
        s.connect(new InetSocketAddress(targetIP,targetPort),timeoutms);
        results.add("port: "+ targetPort +" is accsessable.");
    }
    catch (Exception e) {
        e.printStackTrace();
    }}


    //scans ports by user preference
    public void start_scan(GUI graphic){
        results.clear();
        
        if(graphic.selection==1){
            scan(graphic.targetIP,Integer.parseInt(graphic.targetPort),Integer.parseInt(graphic.timeoutms));

        }
        else if(graphic.selection==2){
            for(int c=graphic.portMin;c<=graphic.portMax;c++){
            scan(graphic.targetIP,c,Integer.parseInt(graphic.timeoutms));}
        }
        else if(graphic.selection==3){
            for(int c=all_ports_range[0];c<=all_ports_range[1];c++){
                scan(graphic.targetIP,c,Integer.parseInt(graphic.timeoutms));}
        }
        //report creation 
        if (!results.isEmpty()) {
            File f=new File("Report-"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm"))+".txt");
            try(PrintWriter fwrite=new PrintWriter(f)){
                for(int c=0;c<results.size();c++){
                fwrite.println(results.get(c));}
            System.out.println("Rapor oluşturuldu.");
        }
            catch(Exception e){
                e.printStackTrace();
         }}
        else {
             System.out.println("Açık port bulunamadı.");
            }

}}

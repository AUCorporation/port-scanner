import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.net.InetSocketAddress;
import java.io.PrintWriter;
import java.util.ArrayList;

public class pscanner {
    int[] all_ports_range={1,65535};
    List<String> results=Collections.synchronizedList(new ArrayList<>());
    ExecutorService executor=null;
        //basic port scan 
     public void scan(String targetIP,int targetPort,int timeoutms){
    try(Socket s=new Socket()){
        s.connect(new InetSocketAddress(targetIP,targetPort),timeoutms);
        results.add("port: "+ targetPort +" is accsessible.");
    }
    catch (Exception e) {}}


    //scans ports by user preference
    public void start_scan(GUI graphic){
        results.clear();
        int timeout=Integer.parseInt(graphic.timeoutms);
        
        if(graphic.selection==1){
            scan(graphic.targetIP,Integer.parseInt(graphic.targetPort),timeout);

        }
        else if(graphic.selection==2){
        executor=Executors.newFixedThreadPool(100);
            for(int c=graphic.portMin;c<=graphic.portMax;c++){
                final int currentPort = c;
                executor.submit(() ->{
                scan(graphic.targetIP,currentPort,timeout);
});
       }}
        else if(graphic.selection==3){
            executor=Executors.newFixedThreadPool(100);
            for(int c=all_ports_range[0];c<=all_ports_range[1];c++){
                final int currentPort = c;
                executor.submit(() ->{
                scan(graphic.targetIP,currentPort,timeout);
});
        }}
          if (executor != null) {
         executor.shutdown();
            try {
                executor.awaitTermination(15, TimeUnit.MINUTES);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }}
        //report creation 
        if (!results.isEmpty()) {
            File f=new File("Report-"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm"))+".txt");
            try(PrintWriter fwrite=new PrintWriter(f)){
                synchronized (results) {
                for(int c=0;c<results.size();c++){
                fwrite.println(results.get(c));}}
            System.out.println("Rapor oluşturuldu.");
        }
            catch(Exception e){
                e.printStackTrace();
         }}
        else {
             System.out.println("Açık port bulunamadı.");
             }

}}

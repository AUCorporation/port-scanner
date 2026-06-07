import javax.swing.*;
import java.awt.Font;
//import javax.swing.text.DocumentFilter; ill add later
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
    JRadioButton single,multi,all;
    JTextField single_text,multi_text_min,multi_text_max,IPInput,msInput;
    JLabel multi_text_middle,IPText,msText;
    JButton start,clear;
    int selection;
    String targetIP,targetPort,timeoutms;
    public int portMin;
    public int portMax;

    ButtonGroup rgrb=new ButtonGroup();
    //all gui elements
    public GUI(){
    JFrame frame=new JFrame("port-scanner");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600,400);
    frame.setLayout(null);
    frame.setResizable(false);


    single=new JRadioButton("single port");
    single.setBounds(5,10,150,50);
    frame.add(single);

    multi=new JRadioButton("multi port");
    multi.setBounds(5,100,150,50);
    frame.add(multi);

    all=new JRadioButton("all ports");
    all.setBounds(5,200,150,40);
    frame.add(all);

    rgrb.add(single);
    rgrb.add(multi);
    rgrb.add(all);

    single_text=new JTextField();
    single_text.setBounds(5,60,150,40);
    frame.add(single_text);

    multi_text_min=new JTextField();
    multi_text_min.setBounds(5,150,150,40);
    frame.add(multi_text_min);

    multi_text_max=new JTextField();
    multi_text_max.setBounds(250,150,150,40);
    frame.add(multi_text_max);

    IPInput=new JTextField();
    IPInput.setBounds(100,240,150,40);
    frame.add(IPInput);

    msInput=new JTextField();
    msInput.setBounds(360,240,150,40);
    frame.add(msInput);

    multi_text_middle=new JLabel("-");
    multi_text_middle.setBounds(200,140,150,50);
    multi_text_middle.setFont(new Font("Arial",Font.PLAIN,30));
    frame.add(multi_text_middle);

    IPText=new JLabel("Target IP :");
    IPText.setBounds(5,235,150,50);
    IPText.setFont(new Font("Arial",Font.PLAIN,15));
    frame.add(IPText);

    msText=new JLabel("Timeout(ms) :");
    msText.setBounds(260,235,150,50);
    msText.setFont(new Font("Arial",Font.PLAIN,15));
    frame.add(msText);

    start=new JButton("Start");
    start.setBounds(190,300,100,40);
    start.setFont(new Font("Arial",Font.PLAIN,18));
    start.addActionListener(this);
    frame.add(start);

    clear=new JButton("Clear");
    clear.setBounds(310,300,100,40);
    clear.setFont(new Font("Arial",Font.PLAIN,18));
    frame.add(clear);

    frame.setVisible(true);
}   
    //action behavior for start and clear buttons
    @Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(start)){
            targetIP=IPInput.getText();
            timeoutms=msInput.getText();
            if(single.isSelected()){
                selection=1;
                targetPort=single_text.getText();
                }
            else if(multi.isSelected()){
                selection=2;
                portMin=Integer.parseInt(multi_text_min.getText());
                portMax=Integer.parseInt(multi_text_max.getText());
            }
            else if(all.isSelected()){
                selection=3;}
        }
        pscanner tarayici = new pscanner();
        tarayici.start_scan(this);
        }

}

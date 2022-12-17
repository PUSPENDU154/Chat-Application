import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;
import java.io.*;

public class Client implements ActionListener {

    JTextField text;

    static JPanel a1;

    static Box vertical = Box.createVerticalBox(); // putting message vertically one down another

    static DataOutputStream dout;

    static JFrame f=new JFrame();

    Client(){

        f.setLayout(null); // for putting values in frame



        // if I want to divide things on frame then I have to use Panel
        JPanel p1= new JPanel();
        p1.setBackground(new Color(7,94,84)); // Setting customized color by user using RGB
        p1.setBounds(0,0,450,70); // Setting the Layout by the uesr when the default setLayOut is set to null.
        p1.setLayout(null);
        f.add(p1); // for adding the object of Panel



        // how to fetch/get image " ARROW SIGN "
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2= i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT); // customize the boundary of image
        ImageIcon i3=new ImageIcon(i2); // after setting the boundary creating another imageicon for putting it in JLabel
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25); // Setting the boundary of Image
        p1.add(back);



        // For adding the function by that after clicking that image the required call of operation should be occured
        back.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent ae) {
                //setVisible(false);
                System.exit(0); // For Exiting the Chat Interface
            }
        });



        // For Setting Profile Picture
        ImageIcon i4= new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5= i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT); // customize the boundary of image
        ImageIcon i6=new ImageIcon(i5); // after setting the boundary creating another imageicon for putting it in JLabel
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,50,50); // Setting the boundary of Image
        p1.add(profile);


        // For adding the video icon on Upper Portion
        ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8= i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT); // customize the boundary of image
        ImageIcon i9=new ImageIcon(i8); // after setting the boundary creating another imageicon for putting it in JLabel
        JLabel video = new JLabel(i9);
        video.setBounds(300,20,30,30); // Setting the boundary of Image
        p1.add(video);



        // For adding the phone icon on Upper Portion
        ImageIcon i10= new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11= i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT); // customize the boundary of image
        ImageIcon i12=new ImageIcon(i11); // after setting the boundary creating another imageicon for putting it in JLabel
        JLabel phone = new JLabel(i12);
        phone.setBounds(360,20,35,30); // Setting the boundary of Image
        p1.add(phone);


        // For adding the 3-dot icon on Upper Portion
        ImageIcon i13= new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14= i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT); // customize the boundary of image
        ImageIcon i15=new ImageIcon(i14); // after setting the boundary creating another imageicon for putting it in JLabel
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(420,20,10,25); // Setting the boundary of Image
        p1.add(morevert);



        // Writing name on the frame (UPPER)
        JLabel name= new JLabel("USER-CLIENT");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE); // setting foreground of a name
        name.setFont(new Font("SAN_SERIF", Font.BOLD,18)); // Setting Font of a name
        p1.add(name);



        // Writing status on the frame (UPPER)
        JLabel status= new JLabel("Active Now");
        status.setBounds(110,40,100,18);
        status.setForeground(Color.WHITE); // setting foreground
        status.setFont(new Font("SAN_SERIF", Font.BOLD,14)); // Setting Font
        p1.add(status);


        // for adding the frame in the body
        a1= new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);


        // for making the textfield the below part of the chat application
        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text);


        // Setting Button "send"
        JButton send= new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(send);



        // Setting size,Location, Background color setting background of the entire frame
        f.setSize(450,700);
        f.setLocation(800,15); // set the location of the frame
        f.setUndecorated(true); // For eliminating the upper part of frame
        f.getContentPane().setBackground(Color.WHITE);  // for setting the background of that frame



        f.setVisible(true); // showing the frame , it will be at last this command


    }


    // By That the ActionListener can't throw Exceptions or Error
    public void actionPerformed(ActionEvent ae){

        try{

            String out = text.getText(); // by that user can extract the text written inside the text
            //System.out.println(out);


            JPanel p2= formatLabel(out);


            a1.setLayout(new BorderLayout()); // BorderLayout sets the text in the Top,Down,Left,Right and Center Position.

            // for aligning at the right
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);

            // for becoming one under other
            vertical.add(right);


            vertical.add(Box.createVerticalStrut(15));

            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);

            text.setText(""); // for clearing the below textfield after sending one text

            f.repaint();
            f.invalidate();
            f.validate();


        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public static JPanel formatLabel(String out){

        JPanel panel= new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel output= new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));

        panel.add(output);

        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;

    }



    public static void main(String[] args) {
        new Client();  // Annonymous Object

        try{

            Socket s=new Socket("127.0.0.1",6001);

            DataInputStream din= new DataInputStream(s.getInputStream()); // For receiving the message
            dout= new DataOutputStream(s.getOutputStream()); // for Sending a message


            while (true){

                a1.setLayout(new BorderLayout());
                // ReadUTF method is used to read the received message
                String msg=din.readUTF();
                JPanel panel=formatLabel(msg);

                // For showing the received message on the left side
                JPanel left=new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START); // Where line starts
                vertical.add(left);

                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical,BorderLayout.PAGE_START);

                f.validate();
            }





        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

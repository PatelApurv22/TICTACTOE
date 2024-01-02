package com.MyGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener {


    JLabel heading,clockLable,Wpanel,Epanel;
    Font font1=new Font("",Font.BOLD,40);
    JPanel mainPanel;
    JButton [] btns=new JButton[9];
    int[] gameChances ={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;

    int WP1;
    int WP2;
    int Draw;

    int[][] wps ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int winner = 2;
    String winnerstr;
    String apS = new String("Player 1");
    String apI =new String("O.png");


    boolean gameOver=false;

    MyGame()
    {
        System.out.println("Ok.");
        super.setTitle("Tic Tac Toe Game...");
        super.setSize(1000,600);
        super.setLocation(150,50);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("TicTacToe.png"));
        super.setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();



        setVisible(true);
    }
    KeyHandler kHandler;

    {
        kHandler = new KeyHandler(this);
    }


    public void createGUI() {
        this.getContentPane().setBackground(Color.decode("16454473"));


        this.setLayout(new BorderLayout());
        heading = new JLabel("Tic Tac Toe");
        //heading.setIcon(new ImageIcon(getClass().getClassLoader().getResource("TicTacToe.png")));
        heading.setFont(font1);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);

        this.add(heading,BorderLayout.NORTH);

        clockLable =new JLabel("Clock");
        clockLable.setFont(font1);
        clockLable.setHorizontalAlignment(SwingConstants.CENTER);
        clockLable.setForeground(Color.white);
        this.add(clockLable, BorderLayout.SOUTH);

        Thread t=new Thread()
        {
            public void run ()
            {
                try {


                    while (true) {

                        String datetime = new Date().toLocaleString();
                        clockLable.setText(datetime);
                        Thread.sleep(1000);
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        Wpanel = new JLabel();
        Wpanel.setFont(font1);
        Wpanel.setHorizontalAlignment(SwingConstants.CENTER);
        Wpanel.setForeground(Color.white);
        Wpanel.setPreferredSize(new Dimension(250,300));
        Wpanel.setLayout(new GridLayout(4,1));
        JLabel WL=new JLabel("Winner List");

        JLabel LWP1 =new JLabel("Player 1: "+WP1);
        JLabel LWP2 =new JLabel("Player 2: "+WP2);
        JLabel LD=new JLabel("Draw: "+Draw);




        WL.setForeground(Color.white);
        LWP1.setForeground(Color.white);
        LWP2.setForeground(Color.white);
        LD.setForeground(Color.white);

        WL.setFont(font1);
        LWP1.setFont(font1);
        LWP2.setFont(font1);
        LD.setFont(font1);


        Wpanel.add(WL);
        Wpanel.add(LWP1);
        Wpanel.add(LWP2);
        Wpanel.add(LD);
        this.add(Wpanel, BorderLayout.WEST);


        Epanel=new JLabel(apS);

        Epanel.setLayout(new GridLayout(2,1));
        Epanel.setIcon(new ImageIcon(getClass().getClassLoader().getResource(apI)));
        Epanel.setFont(font1);
        Epanel.setHorizontalAlignment(SwingConstants.CENTER);
        Epanel.setVerticalAlignment(SwingConstants.TOP);
        Epanel.setForeground(Color.white);
        Epanel.setPreferredSize(new Dimension(250,300));

        JButton btnReset = new JButton("Reset");
        btnReset.addKeyListener(kHandler);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<9;i++)
                {
                    btns[i].setEnabled(true);
                    gameChances[i]=2;
                    btns[i].setIcon(null);
                }
            }
        });
        btnReset.setFont(font1);
        btnReset.setForeground(Color.decode("16454473"));



        Epanel.add(new JLabel());
        Epanel.add(btnReset);

        this.add(Epanel,BorderLayout.EAST);



        t.start();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));


        for (int i=1 ;i<=9;i++){
            JButton btn =new JButton();
            btn.setBackground(Color.decode("469568"));
            btn.setBorder(BorderFactory.createLineBorder(Color.decode("521211"),5));
            btn.setFont(font1);
            mainPanel.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));
        }
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.decode("521211"),10));
        mainPanel.setPreferredSize(new Dimension(600,600));

        this.add(mainPanel,BorderLayout.CENTER);









    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton)e.getSource();
        String nameStr=currentButton.getName();
        int name=Integer.parseInt(nameStr.trim());
        if (activePlayer==0)
        {
            apS="Player: 2";
            apI="X.png";

        } else if (activePlayer==1) {
            apS="Player: 1";
            apI="O.png";
        }
        Epanel.setText(null);
        Epanel.setIcon(null);
        Epanel.setIcon(new ImageIcon(getClass().getClassLoader().getResource(apI)));
        Epanel.setText(apS);

        if(gameChances[name]==2)
        {
            if(activePlayer == 0)
            {

                currentButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("O.png")));
                currentButton.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("O.png")));
                currentButton.setEnabled(false);
                gameChances[name]=activePlayer;
                activePlayer = 1;
            }
            else
            {
                currentButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("X.png")));
                currentButton.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("X.png")));
                currentButton.setEnabled(false);
                gameChances[name]=activePlayer;
                activePlayer = 0;
            }

            for(int[] temp :wps)
            {
                if(gameChances[temp[0]]==gameChances[temp[1]]&&gameChances[temp[1]]==gameChances[temp[2]]&&gameChances[temp[2]]!=2)
                {
                    winner=gameChances[temp[0]];
                    if(winner==0)
                    {
                        WP1++;
                        winnerstr="O";
                    }
                    else if(winner==1)
                    {
                        WP2++;
                        winnerstr="X";
                    }


                    JOptionPane.showMessageDialog(null, new ImageIcon(getClass().getClassLoader().getResource(winnerstr+".png")),winnerstr+" Win The Game", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(winnerstr+" Wins!!!");
                    gameOver = true;
                    for(int i=0;i<9;i++)
                        {
                            btns[i].setEnabled(true);
                            gameChances[i]=2;
                            btns[i].setIcon(null);
                        }
                        winner=2;
                        Wpanel.removeAll();
                        Epanel.setText(null);
                        Epanel.setIcon(null);
                        Epanel.removeAll();


                        mainPanel.removeAll();
                        heading.removeAll();
                        clockLable.setText(null);
                        clockLable.removeAll();
                        createGUI();


                    break;
                }

            }
            int c=0;
            for (int x:gameChances)
            {
                if(x==2)
                {
                    c++;
                    break;
                }
            }
            if(c==0)
            {
                JOptionPane.showMessageDialog(this,"It's Draw!!!","Draw",JOptionPane.INFORMATION_MESSAGE);
                for(int i=0;i<9;i++)
                {
                    btns[i].setEnabled(true);
                    gameChances[i]=2;
                    btns[i].setIcon(null);
                }
                winner=2;
                Draw++;
                Wpanel.removeAll();
                Epanel.setText(null);
                Epanel.setIcon(null);
                Epanel.removeAll();
                mainPanel.removeAll();
                heading.removeAll();
                clockLable.setText(null);
                clockLable.removeAll();
                createGUI();
            }


        }
        else
        {
            JOptionPane.showMessageDialog(this,"Not Valid Move");
        }

    }
}
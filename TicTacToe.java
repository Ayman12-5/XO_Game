


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    
    JFrame mainFrame = new JFrame("TicTacToe Menu");
    JPanel buttonPanel = new JPanel();
    JButton easyButton = new JButton("Easy");
    JButton mediumButton = new JButton("Medium");
    JButton hardButton = new JButton("Hard");
    JPanel text_panel = new JPanel();
    JLabel lab = new JLabel();

    public TicTacToe() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 650);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());
        
        lab.setBackground(new Color(25,25,25));
        lab.setForeground(new Color(25,255,0));
        lab.setFont(new Font("Ink Free" , Font.ROMAN_BASELINE , 40));
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setOpaque(true);
        lab.setText("You are in TicTacToe Choose Level");
        
        easyButton.setFont(new Font("SansSerif", Font.ITALIC, 30));
        mediumButton.setFont(new Font("SansSerif", Font.ITALIC, 30));
        hardButton.setFont(new Font("SansSerif", Font.ITALIC, 30));
        
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setPreferredSize(new Dimension(200, 100));
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        
        text_panel.setLayout(new BorderLayout());
        text_panel.add(lab);
        text_panel.setPreferredSize(new Dimension(800, 100));
        mainFrame.add(text_panel , BorderLayout.NORTH);
        mainFrame.add(buttonPanel, BorderLayout.CENTER);

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); 
                new Tictoe3.Frame();
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); 
                new Tictoe4.Frame();
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); 
                new Tictoe5.Frame();
            
            }
        });
    }

    public static void main(String[] args) {
        new TicTacToe(); 
    }
}
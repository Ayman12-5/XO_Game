import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Tictoe4 {
    public static class Frame implements ActionListener {
        JFrame frame = new JFrame("TicTacToe");
        Random random = new Random();
        JPanel panel_text = new JPanel();
        JPanel panel_btn = new JPanel();
        JPanel resultPanel = new JPanel();
        JLabel lab1 = new JLabel();
        JLabel resultLabel = new JLabel();
        JButton[] Btn1 = new JButton[16]; 
        JButton resetButton = new JButton("Restart");
        JButton undoButton = new JButton("Undo");
        boolean first_play;
        int lastMoveIndex = -1;

        public Frame() {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 700);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.setResizable(false);

            lab1.setBackground(new Color(25, 25, 25));
            lab1.setForeground(new Color(25, 255, 0));
            lab1.setFont(new Font("Ink Free", Font.ITALIC, 50));
            lab1.setHorizontalAlignment(JLabel.CENTER);
            lab1.setOpaque(true);
            lab1.setText("O turn");

            panel_text.setLayout(new BorderLayout());
            panel_text.add(lab1, BorderLayout.CENTER);
            panel_text.setPreferredSize(new Dimension(900, 80));
            frame.add(panel_text, BorderLayout.NORTH);

            panel_btn.setLayout(new GridLayout(4, 4)); // Change to 4x4 grid
            for (int i = 0; i < 16; i++) {
                Btn1[i] = new JButton();
                panel_btn.add(Btn1[i]);
                Btn1[i].setFont(new Font("Sanserif", Font.BOLD, 100));
                Btn1[i].setFocusable(false);
                Btn1[i].addActionListener(this);
                Btn1[i].setBackground(Color.PINK);
            }
            frame.add(panel_btn, BorderLayout.CENTER);

            resultLabel.setFont(new Font("Ink Free", Font.BOLD, 50));
            resultLabel.setHorizontalAlignment(JLabel.CENTER);

            resetButton.setFont(new Font("Ink Free", Font.BOLD, 25));
            resetButton.addActionListener(e -> resetGame());

            undoButton.setFont(new Font("Ink Free", Font.BOLD, 25));
            undoButton.addActionListener(e -> undoLastMove());

            JButton explanationButton = new JButton("Tips");
            explanationButton.setFont(new Font("Ink Free", Font.BOLD, 25));
            explanationButton.addActionListener(e -> showExplanation());

            JButton backToOriginalButton = new JButton("Back to Main Game");
            backToOriginalButton.setFont(new Font("Ink Free", Font.BOLD, 25));
            backToOriginalButton.addActionListener(e -> switchToOriginalGame());

            resultPanel.setBackground(new Color(220, 220, 220));
            resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            resultPanel.setLayout(new BorderLayout());
            resultPanel.add(resultLabel, BorderLayout.CENTER);
            resultPanel.setPreferredSize(new Dimension(900, 120));

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonsPanel.add(undoButton);
            buttonsPanel.add(resetButton);
            buttonsPanel.add(explanationButton);
            buttonsPanel.add(backToOriginalButton);
            resultPanel.add(buttonsPanel, BorderLayout.SOUTH);

            frame.add(resultPanel, BorderLayout.SOUTH);

            first_Player();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 16; i++) {
                if (e.getSource() == Btn1[i]) {
                    // التحقق إذا كان الزر قد تم ملؤه بالفعل
                    if (!Btn1[i].getText().isEmpty()) {
                        // عرض رسالة تنبيه إذا كان الزر قد تم ملؤه
                        JOptionPane.showMessageDialog(frame, "هذا الزر تم استخدامه بالفعل! اختر زر آخر.", "تنبيه", JOptionPane.WARNING_MESSAGE);
                        return; // الخروج من الدالة دون إجراء أي تغيير
                    }
                    
                    if (first_play) {
                        Btn1[i].setText("X");
                        Btn1[i].setForeground(Color.BLUE);
                        first_play = false;
                        lab1.setText("O turn");
                        lab1.setForeground(Color.RED);
                        lastMoveIndex = i;
                        Won_Player();
                    } else {
                        Btn1[i].setText("O");
                        Btn1[i].setForeground(Color.RED);
                        first_play = true;
                        lab1.setText("X turn");
                        lab1.setForeground(Color.BLUE);
                        lastMoveIndex = i;
                        Won_Player();
                    }
                }
            }
        }
        

        public void first_Player() {
            if (random.nextInt(2) == 0) {
                first_play = true;
                lab1.setText("X turn");
            } else {
                first_play = false;
                lab1.setText("O turn");
            }
        }

        public void Won_Player() {
            if (checkWinner("X")) {
                return;
            }
            if (checkWinner("O")) {
                return;
            }
            boolean draw = true;
            for (int i = 0; i < 16; i++) {
                if (Btn1[i].getText().isEmpty()) {
                    draw = false;
                    break;
                }
            }
            if (draw) {
                resultLabel.setText("It's a Draw!");
                for (int i = 0; i < 16; i++) {
                    Btn1[i].setEnabled(false);
                }
                askToPlayAgain();
            }
        }

        public boolean checkWinner(String player) {
            for (int i = 0; i < 4; i++) {
                if (Btn1[i * 4].getText().equals(player) && Btn1[i * 4 + 1].getText().equals(player) &&
                    Btn1[i * 4 + 2].getText().equals(player) && Btn1[i * 4 + 3].getText().equals(player)) {
                    highlightWinner(player);
                    return true;
                }
                if (Btn1[i].getText().equals(player) && Btn1[i + 4].getText().equals(player) &&
                    Btn1[i + 8].getText().equals(player) && Btn1[i + 12].getText().equals(player)) {
                    highlightWinner(player);
                    return true;
                }
            }
            if (Btn1[0].getText().equals(player) && Btn1[5].getText().equals(player) &&
                Btn1[10].getText().equals(player) && Btn1[15].getText().equals(player)) {
                highlightWinner(player);
                return true;
            }
            if (Btn1[3].getText().equals(player) && Btn1[6].getText().equals(player) &&
                Btn1[9].getText().equals(player) && Btn1[12].getText().equals(player)) {
                highlightWinner(player);
                return true;
            }
            return false;
        }

        public void highlightWinner(String player) {
            for (int i = 0; i < 16; i++) {
                Btn1[i].setEnabled(false);
            }
            resultLabel.setText(player + " IS WON");
            resultLabel.setForeground(player.equals("X") ? Color.BLUE : Color.RED);
            askToPlayAgain();
        }

        public void askToPlayAgain() {
            int response = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                frame.dispose();
            }
        }

        public void resetGame() {
            for (int i = 0; i < 16; i++) {
                Btn1[i].setText("");
                Btn1[i].setEnabled(true);
                Btn1[i].setBackground(Color.PINK);
            }
            resultLabel.setText("");
            lastMoveIndex = -1;
            first_Player();
        }

        public void undoLastMove() {
            if (lastMoveIndex != -1) {
                Btn1[lastMoveIndex].setText("");
                Btn1[lastMoveIndex].setEnabled(true);
                Btn1[lastMoveIndex].setBackground(Color.PINK);
                first_play = !first_play;
                lab1.setText(first_play ? "X turn" : "O turn");
                lab1.setForeground(first_play ? Color.BLUE : Color.RED);
                lastMoveIndex = -1;
            }
        }

        private void showExplanation() {
            String message = "لعبة Tic Tac Toe هي لعبة بسيطة بين لاعبين.\n" +
                             "الهدف هو وضع أربعة رموز (X أو O) في صف واحد (أفقي أو عمودي أو قطري).\n" +
                             "اللاعب الأول يستخدم X واللاعب الثاني يستخدم O.\n" +
                             "اللاعب الذي يحقق 4 رموز متتالية في صف واحد يفوز.\n\n" +
                             "التحكم يكون بالنقر على الخلايا الفارغة.\n" +
                             "عند الفوز أو التعادل، يمكنك إعادة تشغيل اللعبة.";
            JOptionPane.showMessageDialog(frame, message, "شرح اللعبة", JOptionPane.INFORMATION_MESSAGE);
        }

        private void switchToOriginalGame() {
            frame.dispose();
            new TicTacToe();
        }
    }

   
    public static void main(String[] args) {
        new Frame();
    }
}

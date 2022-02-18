package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RulesPanel extends JPanel implements MouseListener {
    private StatesObservable obsrvble;
    private static final Color color = new Color(0, 78, 56);

    public RulesPanel(StatesObservable so) {
        obsrvble = so;

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(color);
        JLabel back = new JLabel("Back to menu");
        back.setForeground(Color.white);
        back.addMouseListener(this);
        bottomPanel.add(back, BorderLayout.LINE_END);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(color);
        JTextArea rulesText = new JTextArea(getRules(), 10, 10);
        rulesText.setBackground(color);
        rulesText.setForeground(Color.white);
        topPanel.add(rulesText);

        JPanel rulesPanel = new JPanel(new BorderLayout());
        rulesPanel.setBackground(color);

        rulesPanel.add(topPanel, BorderLayout.CENTER);
        rulesPanel.add(bottomPanel, BorderLayout.SOUTH);

    }

    public String getRules() {
        String str = "MAssa regler sjdfhdwsöuihwuiöewhiuöweywuiw23838bfebjs";
        return str;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        obsrvble.setValue(States.START);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

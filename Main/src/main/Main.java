package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close build when close game
        window.setResizable(false); // Change to resize window
        window.setTitle("Feather RPG"); // Game name
        
        window.add(gamePanel);
        
        window.pack(); // Cause this window to fit the perferred size
        
        window.setLocationRelativeTo(null);
        window.setVisible(true); // Show game
        
        gamePanel.startGameThread();
        
    }
    
}
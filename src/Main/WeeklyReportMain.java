/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import WeeklyReport.WeeklyPDF;
import Email.WeeklyEmail;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author cmeehan
 */
public class WeeklyReportMain {

    private final static JLabel timeLabel = new JLabel(), informationLabel = new JLabel(), warningLabel = new JLabel();
    private final static JPanel jPanel = new JPanel();
    private final static JFrame jFrame = new JFrame();
    private final static JButton jButton = new JButton();

    /**
     * @param args the command line arguments
     */
    private static String getTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE HH:mm:ss", Locale.US);
        String time = dateFormat.format(now);
        return time;
    }

    private static void sendReport() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getTime().equals("Mon 07:45:00")) {
                    WeeklyPDF pdf = new WeeklyReport.WeeklyPDF();
                    WeeklyEmail email = new Email.WeeklyEmail();
                }
            }
        }, 1000, 1000);
    }

    private static JFrame showTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeLabel.setText("Current Time: " + getTime());
            }
        }, 1000, 1000);
        jPanel.add(timeLabel);
        informationLabel.setText("Reports are generated every Monday at 0745.");
        jPanel.add(informationLabel);
        warningLabel.setText("Do not close this window.");
        jPanel.add(warningLabel);
        warningLabel.setText("This application will not work if the window is closed");
        jPanel.add(warningLabel);
        jButton.setText("Close Window");
        jButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        jPanel.add(jButton);
        jFrame.add(jPanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(325, 150);
        jFrame.setResizable(false);
        jFrame.setTitle("RQS Weekly Report");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }
    
    
    public static void main(String... args) {
        sendReport();
        showTimer().setVisible(true);
        //new WeeklyPDF();
    }

}

//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;
        JButton switchFormat;
        JButton timezone;

        Insets inset;


        SimpleClock() {
            this.getContentPane().setBackground(Color.DARK_GRAY);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(350, 220);
            this.setResizable(false);

            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat= new SimpleDateFormat("EEEE");
            dateFormat= new SimpleDateFormat("dd MMMMM, yyyy");

            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 55));
            timeLabel.setBackground(Color.DARK_GRAY);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);

            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
            dayLabel.setForeground(Color.WHITE);

            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));
            dateLabel.setForeground(Color.WHITE);

            switchFormat = new JButton("Format");
            switchFormat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(timeFormat.equals(new SimpleDateFormat("hh:mm:ss a"))){
                    timeFormat = new SimpleDateFormat("HH:mm:ss");
                    } else timeFormat = new SimpleDateFormat("hh:mm:ss a");
                }
            });
            switchFormat.setFont(new Font("Ink Free", Font.BOLD, 15));
            switchFormat.setForeground(Color.BLACK);

            timezone = new JButton("GMT");
            timezone.setFont(new Font("Ink Free", Font.BOLD, 15));
            timezone.setForeground(Color.BLACK);

            timezone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(timeFormat.getTimeZone().equals("Local")){
                        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    } else timeFormat.setTimeZone(TimeZone.getTimeZone("Local"));
                }
            });

            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);

            this.add(switchFormat);
            this.add(timezone);

            this.setVisible(true);
        }

        public static void main(String[] args) {
            MyThread thread = new MyThread();
            thread.start();
        }
    }

    class MyThread extends Thread {
        SimpleClock clock = new SimpleClock();

        public void run() {
            while (true) {
                clock.time = clock.timeFormat.format(Calendar.getInstance().getTime());
                clock.timeLabel.setText(clock.time);

                clock.day = clock.dayFormat.format(Calendar.getInstance().getTime());
                clock.dayLabel.setText(clock.day);

                clock.date = clock.dateFormat.format(Calendar.getInstance().getTime());
                clock.dateLabel.setText(clock.date);

                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
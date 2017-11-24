/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.imageio.ImageIO;

public class DrawVerticalText extends JPanel {

    private String filePath;

    public DrawVerticalText(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void paint(Graphics g) {
        
        
        
        Graphics2D g2 = (Graphics2D) g;

        //
        // Define rendering hint, font name, font style and font size
        //
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font(Font.SERIF, Font.BOLD, 16));

        //
        // Rotate 90 degree to make a vertical text
        //
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 10, 10);
        g2.setTransform(at);
        g2.drawString("L001-17022016", 10, 10);
//        g2.drawString("This is a vertical text 2", 30, 30);
        at.setToRotation(Math.toRadians(0));
        g2.setTransform(at);
        g2.drawString("VCK000-0001", 40, 40);
        BufferedImage background = null;
        Image scaled = null;
        try {
            background = ImageIO.read(new File(filePath));
        } catch (Exception e) {
        }
        scaled = background.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
        g2.drawImage(scaled, 50, 50, this);
    }

    public static void main(String[] args) throws PrinterException {
        JFrame frame = new JFrame();
        frame.setTitle("Draw Vertical Text Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new DrawVerticalText("D:/Project/template/images6720445272038580610.jpg"));
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);

//        PrinterJob pjob = PrinterJob.getPrinterJob();
//        PageFormat preformat = pjob.defaultPage();
//        preformat.setOrientation(PageFormat.LANDSCAPE);
//        PageFormat postformat = pjob.pageDialog(preformat);
////If user does not hit cancel then print.
//        if (preformat != postformat) {
//            //Set print component
//            pjob.setPrintable(new MyPrinter(frame), postformat);
//            if (pjob.printDialog()) {
//                pjob.print();
//            }
//        }
    }
}

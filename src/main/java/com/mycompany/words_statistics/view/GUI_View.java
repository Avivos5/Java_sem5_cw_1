/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.words_statistics.view;

import com.mycompany.words_statistics.exceptions.IsEmptyException;
import com.mycompany.words_statistics.model.TextFileStatsModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateusz Grabowski
 */
public class GUI_View extends JFrame implements ActionListener {
    
    private TextFileStatsModel statsModel;
    
    private DefaultTableModel tableModel = new DefaultTableModel(
            new Object [][] {
                {"Total Characters", null},
                {"Vowels", null},
                {"Consonants", null},
                {"Others", null},
            },
            new String [] {
                "Stats", "Counts"
            });   
    
    
    /**
     * Creates new form GUI_View
     * @param statsModel instace of TextFileStatsModel class
     */
    public GUI_View(TextFileStatsModel statsModel) {
        this.statsModel = statsModel;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pathTextField = new javax.swing.JTextField();
        analyzeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        statsTable = new javax.swing.JTable();
        pathLabel = new javax.swing.JLabel();
        fileContentLabel = new javax.swing.JLabel();
        statsTableLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileContentTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Words Statistics");

        pathTextField.setText("C:/users/mati/desktop/quote.txt");

        analyzeBtn.setText("Analyze");
        analyzeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeBtnActionPerformed(evt);
            }
        });

        statsTable.setModel(tableModel);
        jScrollPane1.setViewportView(statsTable);

        pathLabel.setText("File Path");

        fileContentLabel.setText("File Content");

        statsTableLabel.setText("File Statistics");

        fileContentTextArea.setColumns(20);
        fileContentTextArea.setLineWrap(true);
        fileContentTextArea.setRows(5);
        fileContentTextArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(fileContentTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(analyzeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fileContentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2))
                        .addGap(96, 96, 96))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statsTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(169, 169, 169))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(pathLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(analyzeBtn))
                .addGap(47, 47, 47)
                .addComponent(fileContentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(statsTableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        pathTextField.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void analyzeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeBtnActionPerformed

            savePathFromUser();

            String filePath;
            try{
                filePath = statsModel.checkIfFilePathIsSet();
            }
            catch(IsEmptyException ex){
                //dialog z błędem
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try{
                analyzeText(filePath);
            }
            catch(Exception ex){
                // tutaj dialog z błędem
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }
            
            
    }//GEN-LAST:event_analyzeBtnActionPerformed

    private void savePathFromUser() {
        statsModel.setFilePath(pathTextField.getText());
    }
    
    public void setTableTotal(Integer totalCount){
        tableModel.setValueAt(totalCount, 0, 1);
    }
    public void setTableVowels(Integer vowelsCount){
        tableModel.setValueAt(vowelsCount, 1, 1);
    }
    public void setTableConsonants(Integer consonantsCount){
        tableModel.setValueAt(consonantsCount, 2, 1);
    }
    public void setTableOthers(Integer othersCount){
        tableModel.setValueAt(othersCount, 3, 1);
    }
    
    static String readFile(String path, Charset encoding)
        throws IOException
      {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    
    private void resetCounters(){
        statsModel.setCharCount(0);
        statsModel.setVowelsCount(0);
        statsModel.setConsonantsCount(0);
    }
    
    public void analyzeText(String filePath) throws Exception{
        
        resetCounters();
        
        FileReader fr = new FileReader(filePath);
        int i;
        while ((i = fr.read()) != -1){
            statsModel.incrementChars(1);

            if(TextFileStatsModel.VOWELS.contains(Character.toUpperCase((char)i))){
                statsModel.incrementVowels(1);
            }
            else if(TextFileStatsModel.CONSONANTS.contains(Character.toUpperCase((char)i))){
                statsModel.incrementConsonants(1);
            } 
        }
        
        int otherChars = statsModel.getCharCount() - (statsModel.getVowelsCount() + statsModel.getConsonantsCount());

        setTableTotal(statsModel.getCharCount());
        setTableVowels(statsModel.getVowelsCount());
        setTableConsonants(statsModel.getConsonantsCount());
        setTableOthers(otherChars);
        
        String content = readFile(filePath, StandardCharsets.UTF_8);
        fileContentTextArea.setText(content);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeBtn;
    private javax.swing.JLabel fileContentLabel;
    private javax.swing.JTextArea fileContentTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JTable statsTable;
    private javax.swing.JLabel statsTableLabel;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * Copyright 2015 Igor Maznitsa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.igormaznitsa.nbmindmap.nb.swing;

import com.igormaznitsa.nbmindmap.utils.NbUtils;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import org.apache.commons.io.FileUtils;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.text.CloneableEditorSupport;
import org.openide.text.NbDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PlainTextEditor extends javax.swing.JPanel {

  private static final long serialVersionUID = 5847351391577028903L;

  private static final Logger logger = LoggerFactory.getLogger(PlainTextEditor.class);
  
  private final BaseDocument doc;
  private final JEditorPane editor;
  
  private static final FileFilter TEXT_FILE_FILTER = new FileFilter() {

    @Override
    public boolean accept(final File f) {
      return f.isDirectory() || f.getName().toLowerCase(Locale.ENGLISH).endsWith(".txt"); //NOI18N
    }

    @Override
    public String getDescription() {
      return java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle").getString("PlainTextEditor.fileFilter.description");
    }
  };
  
  public PlainTextEditor(final String text) {
    initComponents();

    editor = new JEditorPane();
    final EditorKit kit = CloneableEditorSupport.getEditorKit("text/plain"); //NOI18N
    editor.setEditorKit(kit);

    this.doc = Utilities.getDocument(editor);

    setText(text);
    
    final Component compo;

    if (doc instanceof NbDocument.CustomEditor) {
      NbDocument.CustomEditor ce = (NbDocument.CustomEditor) doc;
      compo = ce.createEditor(editor);
    }
    else {
      compo = new JScrollPane(editor);
    }

    compo.setPreferredSize(new Dimension(620, 440));
    
    this.add(compo, BorderLayout.CENTER);
  }

  public String getText() {
    try {
      return this.doc.getText(0, this.doc.getLength());
    }
    catch (BadLocationException e) {
      logger.error("Can't get text", e); //NOI18N
      return null;
    }
  }

  private void setText(final String text){
    try {
      this.doc.replace(0, 0, text, null);
    }
    catch (BadLocationException ex) {
      logger.error("Can't set text", ex); //NOI18N
      throw new RuntimeException(ex);
    }    
  }
  
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    editorPopup = new javax.swing.JPopupMenu();
    jToolBar1 = new javax.swing.JToolBar();
    buttonLoad = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();
    buttonCopy = new javax.swing.JButton();
    buttonPaste = new javax.swing.JButton();
    buttonClearAll = new javax.swing.JButton();

    setLayout(new java.awt.BorderLayout());

    jToolBar1.setFloatable(false);
    jToolBar1.setRollover(true);

    buttonLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/disk16.png"))); // NOI18N
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle"); // NOI18N
    buttonLoad.setText(bundle.getString("PlainTextEditor.buttonImport")); // NOI18N
    buttonLoad.setToolTipText(bundle.getString("PlainTextEditor.buttonLoad.toolTipText")); // NOI18N
    buttonLoad.setFocusable(false);
    buttonLoad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonLoad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    buttonLoad.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonLoadActionPerformed(evt);
      }
    });
    jToolBar1.add(buttonLoad);

    buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/file_save16.png"))); // NOI18N
    buttonSave.setText(bundle.getString("PlaintextEditor.buttonExport")); // NOI18N
    buttonSave.setToolTipText(bundle.getString("PlainTextEditor.buttonSave.toolTipText")); // NOI18N
    buttonSave.setFocusable(false);
    buttonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    buttonSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonSaveActionPerformed(evt);
      }
    });
    jToolBar1.add(buttonSave);

    buttonCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/page_copy16.png"))); // NOI18N
    buttonCopy.setText(bundle.getString("PlainTextEditor.buttonCopy.text")); // NOI18N
    java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle"); // NOI18N
    buttonCopy.setToolTipText(bundle1.getString("PlainTextEditor.buttonCopy.toolTipText")); // NOI18N
    buttonCopy.setFocusable(false);
    buttonCopy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonCopy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    buttonCopy.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonCopyActionPerformed(evt);
      }
    });
    jToolBar1.add(buttonCopy);

    buttonPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/paste_plain16.png"))); // NOI18N
    buttonPaste.setText(bundle.getString("PlainTextEditor.buttonPaste.text")); // NOI18N
    buttonPaste.setToolTipText(bundle.getString("PlainTextEditor.buttonPaste.toolTipText")); // NOI18N
    buttonPaste.setFocusable(false);
    buttonPaste.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonPaste.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    buttonPaste.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonPasteActionPerformed(evt);
      }
    });
    jToolBar1.add(buttonPaste);

    buttonClearAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/igormaznitsa/nbmindmap/icons/cross16.png"))); // NOI18N
    buttonClearAll.setText(bundle.getString("PlainTextEditor.buttonClearAll.text")); // NOI18N
    buttonClearAll.setToolTipText(bundle.getString("PlainTextEditor.buttonClearAll.toolTipText")); // NOI18N
    buttonClearAll.setFocusable(false);
    buttonClearAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    buttonClearAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    buttonClearAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonClearAllActionPerformed(evt);
      }
    });
    jToolBar1.add(buttonClearAll);

    add(jToolBar1, java.awt.BorderLayout.PAGE_START);
  }// </editor-fold>//GEN-END:initComponents

  private void buttonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoadActionPerformed
    final File home = new File(System.getProperty("user.home")); //NOI18N
    final File toOpen = new FileChooserBuilder("user-home-dir"). //NOI18N
            setTitle(java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle").getString("PlainTextEditor.buttonLoadActionPerformed.title")).
            addFileFilter(TEXT_FILE_FILTER).setFileFilter(TEXT_FILE_FILTER).
            setFilesOnly(true).
            setDefaultWorkingDirectory(home).
            showOpenDialog();
    if (toOpen!=null){
      try {
        final String text = FileUtils.readFileToString(toOpen, "UTF-8"); //NOI18N
        setText(text);
      }
      catch (Exception ex) {
        logger.error("Error during text file loading", ex); //NOI18N
        NbUtils.msgError(java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle").getString("PlainTextEditor.buttonLoadActionPerformed.msgError"));
      }
    }
  }//GEN-LAST:event_buttonLoadActionPerformed

  private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
    final File home = new File(System.getProperty("user.home")); //NOI18N
    final File toSave = new FileChooserBuilder("user-home-dir").
            setTitle(java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle").getString("PlainTextEditor.buttonSaveActionPerformed.saveTitle")).
            addFileFilter(TEXT_FILE_FILTER).setFileFilter(TEXT_FILE_FILTER).
            setFilesOnly(true).
            setDefaultWorkingDirectory(home).
            showSaveDialog();
    if (toSave != null) {
      try {
        final String text = getText();
        FileUtils.writeStringToFile(toSave, text, "UTF-8"); //NOI18N
      }
      catch (Exception ex) {
        logger.error("Error during text file saving", ex); //NOI18N
        NbUtils.msgError(java.util.ResourceBundle.getBundle("com/igormaznitsa/nbmindmap/i18n/Bundle").getString("PlainTextEditor.buttonSaveActionPerformed.msgError"));
      }
    }
  }//GEN-LAST:event_buttonSaveActionPerformed

  private void buttonCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCopyActionPerformed
    StringSelection stringSelection = new StringSelection(this.editor.getSelectedText());
    final Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
    clpbrd.setContents(stringSelection, null);
  }//GEN-LAST:event_buttonCopyActionPerformed

  private void buttonPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPasteActionPerformed
    try {
      this.editor.replaceSelection((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
    }
    catch (UnsupportedFlavorException ex) {
      // no text data in clipboard
    }
    catch (IOException ex) {
      logger.error("Error during paste from clipboard", ex); //NOI18N
    }

  }//GEN-LAST:event_buttonPasteActionPerformed

  private void buttonClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearAllActionPerformed
    this.editor.setText(""); //NOI18N
  }//GEN-LAST:event_buttonClearAllActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonClearAll;
  private javax.swing.JButton buttonCopy;
  private javax.swing.JButton buttonLoad;
  private javax.swing.JButton buttonPaste;
  private javax.swing.JButton buttonSave;
  private javax.swing.JPopupMenu editorPopup;
  private javax.swing.JToolBar jToolBar1;
  // End of variables declaration//GEN-END:variables
}

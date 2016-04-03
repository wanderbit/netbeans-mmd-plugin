/*
 * Copyright 2016 Igor Maznitsa.
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
package com.igormaznitsa.mindmap.swing.services;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public interface UIComponentFactory {
  @Nonnull
  JPanel makePanel();
  @Nonnull
  JComboBox makeComboBox();
  @Nonnull
  JButton makeButton();
  @Nonnull
  JToolBar makeToolBar();
  @Nonnull
  JScrollPane makeScrollPane();
  @Nonnull
  JCheckBox makeCheckBox();
  @Nonnull
  JLabel makeLabel();
  @Nonnull
  JPopupMenu makePopupMenu();
  @Nonnull
  JTextArea makeTextArea ();
  @Nonnull
  JEditorPane makeEditorPane ();
  @Nonnull
  JMenuItem makeMenuItem (@Nonnull String text, @Nullable Icon icon);
  @Nonnull
  JCheckBoxMenuItem makeCheckboxMenuItem (@Nonnull String text, @Nullable Icon icon, boolean selected);
  @Nonnull
  JSeparator makeMenuSeparator (); 
  @Nonnull
  JMenu makeMenu (@Nonnull String text); 
  @Nonnull
  JSlider makeSlider (); 
}

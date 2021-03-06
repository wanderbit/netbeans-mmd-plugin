/* 
 * Copyright (C) 2018 Igor Maznitsa.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.igormaznitsa.sciareto.ui.editors;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.swing.JComponent;
import com.igormaznitsa.meta.common.interfaces.Disposable;
import com.igormaznitsa.mindmap.swing.panel.DialogProvider;
import com.igormaznitsa.sciareto.Main;
import com.igormaznitsa.sciareto.ui.DialogProviderManager;
import com.igormaznitsa.sciareto.ui.tabs.TabProvider;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;

public abstract class AbstractEditor implements TabProvider, Disposable {

  private final AtomicBoolean disposeFlag = new AtomicBoolean();
  private static final Map<String, ImageIcon> ICON_CACHE = new HashMap<>();

  public AbstractEditor() {
    super();
  }

  @Override
  public void updateConfiguration() {
  }

  @Override
  public boolean saveDocumentAs() throws IOException {
    final DialogProvider dialogProvider = DialogProviderManager.getInstance().getDialogProvider();
    final File file = this.getTabTitle().getAssociatedFile();
    File fileToSave = dialogProvider.msgSaveFileDialog(Main.getApplicationFrame(), "save-as", "Save as", file, true, new FileFilter[]{getFileFilter()}, "Save");
    if (fileToSave != null) {
      if (!fileToSave.getName().contains(".")) {
        final Boolean result = dialogProvider.msgConfirmYesNoCancel(Main.getApplicationFrame(), "Add extension", String.format("Add file extenstion '%s'?", this.getDefaultExtension()));
        if (result == null) {
          return false;
        }
        if (result) {
          fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + '.' + this.getDefaultExtension());
        }
      }
      this.getTabTitle().setAssociatedFile(fileToSave);
      this.getTabTitle().setChanged(true);
      return this.saveDocument();
    }
    return false;
  }

  @Nonnull
  public abstract String getDefaultExtension();

  @Nonnull
  public abstract EditorContentType getEditorContentType();

  @Nonnull
  public abstract JComponent getContainerToShow();

  @Override
  public final void dispose() {
    if (disposeFlag.compareAndSet(false, true)) {
      this.doDispose();
    }
  }

  protected void doDispose() {

  }

  @Override
  public boolean isDisposed() {
    return this.disposeFlag.get();
  }

  @Nonnull
  protected static synchronized ImageIcon loadMenuIcon(@Nonnull final String name) {
    if (ICON_CACHE.containsKey(name)) {
      return ICON_CACHE.get(name);
    } else {
      final ImageIcon loaded = new javax.swing.ImageIcon(ClassLoader.getSystemResource("menu_icons/" + name + ".png"));
      ICON_CACHE.put(name, loaded);
      return loaded;
    }
  }
}

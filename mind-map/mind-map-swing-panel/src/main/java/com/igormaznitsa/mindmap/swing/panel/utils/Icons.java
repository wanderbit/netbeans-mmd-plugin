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
package com.igormaznitsa.mindmap.swing.panel.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;

public enum Icons {
  ICO_FREEMIND("mm16.png"), 
  ICO_MARKDOWN("md16.png"), 
  ICO_MINDMUP("mup16.png"), 
  ICO_PNG("png16.png"), 
  ICO_TXT("txt16.png"),
  ICO_PRINTER("printer16.png"),
  ICO_PAGE("page16.png");

  private final ImageIcon icon;

  @Nonnull
  public ImageIcon getIcon() {
    return this.icon;
  }

  private Icons(@Nonnull final String name) {
    final InputStream in = ScalableIcon.class.getClassLoader().getResourceAsStream("com/igormaznitsa/mindmap/swing/panel/icons/" + name); //NOI18N
    try {
      this.icon = new ImageIcon(ImageIO.read(in));
    }
    catch (IOException ex) {
      throw new Error("Can't load icon " + name, ex); //NOI18N
    }
    finally {
      IOUtils.closeQuietly(in);
    }
  }
}

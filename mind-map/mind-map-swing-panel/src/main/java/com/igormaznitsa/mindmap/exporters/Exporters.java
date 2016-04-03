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
package com.igormaznitsa.mindmap.exporters;

import javax.annotation.Nonnull;

public enum Exporters {
  FREEMIND(new FreeMindExporter()),
  MUNDMAP(new MindmupExporter()),
  TEXT(new TextExporter()),
  MD(new MDExporter()),
  PNGIMAGE(new PNGImageExporter());
  

  private final AbstractMindMapExporter exporter;
  
  private Exporters(@Nonnull final AbstractMindMapExporter exporter) {
    this.exporter = exporter;
  }

  @Nonnull
  public AbstractMindMapExporter getExporter(){
    return this.exporter;
  }
}

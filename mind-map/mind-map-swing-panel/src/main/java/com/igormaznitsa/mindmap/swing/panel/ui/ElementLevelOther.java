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
package com.igormaznitsa.mindmap.swing.panel.ui;

import static com.igormaznitsa.meta.common.utils.Assertions.assertNotNull;

import com.igormaznitsa.mindmap.swing.panel.MindMapPanelConfig;
import com.igormaznitsa.mindmap.model.Topic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.annotation.Nonnull;

public class ElementLevelOther extends ElementLevelFirst {

  public ElementLevelOther(@Nonnull final Topic model) {
    super(model);
  }

  protected ElementLevelOther(@Nonnull final ElementLevelOther element) {
    super(element);
  }

  @Override
  @Nonnull
  public AbstractElement makeCopy() {
    return new ElementLevelOther(this);
  }
  
  @Override
  public void drawComponent(@Nonnull final Graphics2D g, @Nonnull final MindMapPanelConfig cfg, final boolean drawCollapsator) {
    g.setStroke(new BasicStroke(cfg.safeScaleFloatValue(cfg.getElementBorderWidth(),0.1f)));

    final Shape shape = makeShape(cfg, 0f, 0f);

    if (cfg.isDropShadow()) {
      g.setColor(cfg.getShadowColor());
      final float offset = cfg.safeScaleFloatValue(cfg.getShadowOffset(), 0.0f);
      g.fill(makeShape(cfg, offset, offset));
    }

    g.setColor(getBackgroundColor(cfg));
    g.fill(shape);

    g.setColor(getBorderColor(cfg));
    g.draw(shape);

    g.setColor(getTextColor(cfg));
    this.textBlock.paint(g);
    
    if (this.extrasIconBlock.hasContent()) {
      this.extrasIconBlock.paint(g);
    }

    if (drawCollapsator && this.hasChildren()){
      drawCollapsator(g,  cfg,this.isCollapsed());
    }
  }
  
  @Override
  public void doPaintConnectors(@Nonnull final Graphics2D g, final boolean leftDirection, @Nonnull final MindMapPanelConfig cfg) {
    final Rectangle2D source = new Rectangle2D.Double(this.bounds.getX() + this.collapsatorZone.getX(), this.bounds.getY() + this.collapsatorZone.getY(), this.collapsatorZone.getWidth(), this.collapsatorZone.getHeight());
    for (final Topic t : this.model.getChildren()) {
      this.drawConnector(g, source, assertNotNull(((AbstractElement) t.getPayload())).getBounds(), leftDirection, cfg);
    }
  }
  
  @Override
  public void setLeftDirection(boolean leftSide) {
  }

  @Override
  public boolean isLeftDirection() {
    Topic topic = this.model.getParent();

    boolean result = false;

    while (topic != null) {
      final AbstractElement w = assertNotNull((AbstractElement) topic.getPayload());
      if (w.getClass() == ElementLevelFirst.class) {
        result = ((ElementLevelFirst) w).isLeftDirection();
        break;
      }
      else {
        topic = topic.getParent();
      }
    }

    return result;
  }

  @Override
  @Nonnull
  public Color getBackgroundColor(@Nonnull final MindMapPanelConfig config) {
    final Color dflt = this.fillColor == null ? config.getOtherLevelBackgroundColor() : this.fillColor;
    return dflt;
  }

  @Override
  @Nonnull
  public Color getTextColor(@Nonnull final MindMapPanelConfig config) {
    final Color dflt = this.textColor == null ? config.getOtherLevelTextColor() : this.textColor;
    return dflt;
  }
  
}

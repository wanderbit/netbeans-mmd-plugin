/*
 * Copyright 2015-2018 Igor Maznitsa.
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

package com.igormaznitsa.mindmap.plugins.processors;

import com.igormaznitsa.meta.annotation.MustNotContainNull;
import com.igormaznitsa.mindmap.model.Topic;
import com.igormaznitsa.mindmap.plugins.PopUpSection;
import com.igormaznitsa.mindmap.plugins.api.AbstractFocusedTopicPlugin;
import com.igormaznitsa.mindmap.plugins.api.PluginContext;
import com.igormaznitsa.mindmap.swing.panel.DialogProvider;
import com.igormaznitsa.mindmap.swing.panel.MindMapPanel;
import com.igormaznitsa.mindmap.swing.panel.Texts;
import com.igormaznitsa.mindmap.swing.services.IconID;
import com.igormaznitsa.mindmap.swing.services.ImageIconServiceProvider;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.Icon;

public class CloneTopicPlugin extends AbstractFocusedTopicPlugin {

  private static final Icon ICO = ImageIconServiceProvider.findInstance().getIconForId(IconID.POPUP_CLONE_TOPIC);

  @Override
  public int getOrder() {
    return 40;
  }

  @Override
  @Nullable
  protected Icon getIcon(@Nonnull final PluginContext context, @Nullable final Topic activeTopic) {
    return ICO;
  }

  @Override
  @Nonnull
  protected String getName(@Nonnull final PluginContext context, @Nullable final Topic activeTopic) {
    return context.getSelectedTopics().length > 0 ? Texts.getString("MMDGraphEditor.makePopUp.miCloneSelectedTopic") : Texts.getString("MMDGraphEditor.makePopUp.miCloneTheTopic");
  }

  @Override
  protected void doActionForTopic(@Nonnull final PluginContext context,@Nullable final Topic activeTopic) {
    final Topic toClone = context.getSelectedTopics().length > 0 ? context.getSelectedTopics()[0] : activeTopic;

    if (toClone != null) {

      final Boolean cloneSubtree = toClone.hasChildren() ? context.getDialogProvider().msgConfirmYesNoCancel(null, Texts.getString("MindMapPanel.titleCloneTopicRequest"), Texts.getString("MindMapPanel.cloneTopicSubtreeRequestMsg")) : Boolean.FALSE;
      if (cloneSubtree == null) {
        return;
      }
      context.getPanel().cloneTopic(toClone, cloneSubtree);
    }
  }

  @Override
  public boolean needsSelectedTopics() {
    return false;
  }

  @Override
  public boolean needsTopicUnderMouse() {
    return false;
  }

  @Override
  public boolean isEnabled(@Nonnull final PluginContext context, @Nullable final Topic activeTopic) {
    return (context.getSelectedTopics().length == 1 
            && !context.getSelectedTopics()[0].isRoot()) || (context.getSelectedTopics().length == 0 && activeTopic != null && !activeTopic.isRoot());
  }

  @Override
  @Nonnull
  public PopUpSection getSection() {
    return PopUpSection.MAIN;
  }

  @Override
  public boolean isCompatibleWithFullScreenMode() {
    return false;
  }
}

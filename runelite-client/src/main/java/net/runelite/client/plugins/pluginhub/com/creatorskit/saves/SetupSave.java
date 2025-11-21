package net.runelite.client.plugins.pluginhub.com.creatorskit.saves;

import net.runelite.client.plugins.pluginhub.com.creatorskit.models.CustomModelComp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SetupSave
{
    private String version;
    private CustomModelComp[] comps;
    private FolderNodeSave masterFolderNode;
    private CharacterSave[] saves;
}

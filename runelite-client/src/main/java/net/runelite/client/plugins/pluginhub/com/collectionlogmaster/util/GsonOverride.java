package net.runelite.client.plugins.pluginhub.com.collectionlogmaster.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.Tag;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.adapters.EnumAdapter;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.adapters.VerificationAdapter;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.verification.Verification;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.verification.VerificationMethod;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.verification.diary.DiaryDifficulty;
import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.verification.diary.DiaryRegion;
import net.runelite.api.Skill;

import javax.inject.Inject;

public class GsonOverride {
    /**
     * Custom Gson instance capable of parsing additional types.
     */
    public static Gson GSON;

    @Inject
    public GsonOverride(Gson originalGson) {
        GsonBuilder gsonBuilder = originalGson.newBuilder()
                .registerTypeAdapter(Verification.class, new VerificationAdapter())
                .registerTypeAdapter(VerificationMethod.class, new EnumAdapter<>(VerificationMethod.class))
                .registerTypeAdapter(DiaryRegion.class, new EnumAdapter<>(DiaryRegion.class))
                .registerTypeAdapter(DiaryDifficulty.class, new EnumAdapter<>(DiaryDifficulty.class))
                .registerTypeAdapter(Skill.class, new EnumAdapter<>(Skill.class))
                .registerTypeAdapter(Tag.class, new EnumAdapter<>(Tag.class));

        GSON = gsonBuilder.create();
    }
}

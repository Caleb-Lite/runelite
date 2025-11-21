package net.runelite.client.plugins.pluginhub.com.logmaster.domain.adapters;

import com.google.gson.*;
import net.runelite.client.plugins.pluginhub.com.logmaster.domain.verification.Verification;
import net.runelite.client.plugins.pluginhub.com.logmaster.domain.verification.VerificationMethod;
import net.runelite.client.plugins.pluginhub.com.logmaster.domain.verification.clog.CollectionLogVerification;
import net.runelite.client.plugins.pluginhub.com.logmaster.domain.verification.diary.AchievementDiaryVerification;
import net.runelite.client.plugins.pluginhub.com.logmaster.domain.verification.skill.SkillVerification;
import net.runelite.client.plugins.pluginhub.com.logmaster.util.EnumUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class VerificationAdapter implements JsonDeserializer<Verification> {
    private static final String DISCRIMINATOR_FIELD = "method";

    @Override
    public Verification deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = jsonElement.getAsJsonObject();
        if (!obj.has(DISCRIMINATOR_FIELD)) {
            log.error("Verification object has no required discriminator field '{}'", DISCRIMINATOR_FIELD);
            return null;
        }

        String methodStr = obj.get(DISCRIMINATOR_FIELD).getAsString();
        VerificationMethod method = EnumUtils.fromString(VerificationMethod.class, methodStr);
        if (method == null) {
            log.warn("Verification object has unknown method '{}'", methodStr);
            return null;
        }

        switch (method) {
            case COLLECTION_LOG:
                return context.deserialize(jsonElement, CollectionLogVerification.class);

            case ACHIEVEMENT_DIARY:
                return context.deserialize(jsonElement, AchievementDiaryVerification.class);

            case SKILL:
                return context.deserialize(jsonElement, SkillVerification.class);
        }

        log.error("Unhandled verification method '{}'", method);
        return null;
    }
}

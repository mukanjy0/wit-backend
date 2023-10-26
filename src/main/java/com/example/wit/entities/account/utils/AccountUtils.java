package com.example.wit.entities.account.utils;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.account.dto.AccountRequest;
import com.example.wit.entities.platform.domain.Platform;
import com.example.wit.entities.player.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class AccountUtils {
    public static Account updateAccount(Account original, AccountRequest toUpdate) {
        if (toUpdate.getHandle() != null) original.setHandle(toUpdate.getHandle());
        if (toUpdate.getPlayerId() != null) {
            Player player = new Player();
            player.setId(toUpdate.getPlayerId());
            original.setPlayer(player);
        }
        if (toUpdate.getPlatformId() != null) {
            Platform platform = new Platform();
            platform.setId(toUpdate.getPlatformId());
            original.setPlatform(platform);
        }
        if (toUpdate.getRating() != null) original.setRating(toUpdate.getRating());
        if (toUpdate.getUrl() != null) original.setUrl(toUpdate.getUrl());

        return original;
    }
}

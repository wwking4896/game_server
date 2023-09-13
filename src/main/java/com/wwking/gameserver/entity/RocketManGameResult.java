package com.wwking.gameserver.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RocketManGameResult {
    private int maxWinMultiplier;   // 100 for 1
}

package com.wwking.gameserver.protocol.rocketManGame;

import com.wwking.gameserver.protocol.BaseRequest;
import lombok.Data;

import java.util.Date;

@Data
public class RocketManGameRequest extends BaseRequest {

    public static final String DATA_REQUEST_CURRENT_GAME_RESULT = "CurrentGameResult";

    public static final String OP_REQUEST_PLAYER_BET = "PlayerBet";
    public static final String OP_REQUEST_PLAYER_CASH_OUT = "PlayerCashOut";

    private Date requestTime;
    private Integer bet;
    private Integer gameWinMultiplier;
}

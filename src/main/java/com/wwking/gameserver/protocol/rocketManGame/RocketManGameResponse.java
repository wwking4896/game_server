package com.wwking.gameserver.protocol.rocketManGame;

import lombok.Data;
import com.wwking.gameserver.protocol.BaseResponse;
import com.wwking.gameserver.mongodb.entity.RocketManGameRecord;

@Data
public class RocketManGameResponse extends BaseResponse {

    private Integer betID;
    private RocketManGameRecord gameRecord;
    private Integer playerWin;
}

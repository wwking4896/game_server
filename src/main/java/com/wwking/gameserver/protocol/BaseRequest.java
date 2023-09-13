package com.wwking.gameserver.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequest implements Serializable {

    String action;
    String playerId;
}

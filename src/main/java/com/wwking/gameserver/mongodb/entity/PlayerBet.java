package com.wwking.gameserver.mongodb.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "PlayerBet")
public class PlayerBet {
    @Id
    private String id;
    private Integer bet;
    private Date createTime;

    public PlayerBet() {
        this.createTime = new Date();
    }
}

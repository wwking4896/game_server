package com.wwking.gameserver.mongodb.entity;

import com.wwking.gameserver.entity.RocketManGameResult;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "RocketManGameRecord")
public class RocketManGameRecord {
    @Id
    private UUID id;   // this.id = UUID.randomUUID();
    @Embedded
    private RocketManGameResult rocketManGameResult;
    private Date createTime;
    private Date expireTime;

    public RocketManGameRecord() {
        this.id = UUID.randomUUID();
        this.createTime = new Date();
        this.expireTime = new Date(this.createTime.getTime() + 60000);
    }
}

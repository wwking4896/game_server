package com.wwking.gameserver.service.impl;

import org.springframework.stereotype.Service;
import com.wwking.gameserver.service.inte.IRandomService;

import java.util.Random;

@Service
public class RandomService implements IRandomService {

    @Override
    public Integer RandomInt(int min, int max) {
        Random rand = new Random();

        return rand.nextInt(max - min) + min;
    }
}

package edu.depaul.g6.opms.service;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.Random;

public class OnPremiseMonitorSimulator {


    public float getSimulatedEnergyUsage()
    {
        float usedEnergy = 0;

        Random rand = new Random();
        usedEnergy += rand.nextFloat() * 10000;

        return usedEnergy;
    }
}


import java.util.Random;

public class OnPremiseMonitorSimulator
{
    public float getSimulatedEnergyUsage()
    {
        float usedEnergy = 0;

        Random rand = new Random();
        usedEnergy += rand.nextFloat() * 10000;

        return usedEnergy;
    }

}

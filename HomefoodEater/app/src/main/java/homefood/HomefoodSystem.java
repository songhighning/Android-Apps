package homefood;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Alex on 2016-04-14.
 */
public class HomefoodSystem {
    private ArrayList<HomefoodMaker> mMakers;

    private static HomefoodSystem sHomefoodSystem;
    private Context mAppContext;

    private HomefoodSystem(Context appContext){
        mAppContext = appContext;
        mMakers = new ArrayList<HomefoodMaker>();
        Random mRand = new Random();
        int mRandNum = 0;

        for (int i =0; i < 25;i++){
            HomefoodMaker m = new HomefoodMaker();
            m.setShopName("Chef #" + i);
            m.setEmail("Chef#" + i + "@gmail.com");

            mRandNum = mRand.nextInt(10)+1;
            //create dish menu
            for (int j = 0; j <= mRandNum;j++){
                Food mFood = new Food("Chef" + i + "'s Food " + j, i*j%25 + 10);
                m.addFoodToMenu(mFood);
            }

            mMakers.add(m);
        }
    }

    public static HomefoodSystem get(Context c){
        if (sHomefoodSystem == null){
            sHomefoodSystem = new HomefoodSystem(c.getApplicationContext());
        }
        return sHomefoodSystem;
    }

    public ArrayList<HomefoodMaker> getMakers(){
        return mMakers;
    }

    public HomefoodMaker getMaker(UUID id){
        for (HomefoodMaker m:mMakers){
            if(m.getTheHomefoodMakerID().equals(id)){
                return m;
            }
        }
        return null;
    }
}

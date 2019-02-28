package pyuuga.digiponic.com.repositories;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import pyuuga.digiponic.com.model.MenuData;

public class MenuRepository {

    private static MenuRepository instance;
    private ArrayList<MenuData> dataSet = new ArrayList<>();

    public static MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    public MutableLiveData<List<MenuData>> getMenu() {
        setMenu();

        MutableLiveData<List<MenuData>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setMenu() {
        dataSet.add(new MenuData(0, "Feast", "Rp 5000", "Ice Cream", null));
        dataSet.add(new MenuData(1, "Sandwich", "Rp 7000", "Ice Cream", null));
        dataSet.add(new MenuData(2, "Cornetto", "Rp 6000", "Ice Cream", null));
        dataSet.add(new MenuData(3, "Magnum",   "Rp 10000", "Ice Cream", null));
        dataSet.add(new MenuData(4, "Twister", "Rp 3000", "Ice Cream", null));
        dataSet.add(new MenuData(5, "Rainbow", "Rp 3500", "Ice Cream", null));
        dataSet.add(new MenuData(6, "Taro", "Rp 7000", "Cookies", null));
        dataSet.add(new MenuData(7, "Cheese", "Rp 4000", "Cookies", null));
        dataSet.add(new MenuData(8, "Chocolate", "Rp 4000", "Cookies", null));
        dataSet.add(new MenuData(9, "Green Tea", "Rp 7000", "Cookies", null));
        dataSet.add(new MenuData(10, "Strawberry", "Rp 10000", "Cotton Candy", null));
        dataSet.add(new MenuData(11, "Blueberry", "Rp 10000", "Cotton Candy", null));
        dataSet.add(new MenuData(12, "Rainbow Candy", "Rp 12000", "Cotton Candy", null));

    }
}

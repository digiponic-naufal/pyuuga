package pyuuga.digiponic.com.repositories;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import pyuuga.digiponic.com.model.CategoryData;

public class CategoryRepository {

    private static CategoryRepository instance;
    private ArrayList<CategoryData> dataSet = new ArrayList<>();

    public static CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }

    public MutableLiveData<List<CategoryData>> getCategories() {
        setCategories();

        MutableLiveData<List<CategoryData>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setCategories() {
        dataSet.add(new CategoryData("0", "Favorit"));
        dataSet.add(new CategoryData("1", "Semua Kategori"));
        dataSet.add(new CategoryData("2", "Ice Cream"));
        dataSet.add(new CategoryData("3", "Cookies"));
        dataSet.add(new CategoryData("4", "Cotton Candy"));
    }


}

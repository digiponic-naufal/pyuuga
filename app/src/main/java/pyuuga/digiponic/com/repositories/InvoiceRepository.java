package pyuuga.digiponic.com.repositories;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import pyuuga.digiponic.com.model.InvoiceData;

public class InvoiceRepository {

    private static InvoiceRepository instance;
    private ArrayList<InvoiceData> dataSet = new ArrayList<>();

    public static InvoiceRepository getInstance() {
        if (instance == null) {
            instance = new InvoiceRepository();
        }
        return instance;
    }

    public MutableLiveData<List<InvoiceData>> getInvoice() {
        setData();

        MutableLiveData<List<InvoiceData>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setData() {
        dataSet.add(new InvoiceData("TestName", "TestPrice", 0));
        dataSet.add(new InvoiceData("TestName1", "TestPrice1", 1));
        dataSet.add(new InvoiceData("TestName2", "TestPrice2", 2));
    }
}

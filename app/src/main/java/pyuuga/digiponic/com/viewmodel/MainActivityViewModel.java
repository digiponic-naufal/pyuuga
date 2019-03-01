package pyuuga.digiponic.com.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import pyuuga.digiponic.com.model.CategoryData;
import pyuuga.digiponic.com.model.InvoiceData;
import pyuuga.digiponic.com.model.MenuData;
import pyuuga.digiponic.com.repositories.CategoryRepository;
import pyuuga.digiponic.com.repositories.InvoiceRepository;
import pyuuga.digiponic.com.repositories.MenuRepository;
import pyuuga.digiponic.com.view.adapter.CategoryAdapter;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<CategoryData>> mCategoryData;
    private MutableLiveData<List<MenuData>> mMenuData;
    private MutableLiveData<List<InvoiceData>> mInvoiceData;
    private MutableLiveData<Integer> totalHarga = new MutableLiveData<>();
    private CategoryRepository mCategoryRepo;
    private MenuRepository mMenuRepo;
    private InvoiceRepository mInvoiceRepo;

    public void init() {
        if (mCategoryData != null || mMenuData != null || mInvoiceData != null) {
            return;
        }
        mCategoryRepo = CategoryRepository.getInstance();
        mCategoryData = mCategoryRepo.getCategories();

        mMenuRepo = MenuRepository.getInstance();
        mMenuData = mMenuRepo.getMenu();

        mInvoiceRepo = InvoiceRepository.getInstance();
        mInvoiceData = mInvoiceRepo.getInvoice();

        totalHarga.setValue(0);

    }

    public void addItemToInvoice(InvoiceData invoiceData) {
        new addItemInvoice().execute(invoiceData);
    }

    public LiveData<List<CategoryData>> getCategoryData() {
        return mCategoryData;
    }

    public LiveData<List<MenuData>> getMenuData() {
        return mMenuData;
    }

    public void getItemByCategory(String category) {
        mMenuData = mMenuRepo.getMenuByCategory(category);
    }

    public LiveData<List<InvoiceData>> getInvoiceData() {
        return mInvoiceData;
    }

    public LiveData<Integer> getTotalHarga() {
        return totalHarga;
    }

    private void setTotalHarga(int totalHarga) {
        this.totalHarga.setValue(totalHarga);
    }

    public class addItemInvoice extends AsyncTask<InvoiceData, Void, Void> {

        int total_harga = 0;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setTotalHarga(total_harga);
        }

        @Override
        protected Void doInBackground(InvoiceData... invoiceData) {
            boolean found = false;
            List<InvoiceData> currentData = mInvoiceData.getValue();
            InvoiceData itemData = invoiceData[0];
            assert currentData != null;
            for (InvoiceData item : currentData) {
                if (item.getId() == itemData.getId()) {
                    item.setCount(item.getCount() + 1);
                    total_harga = totalHarga.getValue() + Integer.parseInt(item.getPrice());
                    found = true;
                    break;
                }
            }
            if (!found) {
                itemData.setCount(1);
                total_harga = totalHarga.getValue() + Integer.parseInt(itemData.getPrice());
                currentData.add(itemData);
                mInvoiceData.postValue(currentData);
            }
            return null;
        }
    }

}

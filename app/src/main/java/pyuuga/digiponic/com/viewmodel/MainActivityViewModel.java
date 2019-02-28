package pyuuga.digiponic.com.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

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

    }

    public LiveData<List<CategoryData>> getCategoryData() {
        return mCategoryData;
    }

    public LiveData<List<MenuData>> getMenuData() {
        return mMenuData;
    }

    public LiveData<List<InvoiceData>> getInvoiceData() {
        return mInvoiceData;
    }


}

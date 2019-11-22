package com.jgt.autotext.ui.fragments.edit;

import com.jgt.autotext.database.DatabaseTaskListener;
import com.jgt.autotext.database.item.Item;
import com.jgt.autotext.database.item.ItemRepository;
import com.jgt.autotext.utils.Constants;
import com.jgt.autotext.utils.SharedPref;

public class EditFragmentModel implements IEditFragmentContract.Model, DatabaseTaskListener {
    private static final String TAG = EditFragmentModel.class.getSimpleName();
    private IEditFragmentContract.Presenter presenter;
    private ItemRepository itemRepository;

    private String TASK = "";

    public EditFragmentModel(IEditFragmentContract.Presenter presenter) {
        this.presenter = presenter;
        itemRepository = ItemRepository.getInstance();
    }

    @Override
    public void insertItem(Item item) {
        TASK = Constants.TASK_INSERT;
        itemRepository.insertItem(item, this);
    }

    @Override
    public void deleteAll() {
        TASK = Constants.TASK_DELETE_ALL;
        itemRepository.deleteAll(this);
    }

    //Temporary
    @Override
    public void setSmsEnabled() {
        SharedPref.get().setSmsEnabled(true);
    }

    @Override
    public void setSmsDisabled() {
        SharedPref.get().setSmsEnabled(false);
    }
    //Temporary

    @Override
    public void onFinish() {
        switch (TASK) {
            case Constants.TASK_INSERT:
                presenter.onInsertSuccess();
                break;
            case Constants.TASK_DELETE_ALL:
                presenter.onDeleteAllSuccess();
                break;
            default:
                break;
        }
    }
}

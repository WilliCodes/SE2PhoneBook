package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    private final ObservableList<TelefonEntry> phoneBook = FXCollections.observableList(new ArrayList<TelefonEntry>());

    public PhoneBook(){
        phoneBook.add(new TelefonEntry());
        phoneBook.addListener((ListChangeListener<TelefonEntry>)(c ->
        {
            if (phoneBook.isEmpty())
                phoneBook.add(new TelefonEntry());
            }));
    }

    public ObservableList<TelefonEntry> getPhoneBook() {
        return phoneBook;
    }

    public void cleanAddAll(List<TelefonEntry> list) {
        for (TelefonEntry toAdd : list) {
            if (!toAdd.isNew() && !hasDuplicate(toAdd))
                phoneBook.add(toAdd);
        }
    }

    public void addEmptyEntry() {
        for (TelefonEntry e : phoneBook) {
            if (e.isNew())
                return;
        }
        phoneBook.add(new TelefonEntry());
    }

    public void deleteEntries(ObservableList<TelefonEntry> entries){
        phoneBook.removeAll(entries);
    }

    public void setPhoneBook(List<TelefonEntry> tempList) {
        System.out.println(tempList.toString());
        phoneBook.clear();
        cleanAddAll(tempList);
    }

    private boolean hasDuplicate(TelefonEntry te) {
        for (TelefonEntry old : phoneBook) {
            if (old.equals(te))
                    return true;
        }

        return false;
    }


}

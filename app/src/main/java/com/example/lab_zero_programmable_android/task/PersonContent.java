package com.example.lab_zero_programmable_android.task;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PersonContent {

    /**
     * An array of sample (Task) items.
     */

    public static final List<PersonItem> ITEMS = new ArrayList<PersonItem>();

    public static final Map<String, PersonItem> ITEM_MAP = new HashMap<String, PersonItem>();

    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createTaskItem(i));
        }
    }

    public static void addItem(PersonItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void deleteItem(String person_id){
        PersonItem delete_person;
        try{
            delete_person = ITEM_MAP.get(person_id);
        }catch (Exception exp){
            Log.d("contact", "delete error");
            return;
        }
        ITEMS.remove(delete_person);
        ITEM_MAP.remove(delete_person);
    }

    private static PersonItem createTaskItem(int position) {
        return new PersonItem(Integer.toString(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class PersonItem {
        public final String id;
        public final String personName;
        public final String description;
        public final String picPath;

        public PersonItem(String id, String name, String description) {
            this.id = id;
            this.personName = name;
            this.description = description;
            this.picPath = "";
         }
        public PersonItem(String id, String name, String description, String picPath) {
            this.id = id;
            this.personName = name;
            this.description = description;
            this.picPath = "picPath";
        }


        @Override
        public String toString() {
            return personName;
        }
    }
}
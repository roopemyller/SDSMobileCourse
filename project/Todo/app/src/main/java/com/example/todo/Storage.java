package com.example.todo;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private ArrayList<TodoItem> items = new ArrayList<>();
    private static Storage storage = null;

    private Storage() {

    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void addItem(TodoItem item) {
        item.setId(items.size());
        items.add(item);
    }

    public ArrayList<TodoItem> getItems() {
        return items;
    }

    public void saveList(Context context) {
        try {
            ObjectOutputStream productWriter = new ObjectOutputStream(context.openFileOutput("list.data", Context.MODE_PRIVATE));
            productWriter.writeObject(items);
            productWriter.close();
        } catch (IOException e) {
            System.out.println("Error while saving data");
        }
    }

    public void loadList(Context context) {
        try {
            ObjectInputStream productReader = new ObjectInputStream(context.openFileInput("list.data"));
            items = (ArrayList<TodoItem>) productReader.readObject();
            productReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error while reading data");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while reading data");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error while reading data");
            e.printStackTrace();
        }
    }

    public void removeItem(int id) {
        int i = 0;
        for (TodoItem t : items) {
            if (t.getId() == id) {
                break;
            }
            i++;
        }
        items.remove(i);

        int j = 0;
        for (TodoItem t : items){
            t.setId(j);
            j++;
        }
    }

    public void editItem(int id, String newTitle, String newDescription){
        int i = 0;
        for (TodoItem t : items) {
            if (t.getId() == id) {
                break;
            }
            i++;
        }
        items.get(i).setHeader(newTitle);
        items.get(i).setDesc(newDescription);
    }
}

package com.eloquent;

import com.eloquent.adapters.AdapterInterface;
import com.eloquent.adapters.LocalAdapter;
import com.eloquent.exceptions.EloquentException;
import com.eloquent.models.File;

public class Main {

    public static void main(String[] args) {
        // Root Directory
        String root = "X:/www";
        // Create Adapter
        AdapterInterface adapter = new LocalAdapter(root);
        // Instantiate Eloquent
        Eloquent eloquent = new Eloquent(adapter);

        // Read a file
        try {
            File file = eloquent.read(root + "/dropbox/index.php");
            System.out.println("Name: " + file.getName());
            System.out.println("Path: " + file.getPath());
            System.out.println("Size: " + file.getSize());
            System.out.println("Timestamp: " + file.getTimestamp().toString());
            System.out.println("Contents: " + file.getContents().substring(0, 50));
        } catch (EloquentException e) {
            System.out.println(e.getMessage());
        }
    }
}

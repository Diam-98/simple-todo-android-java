package com.example.simple_todo_app;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {

    // La variable qui contient le nom du fichier
    public static final String FILENAME = "list_info.dat";

    //La methode qui enregistre les donnees sur le fichier
    public static void writeData(ArrayList<String> noteList, Context context){
        try {

            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(noteList);

            oas.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //La methode qui recupere les donnees
    public static ArrayList<String> readDate(Context context){
        ArrayList<String> noteItem = null;

        try {

            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            noteItem = (ArrayList<String>) ois.readObject();

        } catch (FileNotFoundException e) {
            noteItem = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return noteItem;
    }

}

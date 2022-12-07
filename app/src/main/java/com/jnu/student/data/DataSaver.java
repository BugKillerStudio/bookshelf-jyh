package com.jnu.student.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.jnu.student.MainActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataSaver implements Serializable {
    public void Save(Context context, ArrayList<bookitem> data)
    {
        try {
            FileOutputStream dataStream=context.openFileOutput("bookshelf_data.dat",Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(dataStream);
            out.writeObject(data);
            out.close();
            dataStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @NonNull
    public ArrayList<bookitem> Load(Context context)
    {
        ArrayList<bookitem> data=new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput("bookshelf_data.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (ArrayList<bookitem>) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Toast.makeText(MainActivity.this, "触发事件", Toast.LENGTH_SHORT).show();
        return data;
    }
}

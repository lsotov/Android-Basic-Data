package com.example.lsoto.workingwithdatabasic;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// Android uses a file system that's similar to disk-based file systems on other platforms.
// A File object is suited to reading or writing large amounts of data in start-to-finish order without skipping around.
// All Android devices have two file storage areas: "internal" and "external" storage.
// Internal storage:
// - It's always available.
// - Files saved here are accessible by only your app.
// - When the user uninstalls your app, the system removes all your app's files from internal storage.
// Internal storage is best when you want to be sure that neither the user nor other apps can access your files.
//
// External storage:
// - It's not always available, because the user can mount the external storage as USB storage and
//      in some cases remove it from the device.
// - It's world-readable, so files saved here may be read outside of your control.
// - When the user uninstalls your app, the system removes your app's files from here only if you
//      save them in the directory from getExternalFilesDir().
// External storage is the best place for files that don't require access restrictions and
// for files that you want to share with other apps or allow the user to access with a computer.
public class FileData extends Activity {

    private static final String LOG_TAG = "FileDataLog";
    private final String INTERNAL_FILE_NAME = "com.example.lsoto.myFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_data);
    }

    public void createInternalFile(View view){
        CreateFileInternal2();
    }

    public void deleteInternalFile(View view){
        DeleteFile();
    }

    public void fileExists(View view){
        File dir = getFilesDir();
        File file = new File(dir, INTERNAL_FILE_NAME);
        boolean exists = file.exists();
    }

    /**** Internal Storage ****/
    /* When saving a file to internal storage, you can acquire the appropriate directory as a File
    by calling one of two methods:

    getFilesDir(): Returns a File representing an internal directory for your app.
    getCacheDir(): Returns a File representing an internal directory for your app's temporary cache files.
    Be sure to delete each file once it is no longer needed and implement a reasonable size limit
    for the amount of memory you use at any given time, such as 1MB. If the system begins running
    low on storage, it may delete your cache files without warning.*/

    public void CreateFileInternal(){
        Context context = getApplicationContext();
        String fileName = "myfile";
        File file = new File(context.getFilesDir(), fileName);
    }

    public void CreateFileInternal2(){
        String filename = "myfile2";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            // As long as you use MODE_PRIVATE for your files on the internal storage,
            // they are never accessible to other apps.
            outputStream = openFileOutput(INTERNAL_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**** External Storage ****/
    /* Although the external storage is modifiable by the user and other apps, there are
    two categories of files you might save here:
    Public files: Files that should be freely available to other apps and to the user. When the user
        uninstalls your app, these files should remain available to the user.
        For example, photos captured by your app or other downloaded files.

    Private files: Files that rightfully belong to your app and should be deleted when the user
        uninstalls your app. Although these files are technically accessible by the user and other apps
        because they are on the external storage, they are files that realistically don't provide value
        to the user outside your app. When the user uninstalls your app, the system deletes all files
        in your app's external private directory.
        For example, additional resources downloaded by your app or temporary media files.*/
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    // If you want to save public files on the external storage, use the getExternalStoragePublicDirectory()
    // method to get a File representing the appropriate directory on the external storage.
    // The method takes an argument specifying the type of file you want to save so that they can be
    // logically organized with other public files, such as DIRECTORY_MUSIC or DIRECTORY_PICTURES.
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    // If you want to save files that are private to your app, you can acquire the appropriate
    // directory by calling getExternalFilesDir() and passing it a name indicating the type of
    // directory you'd like.
    // Each directory created this way is added to a parent directory that encapsulates all your
    // app's external storage files, which the system deletes when the user uninstalls your app.
    public File getAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

    /* If none of the pre-defined sub-directory names suit your files, you can instead call
    getExternalFilesDir() and pass null. This returns the root directory for your app's private
    directory on the external storage. */

    public long getTotalSpace(){
        return Environment.getExternalStorageDirectory().getTotalSpace();
    }

    public long getFreeSpace(){
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }

    public void DeleteFile(){
        File dir = getFilesDir();
        File file = new File(dir, INTERNAL_FILE_NAME);
        boolean deleted = file.delete();
    }


}

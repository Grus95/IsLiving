package com.example.grus95.utilslibrary.file;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.example.grus95.utilslibrary.encrypt.MD5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Created by grus95 on 16/8/31
 * 实现Android应用程序私有文件或静态资源文件的读取与写入功能.
 */

public class FileUtil {
	/**
	 * LogCat TAG 标识
	 */
	private static String TAG = FileUtil.class.getName();

	/**
	 * 读取应用程序私有文件.相对路径: /data/data/应用程序包/files/
	 * 
	 * @param file
	 *            想要读取的文件名
	 */
	public static String readFile(File file) {
		FileInputStream fis = null;
		ByteArrayOutputStream os = null;
		try {
			fis = new FileInputStream(file);
			os = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}
			return os.toString();
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					Log.e(TAG, e.getMessage(), e);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					Log.e(TAG, e.getMessage(), e);
				}
			}
		}
		return null;

	}

	/**
	 * 写入应用程序私有文件.相对路径: /data/data/应用程序包/files/
	 * 
	 * @param fileName
	 *            想要写入的文件名,没有则创建
	 */
	public static void writeFile(String fileName, byte[] buffer) {
		FileOutputStream fos = null;
		try {
			File file = new File(fileName);
			file.deleteOnExit();

			fos = new FileOutputStream(file);
			fos.write(buffer);
			fos.close();
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		} finally {
			Log.d(TAG, buffer.length + "bytes");
		}
	}

	/*
	 * 
	 */
	public static File[] getAllFiles(String dir) {

		File dirs = new File(dir);
		if (!dirs.exists()) {
			dirs.mkdirs();
			return null;
		}
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".tmp");
			}
		};

		return dirs.listFiles(filter);
	}

	public static boolean copy(File source, File target) {
		FileInputStream fin = null;
		RandomAccessFile fout = null;
		try {
			fin = new FileInputStream(source);
			fout = new RandomAccessFile(target, "rw");
			FileChannel fcin = fin.getChannel();
			FileChannel fcout = fout.getChannel();
			MappedByteBuffer mbbi = fcin.map(FileChannel.MapMode.READ_ONLY, 0,
					fcin.size());
			MappedByteBuffer mbbo = fcout.map(FileChannel.MapMode.READ_WRITE,
					0, fcin.size());
			mbbo.put(mbbi);
			mbbi.clear();
			mbbo.clear();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fin != null)
					fin.close();
				if (fout != null)
					fout.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				deleteDir(f);
			}
			dir.delete();
		} else
			dir.delete();
	}

	public static void clearDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				clearDir(f);
			}
		} else{
			dir.delete();
		}
	}
	
	public static long getFolderSize(File file){
		if(file==null || !file.exists() || !file.isDirectory()) return 0L;
        long size = 0;  
        File[] fileList = file.listFiles();  
        for (int i = 0; i < fileList.length; i++)  
        {  
            if (fileList[i].isDirectory())  
            {  
                size = size + getFolderSize(fileList[i]);  
            } else  
            {  
                size = size + fileList[i].length();  
            }  
        }  
        return size;
    }
	
	public static byte[] getBytes(File file) {
		if (file == null || !file.exists())
			return null;
		byte[] bytes = new byte[(int) file.length()];
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			in.read(bytes);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public static boolean checkMD5(File file, String md5) {
		byte[] bytes = getBytes(file);
		return md5.equals(MD5.md5(bytes));
	}
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static String getPath(final Context context, final Uri uri) {

	    final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

	    // DocumentProvider
	    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
	        // ExternalStorageProvider
	        if (isExternalStorageDocument(uri)) {
	            final String docId = DocumentsContract.getDocumentId(uri);
	            final String[] split = docId.split(":");
	            final String type = split[0];

	            if ("primary".equalsIgnoreCase(type)) {
	                return Environment.getExternalStorageDirectory() + "/" + split[1];
	            }

	            // TODO handle non-primary volumes
	        }
	        // DownloadsProvider
	        else if (isDownloadsDocument(uri)) {

	            final String id = DocumentsContract.getDocumentId(uri);
	            final Uri contentUri = ContentUris.withAppendedId(
	                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

	            return getDataColumn(context, contentUri, null, null);
	        }
	        // MediaProvider
	        else if (isMediaDocument(uri)) {
	            final String docId = DocumentsContract.getDocumentId(uri);
	            final String[] split = docId.split(":");
	            final String type = split[0];

	            Uri contentUri = null;
	            if ("image".equals(type)) {
	                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	            } else if ("video".equals(type)) {
	                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
	            } else if ("audio".equals(type)) {
	                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	            }

	            final String selection = "_id=?";
	            final String[] selectionArgs = new String[] {
	                    split[1]
	            };

	            return getDataColumn(context, contentUri, selection, selectionArgs);
	        }
	    }
	    // MediaStore (and general)
	    else if ("content".equalsIgnoreCase(uri.getScheme())) {

	        // Return the remote address
	        if (isGooglePhotosUri(uri))
	            return uri.getLastPathSegment();

	        return getDataColumn(context, uri, null, null);
	    }
	    // File
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        return uri.getPath();
	    }

	    return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 *
	 * @param context The context.
	 * @param uri The Uri to query.
	 * @param selection (Optional) Filter used in the query.
	 * @param selectionArgs (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri, String selection,
	        String[] selectionArgs) {

	    Cursor cursor = null;
	    final String column = "_data";
	    final String[] projection = {
	            column
	    };

	    try {
	        cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
	                null);
	        if (cursor != null && cursor.moveToFirst()) {
	            final int index = cursor.getColumnIndexOrThrow(column);
	            return cursor.getString(index);
	        }
	    } finally {
	        if (cursor != null)
	            cursor.close();
	    }
	    return null;
	}


	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
	    return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
	    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
	    return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
	    return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}
}
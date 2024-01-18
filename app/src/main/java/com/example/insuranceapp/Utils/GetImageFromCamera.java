package com.example.insuranceapp.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.insuranceapp.Activity.AddApplication;

public class GetImageFromCamera {

    private static final int REQUEST_CAMERA_PERMISSION = 101;


    public interface ImageCaptureListener {
        void onImageCaptured(Bitmap imageBitmap, int requestCode);
    }

    public static void requestCameraPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CAMERA_PERMISSION
            );
        }
    }

    public static void captureImage(Activity activity, ImageCaptureListener listener, int requestCode) {
        requestCameraPermission(activity);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, requestCode);
        }
    }
    public static void handleImageResult(Intent data, Activity activity, ImageCaptureListener listener, int requestCode) {
        if (data != null && data.getExtras() != null) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            if (listener != null) {
                listener.onImageCaptured(imageBitmap, requestCode);
            }
        }
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Activity activity, ImageCaptureListener listener) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    // Handle permission denial if needed
                    return;
                }
            }
            // Permission granted, proceed to capture image
            // You can customize this part based on your needs
            captureImage(activity, listener, requestCode);
        }
    }
}


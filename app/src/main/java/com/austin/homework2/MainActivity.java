package com.austin.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.QRCodeDetector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoaderCallbackInterface loaderCallbackInterface = new LoaderCallbackInterface() {
            @Override
            public void onManagerConnected(int status) {

            }

            @Override
            public void onPackageInstall(int operation, InstallCallbackInterface callback) {

            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!OpenCVLoader.initDebug()) {
            boolean success = OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,this, loaderCallbackInterface);
        } else {
            loaderCallbackInterface.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mat mat;
                ImageView imageView = findViewById(R.id.imageView);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                mat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC4);
                Utils.bitmapToMat(bitmap, mat);

                Scalar lineColor = new Scalar(255,0,0,255);
                int lineWidth =5;

                QRCodeDetector qrCodeDetector = new QRCodeDetector();
                String result = qrCodeDetector.detectAndDecode(mat);
                String[] lines  = result.split(";");
                String[] line0 = lines[0].split(" ");
                String[] line1 = lines[1].split(" ");
                String[] line2 = lines[2].split(" ");
                String[] line3 = lines[3].split(" ");
                String[] line4 = lines[4].split(" ");

                String[] point1= line0[0].split(",");
                String[] point2= line0[1].split(",");
                String[] point3= line1[0].split(",");
                String[] point4= line1[1].split(",");
                String[] point5= line2[0].split(",");
                String[] point6= line2[1].split(",");
                String[] point7= line3[0].split(",");
                String[] point8= line3[1].split(",");
                String[] point9= line4[0].split(",");
                String[] point10= line4[1].split(",");

                Point pt1 = new Point(Integer.parseInt(point1[0]), Integer.parseInt(point1[1]));
                Point pt2 = new Point(Integer.parseInt(point2[0]), Integer.parseInt(point2[1]));
                Point pt3 = new Point(Integer.parseInt(point3[0]), Integer.parseInt(point3[1]));
                Point pt4 = new Point(Integer.parseInt(point4[0]), Integer.parseInt(point4[1]));
                Point pt5 = new Point(Integer.parseInt(point5[0]), Integer.parseInt(point5[1]));
                Point pt6 = new Point(Integer.parseInt(point6[0]), Integer.parseInt(point6[1]));
                Point pt7 = new Point(Integer.parseInt(point7[0]), Integer.parseInt(point7[1]));
                Point pt8 = new Point(Integer.parseInt(point8[0]), Integer.parseInt(point8[1]));
                Point pt9 = new Point(Integer.parseInt(point9[0]), Integer.parseInt(point9[1]));
                Point pt10= new Point(Integer.parseInt(point10[0]), Integer.parseInt(point10[1]));

                Imgproc.line(mat, pt1, pt2, lineColor, lineWidth);
                Imgproc.line(mat, pt2, pt3, lineColor, lineWidth);
                Imgproc.line(mat, pt3, pt4, lineColor, lineWidth);
                Imgproc.line(mat, pt4, pt5, lineColor, lineWidth);
                Imgproc.line(mat, pt5, pt6, lineColor, lineWidth);
                Imgproc.line(mat, pt6, pt7, lineColor, lineWidth);
                Imgproc.line(mat, pt7, pt8, lineColor, lineWidth);
                Imgproc.line(mat, pt8, pt9, lineColor, lineWidth);
                Imgproc.line(mat, pt9, pt10, lineColor, lineWidth);

                Bitmap r = Bitmap.createBitmap(mat.width(), mat.height(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(mat, r);
                imageView.setImageBitmap(r);
            }
        });
    }
}

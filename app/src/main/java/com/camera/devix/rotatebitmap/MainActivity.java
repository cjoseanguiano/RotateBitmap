package com.camera.devix.rotatebitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Resources resources;
    private RelativeLayout relativeLayout;
    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the application context
       context = getApplicationContext();
        //Get the resource
        resources = getResources();

        //Get the widgets reference from XML layout
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        button = (Button)findViewById(R.id.buttonOne);
        imageView = (ImageView)findViewById(R.id.imageView);

        //Set a click setOnClickListener for Button widget
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the source bitmap to draw on canvas
                Bitmap srcBitmap = BitmapFactory.decodeResource(resources, R.drawable.photo);

                //Initialize a new Bitmap
                Bitmap bitmap = Bitmap.createBitmap(700,500, Bitmap.Config.ARGB_8888);

                //Initialize a new Canvas instance
                Canvas canvas = new Canvas(bitmap);

                //Draw a solid color on the canvas as background
                canvas.drawColor(Color.BLUE);

                //Initialize a new Paint instance to draw on canvas
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setDither(true);
                paint.setFilterBitmap(true);

                //Initialize a new Matrix instance
                Matrix matrix = new Matrix();

                //Set rotation on matrix
                matrix.setRotate(45, srcBitmap.getWidth() / 2, srcBitmap.getHeight() /2);

                // Draw the bitmap at the center position of the canvas both vertically and horizontally
                matrix.postTranslate(canvas.getWidth()/2 -srcBitmap.getWidth()/2,
                                    canvas.getHeight()/2 - srcBitmap.getHeight()/2);

                //Finally, draw bitmap on canvas as a rotated bitmap
                canvas.drawBitmap(srcBitmap,matrix,paint);

                //Display the newly created bitmap on app interface
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}

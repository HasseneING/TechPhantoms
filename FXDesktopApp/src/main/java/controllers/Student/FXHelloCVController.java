package controllers.Student;




import java.awt.FileDialog;
import java.awt.Frame;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.FileDialog;
import java.awt.Frame;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;


/**
 * The controller for our application, where the application logic is
 * implemented. It handles the button for starting/stopping the camera and the
 * acquired video stream.
 *
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @author <a href="http://max-z.de">Maximilian Zuleger</a> (minor fixes)
 * @version 2.0 (2016-09-17)
 * @since 1.0 (2013-10-20)
 *
 */
public class FXHelloCVController implements Initializable {

    recordAudio RecVoc = new recordAudio();
    String PathvidFile = "D:/video.mpg" ;
    String PathvidFile3 = "" ;

    //
    Scanner scan = new Scanner(System.in);
    VideoCapture camera = new VideoCapture(0);

    String cc = String.valueOf(camera.get(Videoio.CAP_PROP_FOURCC));
    int fps = (int) camera.get(Videoio.CAP_PROP_FPS);
    int width = (int) camera.get(Videoio.CAP_PROP_FRAME_WIDTH);
    int height = (int) camera.get(Videoio.CAP_PROP_FRAME_HEIGHT);
    final Size frameSize = new Size((int) camera.get(Videoio.CAP_PROP_FRAME_WIDTH), (int) camera.get(Videoio.CAP_PROP_FRAME_HEIGHT));

    // "D:/video7777762.mpg"

    VideoWriter save = new VideoWriter(PathvidFile , Videoio.CAP_PROP_FOURCC, fps, frameSize, true);
    // the FXML button
    @FXML
    private Button button;
    // the FXML image view
    @FXML
    private ImageView currentFrame;

    // a timer for acquiring the video stream
    private ScheduledExecutorService timer;
    // the OpenCV object that realizes the video capture
    private VideoCapture capture = new VideoCapture();
    // a flag to change the button behavior
    private boolean cameraActive = false;
    // the id of the camera to be used
    private static int cameraId = 0;
    @FXML
    private Button pathbut;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

//       Mat frame = grabFrame();
//						// convert and show the frame
//					Image imageToShow = Utils.mat2Image(frame);
//						updateImageView(currentFrame, imageToShow);
//
//System.out.print("treztreatrae");

    }
    @FXML
    protected void startCamera(ActionEvent event)
    {

//         System.out.print(PathvidFile+" fffffffffffffffff");
        if (!this.cameraActive)
        {
            // start the video capture
            this.capture.open(cameraId);

            // is the video stream available?
            if (this.capture.isOpened())
            {
                this.cameraActive = true;

                Thread stopper = new Thread(   new Runnable() {
                    public void run() {
                        try {
                            RecVoc.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                });

                stopper.start();
                // grab a frame every 33 ms (30 frames/sec)
                Runnable frameGrabber = new Runnable() {

                    @Override
                    public void run()
                    {


                        //   stopper.start();
                        System.out.print("dazdasdqsd") ;
                        // effectively grab and process a single frame
                        Mat frame = grabFrame();
                        // convert and show the frame
                        Image imageToShow = Utils.mat2Image(frame);
                        updateImageView(currentFrame, imageToShow);
                        //  RecVoc.start() ;

                    }
                };

                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);

                // update the button content
                this.button.setText("Stop Camera");
            }
            else
            {
                // log the error
                System.err.println("Impossible to open the camera connection...");
            }
        }
        else
        {
            // the camera is not active at this point
            this.cameraActive = false;
            // update again the button content
            this.button.setText("Start Camera");

            // stop the timer
            this.stopAcquisition();
        }
    }

    /**
     * Get a frame from the opened video stream (if any)
     *
     * @return the {@link Mat} to show
     */
    public Mat grabFrame()
    {
        // init everything
        Mat frame = new Mat();


        // check if the capture is open
        if (this.capture.isOpened())
        {
            try
            {

                // read the current frame
                this.capture.read(frame);
                // camera.read(frame);
                save.write(frame);
                // if the frame is not empty, process it
                if (!frame.empty())
                {
                    //Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
                }

            }
            catch (Exception e)
            {
                // log the error
                System.err.println("Exception during the image elaboration: " + e);
            }
        }

        return frame;
    }

    /**
     * Stop the acquisition from the camera and release all the resources
     */
    private void stopAcquisition()
    {
        RecVoc.finish();

        if (this.timer!=null && !this.timer.isShutdown())
        {
            try
            {
                // stop the timer
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e)
            {
                // log any exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }

        if (this.capture.isOpened())
        {
            // release the camera
            this.capture.release();
        }
        try {
            this.MergeAudVid() ;
        } catch (IOException ex) {
            Logger.getLogger(FXHelloCVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update the {@link ImageView} in the JavaFX main thread
     *
     * @param view
     *            the {@link ImageView} to update
     * @param image
     *            the {@link Image} to show
     */
    private void updateImageView(ImageView view, Image image)
    {
        Utils.onFXThread(view.imageProperty(), image);
    }

    /**
     * On application close, stop the acquisition from the camera
     */
    protected void setClosed()
    {
        this.stopAcquisition();
    }

    @FXML
    private void pathfile(ActionEvent event) {


        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.SAVE);
        dialog.setVisible(true);
        String filee = dialog.getDirectory() + dialog.getFile();

        PathvidFile3=filee;
        System.out.println(filee );

        // VideoWriter Newsave = new VideoWriter(PathvidFile , Videoio.CAP_PROP_FOURCC, fps, frameSize, true);
        //  save = Newsave ;
    }


    private void MergeAudVid  () throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "D:\\ffmpeg\\bin\\ffmpeg.exe -i D:\\audio.wav -i D:\\video.mpg -acodec copy -vcodec copy "+PathvidFile3);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        line = r.readLine();

        System.out.println(line);
        System.out.println("yiyiyyyyyyy");


    }
}

package core.utils;

import static org.bytedeco.javacpp.opencv_calib3d.cvFindHomography;
import static org.bytedeco.javacpp.opencv_core.CV_64FC1;
import static org.bytedeco.javacpp.opencv_core.CV_AA;
import static org.bytedeco.javacpp.opencv_core.CV_WHOLE_SEQ;
import static org.bytedeco.javacpp.opencv_core.cvCircle;
import static org.bytedeco.javacpp.opencv_core.cvCloneImage;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_core.cvCreateMat;
import static org.bytedeco.javacpp.opencv_core.cvCreateMemStorage;
import static org.bytedeco.javacpp.opencv_core.cvDrawCircle;
import static org.bytedeco.javacpp.opencv_core.cvDrawLine;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_core.cvGet2D;
import static org.bytedeco.javacpp.opencv_core.cvGetSeqElem;
import static org.bytedeco.javacpp.opencv_core.cvGetSize;
import static org.bytedeco.javacpp.opencv_core.cvPoint;
import static org.bytedeco.javacpp.opencv_core.cvSize;
import static org.bytedeco.javacpp.opencv_core.cvZero;
import static org.bytedeco.javacpp.opencv_highgui.cvCreateCameraCapture;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.CV_CHAIN_APPROX_SIMPLE;
import static org.bytedeco.javacpp.opencv_imgproc.CV_GAUSSIAN;
import static org.bytedeco.javacpp.opencv_imgproc.CV_HOUGH_GRADIENT;
import static org.bytedeco.javacpp.opencv_imgproc.CV_POLY_APPROX_DP;
import static org.bytedeco.javacpp.opencv_imgproc.CV_RETR_LIST;
import static org.bytedeco.javacpp.opencv_imgproc.cvApproxPoly;
import static org.bytedeco.javacpp.opencv_imgproc.cvCanny;
import static org.bytedeco.javacpp.opencv_imgproc.cvCheckContourConvexity;
import static org.bytedeco.javacpp.opencv_imgproc.cvContourArea;
import static org.bytedeco.javacpp.opencv_imgproc.cvContourPerimeter;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.cvFindContours;
import static org.bytedeco.javacpp.opencv_imgproc.cvHoughCircles;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;
import static org.bytedeco.javacpp.opencv_imgproc.cvWarpPerspective;
import gui.components.ProgressDialog;
import interfaces.CameraObserver;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.CvContour;
import org.bytedeco.javacpp.opencv_core.CvMat;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvPoint;
import org.bytedeco.javacpp.opencv_core.CvPoint3D32f;
import org.bytedeco.javacpp.opencv_core.CvScalar;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.CvSize;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_highgui;
import org.bytedeco.javacpp.opencv_highgui.CvCapture;

import core.Globals;

public class Camera {

	private static Camera	instance;

	public static final int	MODE_STANDARD					= 0;	// MODO NORMAL
	public static final int	MODE_CORNERS_DETECTION_PREVIEW	= 1;	// MODO DETECTOR DE ESQUINAS RECTANGULOS
	public static final int	MODE_CORRECT_PERSPECTIVE		= 2;	// MODO CORRECTOR DE PERSPECTIVA
	public static final int	MODE_NEW_ROBOT_MARKED			= 3;	// MODO DETECTOR DE NUEVO ROBOT

	private boolean			reading;
	private boolean			stopped;
	private CvCapture		camera;
	private IplImage		frame;
	private int				mode;

	private Camera() {
		this.reading = false;
		this.stopped = true;
		this.mode = MODE_STANDARD;
	}

	public void run(final CameraObserver cameraObserver) {
		if (!this.reading) {
			ProgressDialog dialog = new ProgressDialog("Iniciando cámara");
			dialog.setVisible(true);
			this.camera = cvCreateCameraCapture(opencv_highgui.CV_CAP_ANY); // TODO Comprobar si no tiene
																			// camara
			opencv_highgui.cvSetCaptureProperty(this.camera, opencv_highgui.CV_CAP_PROP_FRAME_HEIGHT,
					Globals.CAMERA_HEIGHT);
			opencv_highgui.cvSetCaptureProperty(this.camera, opencv_highgui.CV_CAP_PROP_FRAME_WIDTH,
					Globals.CAMERA_WIDTH);
			dialog.dispose();
			this.reading = true;
			this.stopped = false;
			new Thread() {
				public void run() {
					while (Camera.this.reading) {
						Camera.this.frame = opencv_highgui.cvQueryFrame(Camera.this.camera);
						if (Camera.this.frame != null) {
							if (Globals.Mirror) {
								cvFlip(Camera.this.frame, Camera.this.frame, 1);
							}
							switch (Camera.this.mode) {
								case MODE_STANDARD:

									break;
								case MODE_CORNERS_DETECTION_PREVIEW:
									Point[] squares = findSquare(Camera.this.frame);
									if (squares != null && squares.length == 4) {
										Camera.this.frame = drawRectangle(Camera.this.frame, squares);
									}
									break;
								case MODE_CORRECT_PERSPECTIVE:
									Camera.this.frame = perspectiveCorrection(Camera.this.frame, Globals.CircuitCorners);
									break;
								case MODE_NEW_ROBOT_MARKED:
									// Camera.this.frame = perspectiveCorrection(Camera.this.frame,
									// Globals.CircuitCorners);
									if (Globals.AuxRobot != null) {
										Point[] circles = findCircles();
										for (Point circle : circles) {
											Color color = getPointColors(circle.x, circle.y, 0, 0).firstElement();
											if (Utilities.ColorBetweenMaxMin(color, Globals.AuxRobot.getColorMin(),
													Globals.AuxRobot.getColorMax(), 10)) {
												CvPoint center = new CvPoint();
												center.x(circle.x);
												center.y(circle.y);
												cvCircle(Camera.this.frame, center, 10, CvScalar.GREEN, 6, CV_AA, 0);
											}
										}
									}
									break;
							}
							cameraObserver.changeImage(cvCloneImage(Camera.this.frame));
						}
					}
					Camera.this.frame.release();
					Camera.this.stopped = true;
				}
			}.start();
		}
	}

	public static void setMode(int mode) {
		instance.mode = mode;
	}

	public Point[] findSquare(IplImage img) {
		try {
			CvMemStorage storage = cvCreateMemStorage(0);
			Point[] squares = null;
			CvSize cvSize = cvSize(img.width(), img.height());
			IplImage gray = cvCreateImage(cvSize, img.depth(), 1);
			cvCvtColor(img, gray, CV_BGR2GRAY);

			cvCanny(gray, gray, 0, 50, 5);

			CvSeq contours = new CvSeq();
			cvFindContours(gray, storage, contours, Loader.sizeof(CvContour.class), CV_RETR_LIST,
					CV_CHAIN_APPROX_SIMPLE, cvPoint(0, 0));

			boolean enc = false;
			while (contours != null && !contours.isNull() && !enc) {
				CvSeq result = cvApproxPoly(contours, Loader.sizeof(CvContour.class), storage, CV_POLY_APPROX_DP,
						cvContourPerimeter(contours) * 0.02, 0);

				if (result.total() == 4 && Math.abs(cvContourArea(result, CV_WHOLE_SEQ, 0)) > 10000
						&& cvCheckContourConvexity(result) != 0) {

					double s = 0.0, t = 0.0;

					for (int i = 0; i < 5; i++) {
						if (i >= 2) {
							t = Math.abs(angle(new CvPoint(cvGetSeqElem(result, i)),
									new CvPoint(cvGetSeqElem(result, i - 2)), new CvPoint(cvGetSeqElem(result, i - 1))));
							s = s > t ? s : t;
						}
					}

					if (s < 0.3) {
						squares = new Point[4];
						for (int i = 0; i < 4; i++) {
							squares[i] = new Point(new CvPoint(cvGetSeqElem(result, i)).x(), new CvPoint(cvGetSeqElem(
									result, i)).y());
						}
						enc = true;
					}
				}
				contours = contours.h_next();
			}
			return squares;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Point[] findCircles() {
		IplImage gray = cvCreateImage(cvGetSize(Camera.this.frame), 8, 1);
		cvCvtColor(Camera.this.frame, gray, CV_BGR2GRAY);
		cvSmooth(gray, gray, CV_GAUSSIAN, 3, 3, 0, 0);
		CvMemStorage mem = CvMemStorage.create();
		CvSeq circles = cvHoughCircles(gray, // Input image
				mem, // Memory Storage
				CV_HOUGH_GRADIENT, // Detection method
				1, // Inverse ratio
				10, // Minimum distance between the centers of the detected circles
				80, // Higher threshold for canny edge detector
				80, // Threshold at the center detection stage
				10, // min radius
				500 // max radius
		);
		Point[] points = new Point[circles.total()];
		for (int i = 0; i < circles.total(); i++) {
			CvPoint3D32f circle = new CvPoint3D32f(cvGetSeqElem(circles, i));
			CvPoint center = new CvPoint();
			center.x((int) circle.x());
			center.y((int) circle.y());
			points[i] = new Point((int) circle.x(), (int) circle.y());
		}
		return points;
	}

	public IplImage drawRectangle(IplImage img, Point[] squares) {
		IplImage cpy = cvCloneImage(img);
		for (int i = 0; i < squares.length; i++) {
			if (i < squares.length - 1) {
				CvPoint origin = new CvPoint();
				origin.x(squares[i].x);
				origin.y(squares[i].y);
				CvPoint end = new CvPoint();
				end.x(squares[i + 1].x);
				end.y(squares[i + 1].y);
				cvDrawLine(cpy, origin, end, CvScalar.GREEN, 3, CV_AA, 0);
			} else {
				CvPoint origin = new CvPoint();
				origin.x(squares[i].x);
				origin.y(squares[i].y);
				CvPoint end = new CvPoint();
				end.x(squares[0].x);
				end.y(squares[0].y);
				cvDrawLine(cpy, origin, end, CvScalar.GREEN, 3, CV_AA, 0);
			}
		}
		return cpy;
	}

	public IplImage drawCircle(IplImage img, int x, int y, int radius) {
		IplImage cpy = cvCloneImage(img);
		CvPoint p = new CvPoint();
		p.x(x);
		p.y(y);
		cvDrawCircle(cpy, p, 10, CvScalar.BLUE, -1, 8, 0);
		return cpy;
	}

	public IplImage perspectiveCorrection(IplImage img, Point[] squares) {
		if (squares.length == 4) {
			IplImage cpy = cvCloneImage(img);
			CvMat p = cvCreateMat(4, 2, CV_64FC1);
			CvMat h = cvCreateMat(4, 2, CV_64FC1);
			CvMat p2h = cvCreateMat(3, 3, CV_64FC1);
			cvZero(p);
			cvZero(h);
			cvZero(p2h);

			squares = Utilities.ReorderRectangleCorners(squares);

			p.put(0, 0, squares[0].x); // BLUE TOP LEFT
			p.put(0, 1, squares[0].y);
			p.put(1, 0, squares[1].x); // RED TOP RIGHT
			p.put(1, 1, squares[1].y);
			p.put(2, 0, squares[2].x); // GREEN BOT RIGHT
			p.put(2, 1, squares[2].y);
			p.put(3, 0, squares[3].x); // YELLOW BOT LEFT
			p.put(3, 1, squares[3].y);

			h.put(0, 0, 0); // TOP LEFT
			h.put(0, 1, 0);
			h.put(1, 0, Globals.CAMERA_WIDTH); // TOP RIGHT
			h.put(1, 1, 0);
			h.put(2, 0, Globals.CAMERA_WIDTH); // BOT RIGHT
			h.put(2, 1, Globals.CAMERA_HEIGHT);
			h.put(3, 0, 0); // BOT LEFT
			h.put(3, 1, Globals.CAMERA_HEIGHT);

			cvFindHomography(p, h, p2h);
			cvWarpPerspective(img, cpy, p2h);
			return cpy;
		}
		return img;
	}

	public Vector<Color> getPointColors(int x, int y, int marginWidth, int marginHeight) {
		Vector<Color> colors = new Vector<Color>();
		for (int j = x - marginHeight; j < x + marginHeight; j++) {
			for (int i = x - marginWidth; i < x + marginWidth; i++) {
				if (i >= 0 && j >= 0 && i < Globals.CAMERA_WIDTH - 1 && j < Globals.CAMERA_HEIGHT - 1) {
					CvScalar s = cvGet2D(Camera.this.frame, i, j);
					colors.add(new Color((int) s.val(2), (int) s.val(1), (int) s.val(0)));
				}
			}
		}
		return colors;
	}

	public static double angle(CvPoint pt1, CvPoint pt2, CvPoint pt0) {
		double dx1 = pt1.x() - pt0.x();
		double dy1 = pt1.y() - pt0.y();
		double dx2 = pt2.x() - pt0.x();
		double dy2 = pt2.y() - pt0.y();
		return (dx1 * dx2 + dy1 * dy2) / Math.sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2) + 1e-10);
	}

	public void stop() {
		this.reading = false;
		while (!this.stopped) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		opencv_highgui.cvReleaseCapture(this.camera);
	}

	public boolean isReading() {
		return instance.reading;
	}

	public IplImage getImage() {
		return this.frame;
	}

	public static Camera getInstance() {
		if (instance == null) {
			instance = new Camera();
		}
		return instance;
	}

}
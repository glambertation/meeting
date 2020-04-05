package org.meeting.demo.business;


import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class PushRTMPSS {
	static boolean exit = false;

	public static void run() throws Exception {
		System.out.println("start...");
		String rtmpPath = "rtmp://58.200.131.2:1935/livetv/gxtv";
		/*String rtmpPath = "rtmp://58.200.131.2:1935/livetv/hunantv";*/
		String rtspPath = "rtmp://localhost:1935/oflaDemo/sssssss"; //oflaDemo
		//String rtspPath = "rtmp://58.200.131.2:1935/livetv/gxtvvvvv";
		int audioRecord = 0; // 0 = 不录制，1=录制
		boolean saveVideo = false;
		push(rtmpPath, rtspPath, audioRecord, saveVideo);

		/*String rtmpPath1 = "rtmp://58.200.131.2:1935/livetv/gxtv";
		*//*String rtmpPath1 = "rtmp://58.200.131.2:1935/livetv/hunantv";*//*
		String rtspPath1 = "rtmp://localhost:1935/oflaDemo/sssssss"; //oflaDemo
		//String rtspPath1 = "rtmp://58.200.131.2:1935/livetv/gxtvvvvv";
		int audioRecord1 = 0; // 0 = 不录制，1=录制
		boolean saveVideo1 = false;
		push(rtmpPath1, rtspPath1, audioRecord1, saveVideo1);*/

		System.out.println("end...");
	}

	@SuppressWarnings("resource")
	private static void push(String rtmpPath, String rtspPath, int audioRecord, boolean saveVideo){
		// 使用rtsp的时候需要使用 FFmpegFrameGrabber，不能再用 FrameGrabber
		int width = 640, height = 480;
		try {
			FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(rtmpPath);
			grabber.setOption("rtsp_transport", "tcp"); // 使用tcp的方式，不然会丢包很严重

			grabber.setImageWidth(width);
			grabber.setImageHeight(height);
			System.out.println("grabber start");
			grabber.start();
			// 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
			FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(rtspPath, width, height, audioRecord);
			recorder.setInterleaved(true);
			// recorder.setVideoOption("crf","28");
			recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
			recorder.setFormat("flv"); // rtmp的类型
			recorder.setFrameRate(25);
			recorder.setImageWidth(width);
			recorder.setImageHeight(height);
			recorder.setPixelFormat(0); // yuv420p

			// 192 Kbps

			recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
			System.out.println("recorder start");
			recorder.start();
			 
			System.out.println("all start!!"); 
			while (!exit) { 
				Frame frame = grabber.grabImage();
				if (frame == null) {
					continue;
				} 
				recorder.record(frame);
			} 
			grabber.stop();
			grabber.release();
			recorder.stop();
			recorder.release();
		} catch (Exception e) {
			//不抛异常
		} 
	}
}
package org.meeting.demo.business;


import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;
import org.meeting.demo.audio.record;

import javax.sound.sampled.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PushRTMP {
	static boolean exit = false;

	public static void run() throws Exception {
		System.out.println("start...");
		/*String rtmpPath = "rtmp://58.200.131.2:1935/livetv/gxtv";*/
		String rtmpPath = "rtmp://58.200.131.2:1935/livetv/hunantv";
		String rtspPath = "rtmp://localhost:1935/oflaDemo/123"; //oflaDemo
		//String rtspPath = "rtmp://58.200.131.2:1935/livetv/gxtvvvvv";
		int audioRecord = 1; // 0 = 不录制，1=录制
		boolean saveVideo = false;
		push(rtmpPath, rtspPath, audioRecord, saveVideo);

/*		String rtmpPath1 = "rtmp://58.200.131.2:1935/livetv/gxtv";
		*//*String rtmpPath1 = "rtmp://58.200.131.2:1935/livetv/hunantv";*//*
		String rtspPath1 = "rtmp://localhost:1935/oflaDemo/sssssss"; //oflaDemo
		//String rtspPath1 = "rtmp://58.200.131.2:1935/livetv/gxtvvvvv";
		int audioRecord1 = 1; // 0 = 不录制，1=录制
		boolean saveVideo1 = false;
		push(rtmpPath1, rtspPath1, audioRecord1, saveVideo1);*/

        System.out.println("start...record");
       /* record.recordWebcamAndMicrophone(0,4,"rtmp://localhost:1935/oflaDemo/sssssss",640,480,50);
*/
		System.out.println("end...");
	}
    final private static int WEBCAM_DEVICE_INDEX= 1;
    final private static int AUDIO_DEVICE_INDEX= 4;

    final private static int FRAME_RATE = 30;
    final private static int GOP_LENGTH_IN_FRAMES= 60;

    private static long startTime= 0;
    private static long videoTS= 0;

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

			recorder.setFormat("flv");


/*
			// 192 Kbps
            recorder.setAudioOption("crf", "0");
            // 最高质量
            recorder.setAudioQuality(0);
            // 音频比特率
            recorder.setAudioBitrate(192000);
            // 音频采样率
            recorder.setSampleRate(44100);
            // 双通道(立体声)
            recorder.setAudioChannels(2);
            // 音频编/解码器*/
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
			System.out.println("recorder start");
			recorder.start();


// Thread for audio capture, this could be in a nested private class if you prefer...
            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    // Pick a format...
                    // NOTE: It is better to enumerate the formats that the system supports,
                    // because getLine() can error out with any particular format...
                    // For us: 44.1 sample rate, 16 bits, stereo, signed, little endian
                    AudioFormat audioFormat = new AudioFormat(44100.0F,16, 2, true, false);

                    // Get TargetDataLine with that format
                    Mixer.Info[] minfoSet= AudioSystem.getMixerInfo();
                    Mixer mixer = AudioSystem.getMixer(minfoSet[4]);
                    DataLine.Info dataLineInfo= new DataLine.Info(TargetDataLine.class, audioFormat);

                    try
                    {
                        // Open and start capturing audio
                        // It's possible to have more control over the chosen audio device with this line:
                        // TargetDataLine line = (TargetDataLine)mixer.getLine(dataLineInfo);
                        TargetDataLine line = (TargetDataLine)AudioSystem.getLine(dataLineInfo);
                        line.open(audioFormat);
                        line.start();

                        int sampleRate = (int) audioFormat.getSampleRate();
                        int numChannels = audioFormat.getChannels();

                        // Let's initialize our audio buffer...
                        int audioBufferSize = sampleRate * numChannels;
                        byte[] audioBytes = new byte[audioBufferSize];

                        // Using a ScheduledThreadPoolExecutor vs a while loop with
                        // a Thread.sleep will allow
                        // us to get around some OS specific timing issues, and keep
                        // to a more precise
                        // clock as the fixed rate accounts for garbage collection
                        // time, etc
                        // a similar approach could be used for the webcam capture
                        // as well, if you wish
                        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
                        exec.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run()
                        {
                            try
                            {
                                // Read from the line... non-blocking
                                int nBytesRead = line.read(audioBytes,0, line.available());

                                // Since we specified 16 bits in the AudioFormat,
                                // we need to convert our read byte[] to short[]
                                // (see source from FFmpegFrameRecorder.recordSamples for AV_SAMPLE_FMT_S16)
                                // Let's initialize our short[] array
                                int nSamplesRead = nBytesRead / 2;
                                short[] samples = new short[nSamplesRead];

                                // Let's wrap our short[] into a ShortBuffer and
                                // pass it to recordSamples
                                ByteBuffer.wrap(audioBytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(samples);
                                ShortBuffer sBuff = ShortBuffer.wrap(samples,0, nSamplesRead);
                             /*   System.out.println(
                                        "sBuff-flap correction:");
*/
                                // recorder is instance of
                                // org.bytedeco.javacv.FFmpegFrameRecorder
                                recorder.recordSamples(sampleRate, numChannels, sBuff);
                            }
                            catch (org.bytedeco.javacv.FrameRecorder.Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }, 0, (long)1000 / 30, TimeUnit.MILLISECONDS);
                    }
                    catch (LineUnavailableException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }).start();
			 
			System.out.println("all start!!");


/*            Frame capturedFrame = null;

            // While we are capturing...
            while ((capturedFrame = grabber.grab()) != null)
            {


                // Let's define our start time...
                // This needs to be initialized as close to when we'll use it as
                // possible,
                // as the delta from assignment to computed time could be too high
                if (startTime == 0)
                    startTime = System.currentTimeMillis();

                // Create timestamp for this frame
                videoTS = 1000 * (System.currentTimeMillis()- startTime);

                // Check for AV drift
                if (videoTS > recorder.getTimestamp())
                {
                    System.out.println(
                            "Lip-flap correction:"
                                    + videoTS + " :"
                                    + recorder.getTimestamp()+ " -> "
                                    + (videoTS - recorder.getTimestamp()));

                    // We tell the recorder to write this frame at this timestamp
                    recorder.setTimestamp(videoTS);
                }

                // Send the frame to the org.bytedeco.javacv.FFmpegFrameRecorder
                recorder.record(capturedFrame);
            }*/

          while (!exit) {
				Frame frame = grabber.grabImage();
				Frame audio = grabber.grabSamples();



				if (frame == null) {
                    System.out.println("掉帧");
					continue;
				}
                if (startTime == 0)
                    startTime = System.currentTimeMillis();

                // Create timestamp for this frame
                videoTS = 1000 * (System.currentTimeMillis()- startTime);

                // Check for AV drift
                if (videoTS > recorder.getTimestamp())
                {
                    System.out.println(
                            "Lip-flap correction:"
                                    + videoTS + " :"
                                    + recorder.getTimestamp()+ " -> "
                                    + (videoTS - recorder.getTimestamp()));

                    // We tell the recorder to write this frame at this timestamp
                    recorder.setTimestamp(videoTS);
                }

                // Send the frame to the org.bytedeco.javacv.FFmpegFrameRecorder
                recorder.record(frame);
				/*recorder.record(frame);*/
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


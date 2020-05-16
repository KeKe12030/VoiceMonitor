package cn.mcplugin.demo;

import java.io.ByteArrayOutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class RecordVoice implements Runnable{
	public static ByteArrayOutputStream out = new ByteArrayOutputStream();
	static boolean stopped = false;
	public void record() {
		try {
			AudioFormat format = AudioUtils.getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class,format); // format is an AudioFormat object
			TargetDataLine  line = (TargetDataLine)(AudioSystem.getLine(info));
			line.open(format);
			line.start();
			// Assume that the TargetDataLine, line, has already
			// been obtained and opened.
			int numBytesRead;
			byte[] data = new byte[line.getBufferSize() / 5];
			// Begin audio capture.
			line.start();
			// Here, stopped is a global boolean set by another thread.
			for(;;) {
				// Read the next chunk of data from the TargetDataLine.
				numBytesRead =  line.read(data, 0, data.length);
				// Save this chunk of data.
				out.write(data, 0, numBytesRead);
			}
		} catch (LineUnavailableException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		record();
	}
	public static void main(String[] args) {
	}
}

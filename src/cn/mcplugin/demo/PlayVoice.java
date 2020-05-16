package cn.mcplugin.demo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class PlayVoice implements Runnable {
	private InputStream is = null;
	public PlayVoice() {
		Thread t = new Thread(new RecordVoice());
		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		is = (ByteArrayInputStream) AudioUtils.streamTran(RecordVoice.out);
		System.out.println("record finnished");
	}
	public void updateIs() {
		is = (ByteArrayInputStream) AudioUtils.streamTran(RecordVoice.out);
	}
	public void play() {
		System.out.println("进入Play");
		SourceDataLine sdl = null;
		try {
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, AudioUtils.getAudioFormat());
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(AudioUtils.getAudioFormat());
			sdl.start();
			byte[] buffer = new byte[1024];
			int temp = 0;
			while(true) {
				temp = is.read(buffer);
				//(temp = inputStream.read(buffer))>=0
				try {
					sdl.write(buffer, 0, temp);
				}catch(Exception e1) {continue;}
				//输出声音
				updateIs();
				//每一次都重新获取一下声音流
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sdl.drain();
			sdl.close();
		}
	}
	@Override
	public void run() {
		play();
	}

	public static void main(String[] args) {
		new PlayVoice().play();
	}
}

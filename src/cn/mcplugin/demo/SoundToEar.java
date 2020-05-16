package cn.mcplugin.demo;

public class SoundToEar {
	public static void main(String[] args) {
		PlayVoice pv = new PlayVoice();
		Thread t = new Thread(pv);
		t.start();
	}
}

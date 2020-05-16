package cn.mcplugin.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;

public class AudioUtils {
	public static AudioFormat getAudioFormat(){
		//����ע�Ͳ���������һ����Ƶ��ʽ�����߶�����
		AudioFormat.Encoding encoding = AudioFormat.Encoding.
        PCM_SIGNED ;
		float rate = 8000f;
		int sampleSize = 16;
		String signedString = "signed";
		boolean bigEndian = true;
		int channels = 1;
		return new AudioFormat(encoding, rate, sampleSize, channels,
				(sampleSize / 8) * channels, rate, bigEndian);
//		//��������ÿ�벥�ź�¼�Ƶ�������
//		float sampleRate = 16000.0F;
//		// ������8000,11025,16000,22050,44100
//		//sampleSizeInBits��ʾÿ�����д˸�ʽ�����������е�λ��
//		int sampleSizeInBits = 16;
//		// 8,16
//		int channels = 1;
//		// ������Ϊ1��������Ϊ2
//		boolean signed = true;
//		// true,false
//		boolean bigEndian = true;
//		// true,false
//		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed,bigEndian);
	}
	
	
	
	public static InputStream streamTran(ByteArrayOutputStream baos) {
		return new ByteArrayInputStream(baos.toByteArray());
	}
}

package com.zl.lock.util;

import java.util.Random;

import com.zl.lock.config.Config;

public class RandomSort {
	private Random random = new Random();
	// �����С
	private int size;
	// Ҫ�����������
	private char[] positions;

	/**
	 * 
	 * @param ch
	 *            ����������
	 * @param size
	 *            ��Ҫ����Ĵ�С
	 */
	public RandomSort(char[] ch, int size) {
		positions = ch;
		this.size = size;
	}

	// ������
	public char[] changePosition() {
		addRandomLetter(Config.BUTTON_NUM-size);
		delReapeat();
		for (int index = size - 1; index >= 0; index--) {
			// ��0��index��֮�����ȡһ��ֵ����index����Ԫ�ؽ���
			exchange(random.nextInt(index + 1), index);
		}
		return positions;
	}

	// ����λ��
	private void exchange(int p1, int p2) {
		char temp = positions[p1];
		positions[p1] = positions[p2];
		positions[p2] = temp; // ����λ��
	}

	// ȥ�ظ�
	private void delReapeat() {
		char[] temp = new char[100];
		int flag = 0;
		int count = 0;
		int i;
		for (i = 0; i < size - 1; i++) {
			flag = 0;
			for (int j = i + 1; j < size; j++) {
				if (positions[i] == positions[j]) {
					// ����ͬ��Ԫ��
					flag = 1;
				}
			}
			if (flag == 0) {
				temp[count] = positions[i];
				count++;
			}
		}
		temp[count] = positions[i];
		size = count;
		positions = temp;
	}

	/**
	 * ��������ĸ
	 * 
	 * @param num
	 */
	private void addRandomLetter(int num) {
		String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z" };

		Boolean flag = true;
		while (flag) {
			// ��ȡһ�������
			int temp = random.nextInt(25);
			// ��־λ 0-û���ظ� 1-���ظ�
			int isRepeat = 0;
			// ���������ж��Ƿ��������ĸ
			for (int i = 0; i < size; i++) {
				if ((positions[i] + "").equals(letters[temp])) {
					// �����ظ���־λ��һ
					isRepeat = 1;
					break;
				}
			}
			//------û���ظ�
			if(isRepeat==0){
				positions[size] = letters[temp].toCharArray()[0];
				size++;
				num--;
			}
			
			// ������
			if (num == 0) {
				flag = false;
			}
		}

	}
}

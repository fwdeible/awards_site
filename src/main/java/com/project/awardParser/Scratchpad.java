package com.project.awardParser;

public class Scratchpad {

	public static void main(String... args) {
		organize(new String[] { "fate", "pedal", "stash", "plead", "grasp", "feat", "hats", "paled" });
	}

	public static void organize(String[] array) {
		for (String s : array) { // for every element in the array
			System.out.println(s);
			for (String s2 : array) { // check against every other element in array
				char[] charArray = s.toCharArray();
				boolean canBeUsed = true;
				for (char c : charArray) { // check for each character 
					// in second string contains character from first
					if (s2.indexOf(c) < 0) { // will return -1 if s2 doesn't contain that char
						canBeUsed = false;
						break;
					} 
					//each character found,
					//make sure there's enough of each character in the first to spell the second 
					if (countCharsInString(s, c) < countCharsInString(s2, c)) {
						canBeUsed = false;
						break;
					}
				}
				if (canBeUsed && !s.equals(s2)) {
					System.out.println(s2);
				}
			}

			System.out.println("---");
		}

	}

	public static int countCharsInString(String str, char c) {
		int count = 0;
		for (char ch : str.toCharArray()) {
			if (ch == c) {
				count++;
			}
		}
		return count;
	}
}
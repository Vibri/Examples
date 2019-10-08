package cz.vibri.examples.drives;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

import javax.swing.filechooser.FileSystemView;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		FileSystemView fsv = FileSystemView.getFileSystemView();

		for (File drive : File.listRoots()) {
			System.out.println("Drive Name: " + drive);
			System.out.println("Description: " + fsv.getSystemTypeDescription(drive));
			System.out.println();
		}

		System.out.println("Write down selected drive:");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String selectedDrive = reader.readLine().trim();

		printFileContent(new File(getDrive(selectedDrive)));

	}

	private static String getDrive(String selectedDrive) {
		String regex = ".";
		String drive = selectedDrive.substring(0, 1).toUpperCase();
		if (drive.matches(regex)) {
			return drive + ":\\";
		} else {
			throw new IllegalArgumentException("Selected drive must be one character.");
		}
	}

	private static void printFileContent(File file) {
		File[] files = file.listFiles();
		boolean containFiles = false;
		StringJoiner sj = new StringJoiner("\n\t");
		sj.add(file.getPath() + ":");
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					printFileContent(f);
				} else {
					containFiles = true;
					sj.add(f.getName());
				}
			}
		}
		if (containFiles) {
			System.out.println(sj.toString());
		}
	}
}

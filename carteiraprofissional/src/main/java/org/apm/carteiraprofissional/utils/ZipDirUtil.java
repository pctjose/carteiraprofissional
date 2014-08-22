package org.apm.carteiraprofissional.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipDirUtil {

	private static Logger log = Logger.getLogger(ZipDirUtil.class);

	public static void zipDir(String sourceFolder, String targetZipedFolder) {

		try {

			// create byte buffer

			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(targetZipedFolder);

			ZipOutputStream zos = new ZipOutputStream(fos);

			File dir = new File(sourceFolder);

			File[] files = dir.listFiles();

			for (int i = 0; i < files.length; i++) {

				System.out.println("Adding file: " + files[i].getName());

				FileInputStream fis = new FileInputStream(files[i]);

				// begin writing a new ZIP entry, positions the stream to the
				// start of the entry data

				zos.putNextEntry(new ZipEntry(files[i].getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {

					zos.write(buffer, 0, length);

				}

				zos.closeEntry();

				// close the InputStream

				fis.close();

			}

			// close the ZipOutputStream

			zos.close();

		}

		catch (IOException ioe) {

			log.debug(ioe);

		}

	}

}

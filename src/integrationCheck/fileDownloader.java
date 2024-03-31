package integrationCheck;

public class fileDownloader {
	public static boolean downloadFile(String url, String fullPath) {
		// TODO - This is stupid...
		String fileType = fullPath.split(".")[fullPath.split(".").length - 1];
		System.out.println(fileType);


		/* When downloading a different file
		 * URL url = new URL("https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/doNotTouch/language.csv");
				// System.out.println(url);
				InputStream in = url.openStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				in.close();
				fos.close();
		 */


		/* If fullpath ends with .png or .jpg, download image
		 * URL url = new URL("https://raw.githubusercontent.com/DiamondCoder02/nsfw-game-manager/master/icons_doNotTouch/" + picturesThatGets[i]);
					BufferedImage img = ImageIO.read(url);
					ImageIO.write(img, "png", file2);
		 */
	}
}

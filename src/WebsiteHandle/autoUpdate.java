package WebsiteHandle;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JOptionPane;

public class autoUpdate {
	public static String onlineV = null;
	public static Runnable updating(String currentVersion, String onlineVersion, String onlineLocation) {
		onlineV = onlineVersion;
		String path, ext;
		Boolean success = false;
		try{
			path = autoUpdateCheck.class.getProtectionDomain().getCodeSource().getLocation().toURI().toString();
			path = path.replace("file:/", "");
			ext = path.substring(path.lastIndexOf(".") + 1, path.length());
			// path = "C:\\Users\\Diamond\\Desktop";
			// ext = "exe";
			onlineLocation = onlineLocation.replace("tag", "download").concat("/HentaiGameManager." + ext);

			System.out.println(path);
			System.out.println(onlineLocation);
			// TODO Broken autoUpdate:
			try{
				URL gotUrl = new URL(onlineLocation);
				// System.out.println(gotUrl);
				InputStream in = gotUrl.openStream();

				try{ path.replace("_"+currentVersion+".", "_"+onlineVersion+"."); } 
				catch (Exception e) { path = path.replace("."+ext, "_"+onlineVersion+"."+ext); }

				FileOutputStream fos = new FileOutputStream(path);
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				in.close();
				fos.close();
				success = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error downloading update >.< (autoUpdate2)", "Error", JOptionPane.ERROR_MESSAGE);
			}

			// if (success){ settingsManager.xmlSettings("appVersion", "appVer"); }

			JOptionPane.showMessageDialog(null, success+"\n"+path+"\n"+onlineLocation, "Yes?", JOptionPane.ERROR_MESSAGE);	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error downloading update >.< (autoUpdate)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public String newVersion(){
		return onlineV;
	}
}

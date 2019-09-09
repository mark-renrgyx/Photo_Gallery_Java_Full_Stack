package db;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import entities.Image;
import entities.User;

public class HibernateUtil {
	
	private static Session session = HibernateConnection.getSession();
	
	/**
	 * Get the `src` for each <img> in a List
	 * 
	 * @return
	 */
	public static List<String> getImages(Integer user_id) {
		
//		List<Image> images = session.createQuery("From Image where user_id='" + user_id + "'").list();
	
//		String hql = "SELECT I.filename FROM image I WHERE I.user_id = " + user_id;
//		Query<String> query = session.createQuery(hql, String.class);
//		List<String> filenames = query.list();
//		
//		return filenames;
	
//		if (user_id == null) // TODO: Who knows?
//			return new ArrayList<String>();
		
		User theUser = session.get(User.class, user_id);
		if (theUser == null) {
			System.err.println("Why are we getting images for nobody?");
			return new ArrayList<String>();
		}
		
		List<Image> images = theUser.getImages();
		if (images == null)
			return new ArrayList<String>();
		
		Iterator<Image> it = images.iterator();
		List<String> filenames = new ArrayList<String>();
		
		while (it.hasNext()) {
			filenames.add("img" + "/" + it.next().getFilename());
		}
		
		return filenames;
	}
	
	public static String sha256(String base) { // from https://stackoverflow.com/questions/3103652/hash-string-via-sha-256-in-java/3103727#3103727
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		} catch (Exception e) {
			System.err.println("FAILED TO HASH");
			e.printStackTrace();
		}
		return hexString.toString();
	}
}

package model;

import java.util.ArrayList;

public class Paths {

	private int id;
	private int itemId;
	private ArrayList<String> paths;

	public Paths() {
		this.id = 0;
		this.itemId = 0;
		this.paths = new ArrayList<String>();
	}

	public void addPath(String path) {
		this.paths.add(path);
	}

	public String getPath(int index) {
		return this.paths.get(index);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public ArrayList<String> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<String> paths) {
		this.paths = paths;
	}

	public ArrayList<String> getRelativePaths() {
		ArrayList<String> toReturn = new ArrayList<String>();

		for (String patToModify : this.paths) {

			String[] tempPhat = patToModify.split("/");
			String relativePath = "";
			for (int i = 8; i < tempPhat.length; i++) {
				if (i < tempPhat.length - 1)
					relativePath += tempPhat[i] + "/";
				else
					relativePath += tempPhat[i];
			}
			toReturn.add(relativePath);
		}
		return toReturn;
	}

	public String getRelativePath(int index){
		return getRelativePaths().get(index);
	}
	
}

package model;

public class CompleteItem {

	private Item item;
	private Paths paths;

	public CompleteItem() {
		this.item = new Item();
		this.paths = new Paths();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Paths getPaths() {
		return paths;
	}

	public void setPaths(Paths paths) {
		this.paths = paths;
	}

	public void setPath(String path) {
		this.paths.addPath(path);
	}

	public String getPath(int index) {
		return this.paths.getPath(index);
	}
}

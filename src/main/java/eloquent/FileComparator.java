package eloquent;

import eloquent.models.Metadata;

import java.util.Comparator;

/**
 * @author Kunal
 */
public class FileComparator implements Comparator<Metadata> {

	private static final String DEFAULT_COMPARISON_KEY = "name";
	protected final String ascKey = "asc";
	protected final String descKey = "desc";
	protected final String defaultOrder = ascKey;
	protected String key;
	protected String order;

	public FileComparator() {
		this.key = DEFAULT_COMPARISON_KEY;
	}

	public FileComparator(String key) {
		this.key = key;
		this.order = defaultOrder;
	}

	public FileComparator(String key, String order) {
		this.key = key;
		this.order = order;
	}

	public int compare(Metadata f1, Metadata f2) {

		switch (this.key) {
		case "size":
			return this.compareBySize(f1, f2);
		case "timestamp":
			return this.compareByTimestamp(f1, f2);
		default:
			return this.compareByName(f1, f2);
		}
	}

	/**
	 * Sort Files by name
	 *
	 * @param f1
	 *            File
	 * @param f2
	 *            File
	 *
	 * @return int
	 */
	protected int compareByName(Metadata f1, Metadata f2) {
        if (f1.getName() != null && f2.getName() != null) {
            return f1.getName().compareTo(f2.getName());
        }

        return -1;
	}

	/**
	 * Sort Files by size
	 *
	 * @param f1
	 *            File
	 * @param f2
	 *            File
	 *
	 * @return int
	 */
	protected int compareBySize(Metadata f1, Metadata f2) {
        if (f1.getSize() != null && f2.getSize() != null) {
            int s1 = Integer.parseInt(f1.getSize().isEmpty() ? "0" : f1.getSize());
            int s2 = Integer.parseInt(f2.getSize().isEmpty() ? "0" : f2.getSize());

            return s1 - s2;
        }

        return -1;
	}

	/**
	 * Sort Files by timestamp
	 *
	 * @param f1
	 *            File
	 * @param f2
	 *            File
	 *
	 * @return int
	 */
	protected int compareByTimestamp(Metadata f1, Metadata f2) {
		if (f1.getTimestamp() != null && f2.getTimestamp() != null) {
			return f1.getTimestamp().compareTo(f2.getTimestamp());
		}

		return -1;
	}

}

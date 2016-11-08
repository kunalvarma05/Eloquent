package eloquent;

import eloquent.adapters.AdapterInterface;
import eloquent.adapters.LocalAdapter;
import eloquent.exceptions.EloquentException;
import eloquent.models.File;

public class Main {

    /**
     * Dropbox Access Token
     */
    private static final String ACCESS_TOKEN = "pHsUz289lkEAAAAAAAADivHzOVZUU4sXV0olTwMqwGtaVgGSty1ca7lRmMuJU-aN";

	public static void main(String[] args) {

	}

	private void localTest() {
        // Root Directory
        String root = "X:/www";
        // Create Adapter
        AdapterInterface adapter = new LocalAdapter(root);
        // Instantiate Eloquent
        Eloquent eloquent = new Eloquent(adapter);

        // Read a file
        try {
            File file = eloquent.read(root + "/dropbox/index.php");
            System.out.println("Name: " + file.getName());
            System.out.println("Path: " + file.getPath());
            System.out.println("Size: " + file.getSize());
            System.out.println("Timestamp: " + file.getTimestamp().toString());
            System.out.println("Contents: " + file.getContents().substring(0, 50));
        } catch (EloquentException e) {
            System.out.println(e.getMessage());
        }
    }
}
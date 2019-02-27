import JSON.DataParser;
import objects.Player;

public class main {
    public static void main(String[] args) {
        DataParser.parse();
        Player player = new Player(1);

        player.skillsToNextChunk();
        player.BiSItems();
    }
}


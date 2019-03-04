import JSON.DataParser;
import constants.Skills;
import objects.Player;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        DataParser.parse();
        Player player = new Player(new int[]{1});
        player.checkTrainableSkills();
    }
}


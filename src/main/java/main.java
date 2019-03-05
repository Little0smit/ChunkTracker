import JSON.DataParser;
import constants.Constant;
import constants.Skills;
import objects.Player;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        if (args.length > 0){
            DataParser.parse(args[0].equals("debug"));
        } else {
            DataParser.parse(false);
        }
        Player player = new Player(new int[]{1});
        player.checkTrainableSkills();
    }
}


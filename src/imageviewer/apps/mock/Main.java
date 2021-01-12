
package imageviewer.apps.mock;

import control.Command;
import control.InitCommand;
import control.NextCommand;
import control.NullCommand;
import control.PrevCommand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Image;
import view.ImageDisplay;
import view.ImageLoader;


public class Main {

  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,Command> commands = initCommands(new ArrayList<>(),new MockImageDisplay(),new MockImageLoader());
        while(true) {
            commands.getOrDefault(scanner.next(),NullCommand.Instance).execute();     
        }
    }

    private static Map<String, Command> initCommands(List<Image> images, ImageDisplay imageDisplay,ImageLoader imageLoader) {
        HashMap<String,Command> result = new HashMap<>();
        result.put("i",new InitCommand(imageLoader,images,imageDisplay));
        result.put("p",new PrevCommand(images,imageDisplay));
        result.put("n",new NextCommand(images, imageDisplay));
        result.put("Q",result.get("q"));
        result.put("P",result.get("p"));
        result.put("N",result.get("n"));
        return result;
    } 
}

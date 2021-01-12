
package control;

import java.util.List;
import model.Image;
import view.ImageDisplay;

public class PrevCommand implements Command{
    private  final List<Image> images;
    private  final ImageDisplay imageDisplay;

    public PrevCommand(List<Image> images, ImageDisplay imageDisplay) {
       this.images = images;
       this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute() {
        imageDisplay.display(prev());
    }

    private Image prev() {
        int index = images.indexOf(imageDisplay.currentImage());
        return images.get((index-1+images.size()) % images.size());
    }

}

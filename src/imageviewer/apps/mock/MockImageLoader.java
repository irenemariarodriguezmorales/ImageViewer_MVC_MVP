
package imageviewer.apps.mock;

import java.util.ArrayList;
import java.util.List;
import model.Image;
import view.ImageLoader;

public class MockImageLoader implements ImageLoader {

    @Override
    public List<Image> load() {
        List<Image> list = new ArrayList<>();
        list.add(new Image("hola"));
        list.add(new Image("mundo"));
        list.add(new Image("bienvenido"));
        return list;
    }

}

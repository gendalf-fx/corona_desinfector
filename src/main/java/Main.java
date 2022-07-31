import com.gendalf.app.config.ObjectFactory;
import com.gendalf.app.data.CoronaDesinfector;
import com.gendalf.app.data.Room;

public class Main {
    public static void main(String[] args) {
        CoronaDesinfector coronaDesinfector = ObjectFactory.getInstance().createObject(CoronaDesinfector.class);
        coronaDesinfector.start(new Room());
    }
}

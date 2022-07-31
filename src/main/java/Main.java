import java.util.HashMap;
import java.util.Map;

import com.gendalf.app.config.ObjectFactory;
import com.gendalf.app.config.impl.JavaConfig;
import com.gendalf.app.context.ApplicationContext;
import com.gendalf.app.data.CoronaDesinfector;
import com.gendalf.app.data.Room;
import com.gendalf.app.runner.Application;
import com.gendalf.app.servcie.policeman.Policeman;
import com.gendalf.app.servcie.policeman.impl.AngerPoliceman;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
            Application.run("com.gendalf.app", new HashMap<>(Map.of(Policeman.class, AngerPoliceman.class)));
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room());

    }
}

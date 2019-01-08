package wolox.training.factories;

import java.util.ArrayList;
import java.util.List;

public abstract class FactoryBase<Class> {
    public abstract Class build();

    public List<Class> buildList(Integer instances) {
        List<Class> records = new ArrayList<Class>();
        for(int i=0; i < instances; i++) {
            records.add(build());
        }
        return records;
    }
}

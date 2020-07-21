package xml.converter.wiki.model;

import javax.xml.bind.Unmarshaller;
import java.util.List;

public abstract class BaseModel extends Unmarshaller.Listener {
    protected Object parent;

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent)     {
        this.parent = parent;
    }

    public Object getParent(){
        return parent;
    }

    public void setParent(Object parent){
        this.parent = parent;
    }

    public abstract List<Object> getContent();
}
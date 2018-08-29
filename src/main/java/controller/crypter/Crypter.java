package controller.crypter;

import model.entity.Entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Crypter <T extends Entity>{
    private Map<Integer,Integer> complianceTable = new HashMap<>();
    /*
    public List<T> cryptEntityId(List<T> entityList){
        int fakeId = 0;
        for (T entity : entityList) {
            complianceTable.put(++fakeId, entity.getId());
            //entity.setId(fakeId);
        }
        return entityList;
    }
    */
    public Map<Integer,Integer> getComplianceTable(List<T> entityList){
        cryptEntityId(entityList);
        return complianceTable;
    }

    public List<SecurityEntity<T>> cryptEntityId(List<T> entityList){
        List<SecurityEntity<T>> securityEntityList = new LinkedList<>();
        for (T entity : entityList) {
            int fakeId = entity.hashCode();
            complianceTable.put(fakeId, entity.getId());
            securityEntityList.add(new SecurityEntity<>(fakeId,entity));
        }
        return securityEntityList;
    }


    public Map<Integer,Integer> getComplianceTable(){
        return complianceTable;
    }
}

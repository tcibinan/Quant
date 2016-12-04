package ru.ifmo.quant;

import org.springframework.beans.factory.InitializingBean;
import ru.ifmo.quant.entity.AccountEntity;

import java.util.HashMap;

/**
 * Created by andrey on 08.11.2016.
 */
public class HandlingProcess implements InitializingBean {

    private HandlingState handlingState;
    private AccountEntity accountEntity;
    private HashMap<String,Object> processParameters;

    public void afterPropertiesSet() throws Exception {
        processParameters = new HashMap<String, Object>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        AccountEntity that = (AccountEntity) o;

        return accountEntity.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return accountEntity.getId().hashCode();
    }

    public HandlingState getHandlingState() {
        return handlingState;
    }

    public void setHandlingState(HandlingState handlingState) {
        this.handlingState = handlingState;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    public void setParameter(String name, Object object) {
        processParameters.put(name, object);
    }

    public <K> K getParameter(String name, Class<K> className) {
        return (K) processParameters.get(name);
    }

    public void clearParameters() {
        processParameters.clear();
    }

    public void changeState(String stateName) {
        try {
            handlingState.changeExtractor(stateName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

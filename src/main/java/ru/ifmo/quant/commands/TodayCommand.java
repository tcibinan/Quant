package ru.ifmo.quant.commands;

import ru.ifmo.quant.HandlingProcess;
import ru.ifmo.quant.QuantMessage;
import ru.ifmo.quant.entity.AccountEntity;
import ru.ifmo.quant.exceptions.WrongContextCommandException;

/**
 * Created by andrey on 09.11.2016.
 */
public class TodayCommand extends AbstractCommand {

    public String perform(QuantMessage input, AccountEntity account, HandlingProcess process) throws WrongContextCommandException {
        //TODO: realise perform method
        return "Today";
    }
}